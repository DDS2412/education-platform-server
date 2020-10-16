package tpo.services.impl;

import org.springframework.stereotype.Service;
import tpo.domains.Chat;
import tpo.domains.Message;
import tpo.domains.User;
import tpo.dtos.request.AssignTestDto;
import tpo.dtos.request.NewChatDto;
import tpo.dtos.request.NewMessageDto;
import tpo.dtos.response.ExecutionStatusDto;
import tpo.dtos.simplified.SimplifiedChatDto;
import tpo.dtos.simplified.SimplifiedMessageDto;
import tpo.enums.MessageType;
import tpo.mappers.simplified.SimplifiedChatMapper;
import tpo.repositories.ChatRepository;
import tpo.repositories.MessageRepository;
import tpo.repositories.UserRepository;
import tpo.services.ChatService;
import tpo.services.TestService;
import tpo.services.UserTestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final TestService testService;

    private final UserTestService userTestService;

    private final SimplifiedChatMapper simplifiedChatMapper;

    public ChatServiceImpl(ChatRepository chatRepository,
                           UserRepository userRepository,
                           MessageRepository messageRepository,
                           TestService testService,
                           UserTestService userTestService,
                           SimplifiedChatMapper simplifiedChatMapper) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.testService = testService;

        this.userTestService = userTestService;

        this.simplifiedChatMapper = simplifiedChatMapper;
    }

    @Override
    public ExecutionStatusDto createNewChat(NewChatDto newChatDto) {
        List<User> users = newChatDto
                .getUserNames()
                .stream()
                .map(userRepository::findByLogin)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (users.size() == 0) {
            return ExecutionStatusDto.createBadExecutionStatus("Необходимо ввести имена существующих пользователей!");
        }

        if(newChatDto.getChatName().isEmpty()){
            return ExecutionStatusDto.createBadExecutionStatus("Необходимо ввести название чата!");
        }

        Optional<User> optionalUser = userRepository.findByToken(newChatDto.getUserToken());
        if (optionalUser.isEmpty()) {
            return ExecutionStatusDto.createBadExecutionStatus("Передан неккоректный токен!");
        }

        User user = optionalUser.get();
        users.add(user);

        chatRepository
                .save(new Chat()
                        .setUsers(users)
                        .setName(newChatDto.getChatName()));

        return ExecutionStatusDto.createOkExecutionStatus();
    }

    @Override
    public List<SimplifiedChatDto> getChatsByUserToken(String userToken) {
        Optional<User> optionalUser = userRepository.findByToken(userToken);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return chatRepository
                    .findAllByUsers(List.of(user))
                    .stream()
                    .map(chat -> simplifiedChatMapper.toDto(chat).setCountOfUsers(chat.getUsers().size()))
                    .collect(Collectors.toList());
        }

        return null;
    }

    @Override
    public List<SimplifiedMessageDto> getChatMassages(String userToken, Integer chatId) {
        Optional<User> optionalUser = userRepository.findByToken(userToken);

        if (optionalUser.isPresent()){
            Optional<Chat> optionalChat = chatRepository.findById(chatId);
            if (optionalChat.isPresent()){
                Chat chat = optionalChat.get();

                List<SimplifiedMessageDto> result = new ArrayList<>();

                chat
                        .getMessages()
                        .forEach(message ->
                                result.add(new SimplifiedMessageDto(
                                        message.getId(),
                                        message.getText(),
                                        message.getUser().getLogin(),
                                        message.getMetaInformation(),
                                        message.getType()
                                )));

                return result;
            }
        }

        return null;
    }

    @Override
    public ExecutionStatusDto sendNewMessage(NewMessageDto newMessageDto) {
        Optional<User> optionalUser = userRepository.findByToken(newMessageDto.getUserToken());

        if(newMessageDto.getText().contains("@")){
            return ExecutionStatusDto.createBadExecutionStatus("Введен недопустимый символ");
        }

        if (optionalUser.isPresent()){
            User user = optionalUser.get();

            Optional<Chat> optionalChat = chatRepository.findById(newMessageDto.getChatId());
            if (optionalChat.isPresent()){
                Chat chat = optionalChat.get();
                Message newMessage = new Message()
                        .setText(newMessageDto.getText())
                        .setChat(chat)
                        .setUser(user)
                        .setType(MessageType.MESSAGE.getType());

                chat
                        .getMessages()
                        .add(newMessage);

                chatRepository.save(chat);

                return ExecutionStatusDto.createOkExecutionStatus();
            }

            return ExecutionStatusDto.createBadExecutionStatus("Передан неккоректный идентификатор чата!");
        }

        return ExecutionStatusDto.createBadExecutionStatus("Передан неккоректный токен!");
    }

    @Override
    public ExecutionStatusDto assignTest(AssignTestDto assignTestDto) {
        Optional<User> optionalUser = userRepository.findByToken(assignTestDto.getUserToken());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();

            Optional<Chat> optionalChat = chatRepository.findById(assignTestDto.getChatId());
            if (optionalChat.isPresent()){
                Chat chat = optionalChat.get();
                List<User> filteredUsers = chat
                        .getUsers()
                        .stream()
                        .filter(user1 -> !user1.getLogin().equals(user.getLogin()))
                        .collect(Collectors.toList());

                ExecutionStatusDto executionStatusDto = userTestService.assignTestOnUsers(filteredUsers, assignTestDto.getTestId());
                if (executionStatusDto.getStatus()){
                    Message newMessage = new Message()
                            .setText(testService.getTestTitleById(assignTestDto.getTestId()))
                            .setChat(chat)
                            .setUser(user)
                            .setType(MessageType.ASSIGN_COMMAND.getType())
                            .setMetaInformation(assignTestDto.getTestId().toString());

                    chat
                            .getMessages()
                            .add(newMessage);

                    chatRepository.save(chat);

                    return ExecutionStatusDto.createOkExecutionStatus();
                }

                return executionStatusDto;
            }
        }

        return ExecutionStatusDto.createBadExecutionStatus("Передан неккоректный токен!");
    }
}

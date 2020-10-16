package tpo.services;

import tpo.dtos.request.AssignTestDto;
import tpo.dtos.request.NewChatDto;
import tpo.dtos.request.NewMessageDto;
import tpo.dtos.response.ExecutionStatusDto;
import tpo.dtos.simplified.SimplifiedChatDto;
import tpo.dtos.simplified.SimplifiedMessageDto;

import java.util.List;

public interface ChatService {
    ExecutionStatusDto createNewChat(NewChatDto newChatDto);

    List<SimplifiedChatDto> getChatsByUserToken(String userToken);

    List<SimplifiedMessageDto> getChatMassages(String userToken, Integer chatId);

    ExecutionStatusDto sendNewMessage(NewMessageDto newMessageDto);

    ExecutionStatusDto assignTest(AssignTestDto assignTestDto);
}

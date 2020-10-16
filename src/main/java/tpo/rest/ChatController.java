package tpo.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpo.dtos.request.AssignTestDto;
import tpo.dtos.request.NewChatDto;
import tpo.dtos.request.NewMessageDto;
import tpo.dtos.response.ExecutionStatusDto;
import tpo.dtos.simplified.SimplifiedChatDto;
import tpo.dtos.simplified.SimplifiedMessageDto;
import tpo.services.ChatService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/chats", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(allowCredentials = "true")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "/new")
    private ResponseEntity<ExecutionStatusDto> createNewChat(@RequestBody @Valid NewChatDto newChatDto){
        return ResponseEntity.ok(chatService.createNewChat(newChatDto));
    }

    @GetMapping(value = "/my")
    private ResponseEntity<List<SimplifiedChatDto>> getChatsByUserToken(@RequestParam("user_token") String userToken){
        return ResponseEntity.ok(chatService.getChatsByUserToken(userToken));
    }

    @GetMapping(value = "/massages")
    private ResponseEntity<List<SimplifiedMessageDto>> getChatMassages(
            @RequestParam("user_token") String userToken, @RequestParam("chat_id") Integer chatId){
        return ResponseEntity.ok(chatService.getChatMassages(userToken, chatId));
    }

    @PostMapping(value = "/massages/send")
    private ResponseEntity<ExecutionStatusDto> sendNewMessage(@RequestBody @Valid NewMessageDto newMessageDto){
        return ResponseEntity.ok(chatService.sendNewMessage(newMessageDto));
    }

    @PostMapping(value = "/assign/test")
    private ResponseEntity<ExecutionStatusDto> assignTest(@RequestBody @Valid AssignTestDto assignTestDto){
        return ResponseEntity.ok(chatService.assignTest(assignTestDto));
    }
}

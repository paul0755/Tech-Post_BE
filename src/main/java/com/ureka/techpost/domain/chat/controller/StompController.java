package com.ureka.techpost.domain.chat.controller;

import com.ureka.techpost.domain.chat.dto.request.ChatMessageReq;
import com.ureka.techpost.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class StompController {

    private final SimpMessageSendingOperations messageTemplate;
    private final ChatService chatService;

    @MessageMapping("/{roomId}")
    public void sendMessage(@DestinationVariable Long roomId, Long userid, ChatMessageReq chatMessageReq) {
      chatService.saveMessage(roomId, userid, chatMessageReq);
      chatService.saveMessage(roomId, userid, chatMessageReq);
      messageTemplate.convertAndSend("/topic/" + roomId, chatMessageReq);
    }
}
package com.example.mschatsocket.controller;

import com.example.mschatsocket.dto.ChatMessage;
import com.example.mschatsocket.entity.Message;
import com.example.mschatsocket.repository.MessageRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WebSocketController {

    private final MessageRepository messageRepository;

    public WebSocketController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage chat(@DestinationVariable String roomId, ChatMessage message) {
        // Guardar el mensaje en la base de datos
        Message savedMessage = messageRepository.save(new Message(
                message.getUser(), message.getMessage(), System.currentTimeMillis(), roomId
        ));

        // Retornar el mensaje como DTO
        return new ChatMessage(savedMessage.getMessage(), savedMessage.getUser());
    }

    // Método para obtener los mensajes históricos de una sala
    @MessageMapping("/chat/history/{roomId}")
    @SendTo("/topic/{roomId}")
    public List<ChatMessage> getChatHistory(@DestinationVariable String roomId) {
        // Consultar los mensajes de la base de datos por roomId
        List<Message> messages = messageRepository.findAllByRoomId(roomId);

        // Convertir las entidades Message en DTOs ChatMessage
        return messages.stream()
                .map(msg -> new ChatMessage(msg.getMessage(), msg.getUser()))
                .collect(Collectors.toList());
    }

}

package com.grandprixhub.grandprixhub.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grandprixhub.grandprixhub.model.ChatMessage;
import com.grandprixhub.grandprixhub.service.ChatRepository;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @GetMapping
    public List<ChatMessage> getAllMessages() {
        return chatRepository.findAll();
    }

    @PostMapping
    public ChatMessage sendMessage(@RequestBody ChatMessage chatMessage) {
        // Set timestamp if not provided
        if (chatMessage.getTimestamp() == null) {
            chatMessage.setTimestamp(LocalDateTime.now());
        }
        return chatRepository.save(chatMessage);
    }
}
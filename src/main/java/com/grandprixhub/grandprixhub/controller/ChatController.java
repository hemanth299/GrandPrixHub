package com.grandprixhub.grandprixhub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; // New import for @PostMapping
import org.springframework.web.bind.annotation.RequestMapping; // New import for @RequestBody
import org.springframework.web.bind.annotation.RestController;

import com.grandprixhub.grandprixhub.model.ChatMessage;
import com.grandprixhub.grandprixhub.service.ChatRepository;

@RestController
@RequestMapping("/api/chat") // Base path for chat endpoints
public class ChatController {

    private final ChatRepository chatRepository;

    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    // GET all chat messages
    @GetMapping
    public List<ChatMessage> getAllMessages() {
        return chatRepository.findAll();
    }

    // POST a new chat message
    @PostMapping
    public ChatMessage sendMessage(@RequestBody ChatMessage chatMessage) { // @RequestBody binds request body to ChatMessage object
        return chatRepository.save(chatMessage);
    }
}
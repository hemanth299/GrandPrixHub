package com.grandprixhub.grandprixhub.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.grandprixhub.grandprixhub.model.ChatMessage;

import jakarta.annotation.PostConstruct; // For generating unique IDs

@Component
public class ChatRepository {

    private final List<ChatMessage> messages = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong idCounter = new AtomicLong(); // Simple ID generator

    @PostConstruct
    public void init() {
        // Add some initial dummy chat messages
        messages.add(new ChatMessage(String.valueOf(idCounter.incrementAndGet()), "F1Fanatic", "Excited for the next race!", LocalDateTime.now().minusMinutes(10)));
        messages.add(new ChatMessage(String.valueOf(idCounter.incrementAndGet()), "MotorheadMax", "Who do you think will win in Silverstone?", LocalDateTime.now().minusMinutes(5)));
        messages.add(new ChatMessage(String.valueOf(idCounter.incrementAndGet()), "GrandPrixGuru", "My prediction is always the pole sitter!", LocalDateTime.now().minusMinutes(2)));

        System.out.println("Initialized " + messages.size() + " chat messages.");
    }

    public List<ChatMessage> findAll() {
        return new ArrayList<>(messages); // Return a copy to prevent external modification
    }

    public ChatMessage save(ChatMessage chatMessage) {
        if (chatMessage.getId() == null || chatMessage.getId().isEmpty()) {
            chatMessage.setId(String.valueOf(idCounter.incrementAndGet()));
        }
        if (chatMessage.getTimestamp() == null) {
            chatMessage.setTimestamp(LocalDateTime.now());
        }
        messages.add(chatMessage);
        return chatMessage;
    }
}
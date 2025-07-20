package com.grandprixhub.grandprixhub.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; // For timestamp

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String id;
    private String username;
    private String message;
    private LocalDateTime timestamp;
}
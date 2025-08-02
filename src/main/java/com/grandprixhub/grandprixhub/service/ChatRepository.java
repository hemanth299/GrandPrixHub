package com.grandprixhub.grandprixhub.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grandprixhub.grandprixhub.model.ChatMessage;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {
}
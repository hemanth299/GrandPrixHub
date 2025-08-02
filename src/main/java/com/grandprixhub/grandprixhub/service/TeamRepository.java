package com.grandprixhub.grandprixhub.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grandprixhub.grandprixhub.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
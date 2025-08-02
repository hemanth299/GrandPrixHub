package com.grandprixhub.grandprixhub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grandprixhub.grandprixhub.model.Team;
import com.grandprixhub.grandprixhub.service.TeamRepository;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

  private final TeamRepository teamRepository;

  @Autowired
  public TeamController(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @GetMapping
  public List<Team> getAllTeams() {
    return teamRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
    Optional<Team> team = teamRepository.findById(id);

    if (team.isPresent()) {
      return ResponseEntity.ok(team.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
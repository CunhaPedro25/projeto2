package org.projeto.data.controllers;

import org.projeto.data.entities.Team;
import org.projeto.data.entities.User;
import org.projeto.data.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    public TeamController(TeamService teamService) {
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewTeam(@RequestBody Team newTeam) {
        try {
            TeamService.addNew(newTeam);
            return new ResponseEntity<>("Team added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = TeamService.getTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/get/free")
    public ResponseEntity<List<Team>> getFreeTeams() {
        List<Team> teams = TeamService.getTeams();
        List<Team> freeTeams = teams.stream()
                .filter(team -> !team.getBusy())
                .collect(Collectors.toList());
        return new ResponseEntity<>(freeTeams, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        try {
            Team team = TeamService.getTeamById(id);
            return new ResponseEntity<>(team, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/leader/{id}")
    public ResponseEntity<User> getLeaderByTeamId(@PathVariable Long id) {
        try {
            User leader = TeamService.getLeaderByTeamId(id);
            return new ResponseEntity<>(leader, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        try {
            TeamService.delete(id);
            return new ResponseEntity<>("Team deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTeam(@PathVariable Long id, @RequestBody User leader) {
        try {
            TeamService.update(id, leader);
            return new ResponseEntity<>("Team updated successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

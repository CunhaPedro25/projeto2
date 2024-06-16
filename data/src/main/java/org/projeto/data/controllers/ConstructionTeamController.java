package org.projeto.data.controllers;

import org.projeto.data.entities.ConstructionTeam;
import org.projeto.data.services.ConstructionTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/constructionTeam")
public class ConstructionTeamController {
    public final ConstructionTeamService constructionTeamService;

    @Autowired
    public ConstructionTeamController(ConstructionTeamService constructionTeamService) {
        this.constructionTeamService = constructionTeamService;
    }

    @GetMapping("/get/construction/{id}")
    public ResponseEntity<List<ConstructionTeam>> getConstructionTeamByConstructionID(@PathVariable Integer id) {
        return new ResponseEntity<>(ConstructionTeamService.findByConstructionID(id), HttpStatus.OK);
    }

    @GetMapping("/get/constructionTeams/{constructionId}/{teamId}")
    public ResponseEntity<List<ConstructionTeam>> getConstructionTeamsByConstructionIdAndTeamId(@PathVariable Integer constructionId, @PathVariable Integer teamId) {
        List<ConstructionTeam> constructionTeams = ConstructionTeamService.findByConstructionIDAndTeamID(constructionId, teamId);
        if (constructionTeams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(constructionTeams, HttpStatus.OK);
    }
}

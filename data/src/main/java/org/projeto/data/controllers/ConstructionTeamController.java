package org.projeto.data.controllers;

import org.projeto.data.entities.ConstructionTeam;
import org.projeto.data.services.ConstructionTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/constructionTeam")
public class ConstructionTeamController {
    public final ConstructionTeamService constructionTeamService;

    @Autowired
    public ConstructionTeamController(ConstructionTeamService constructionTeamService) {
        this.constructionTeamService = constructionTeamService;
    }

    @PostMapping("/add")
    public ResponseEntity<ConstructionTeam> addConstructionTeam(@RequestBody ConstructionTeam constructionTeam) {
        return new ResponseEntity<>(ConstructionTeamService.addNew(constructionTeam), HttpStatus.CREATED);
    }

    @PutMapping("/finish/{id}")
    public ResponseEntity<ConstructionTeam> updateConstructionTeamEndDate(@PathVariable Long id) {
        ConstructionTeam existingConstructionTeam = ConstructionTeamService.findByID(id);
        if (existingConstructionTeam == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingConstructionTeam.setEndDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return new ResponseEntity<>(ConstructionTeamService.addNew(existingConstructionTeam), HttpStatus.OK);
    }

    @GetMapping("/get/construction/{id}")
    public ResponseEntity<List<ConstructionTeam>> getConstructionTeamByConstructionID(@PathVariable Integer id) {
        return new ResponseEntity<>(ConstructionTeamService.findByConstructionID(id), HttpStatus.OK);
    }

    @GetMapping("/get/construction/{constructionId}/{teamId}")
    public ResponseEntity<List<ConstructionTeam>> getConstructionTeamsByConstructionIdAndTeamId(@PathVariable Integer constructionId, @PathVariable Integer teamId) {
        List<ConstructionTeam> constructionTeams = ConstructionTeamService.findByConstructionIDAndTeamID(constructionId, teamId);
        if (constructionTeams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(constructionTeams, HttpStatus.OK);
    }

    @GetMapping("/get/team/{id}")
    public ResponseEntity<List<ConstructionTeam>> getConstructionTeamByTeamID(@PathVariable Integer id) {
        return new ResponseEntity<>(ConstructionTeamService.findByTeamID(id), HttpStatus.OK);
    }

    @GetMapping("/get/working/team/{id}")
    public ResponseEntity<ConstructionTeam> getMostRecentConstructionTeamByTeamID(@PathVariable Integer id) {
        List<ConstructionTeam> constructionTeams = ConstructionTeamService.findByTeamID(id);

        if (constructionTeams.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        // Filter the construction teams where end date is not null and sort them by end date in descending order
        ConstructionTeam mostRecentConstructionTeam = constructionTeams.stream()
                .filter(constructionTeam -> constructionTeam.getEndDate() == null)
                .findFirst()
                .orElse(null);

        if (mostRecentConstructionTeam == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(mostRecentConstructionTeam, HttpStatus.OK);
    }
}

package org.projeto.data.controllers;

import org.projeto.data.entities.Construction;
import org.projeto.data.entities.ConstructionTeam;
import org.projeto.data.entities.Project;
import org.projeto.data.services.ConstructionService;
import org.projeto.data.services.ConstructionTeamService;
import org.projeto.data.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/construction")
public class ConstructionController {
    public final ConstructionService constructionService;
    @Autowired
    public ConstructionController(ConstructionService constructionService) {
        this.constructionService = constructionService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewConstruction(@RequestBody Construction newConstruction) {
        try {
            ConstructionService.addNew(newConstruction);
            return new ResponseEntity<>("Construction added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteConstruction(@PathVariable Long id) {
        try {
            ConstructionService.delete(id);
            return new ResponseEntity<>("Construction deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/get/all")
    public ResponseEntity<List<Construction>> getAllConstructions() {
        return new ResponseEntity<>(ConstructionService.getAllConstructions(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Construction> getAllConstructions(@PathVariable Integer id) {
        return new ResponseEntity<>(ConstructionService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/get/client/{id}")
    public ResponseEntity<List<Construction>> getClientConstructions(@PathVariable Integer id) {
        List<Project> projects = ProjectService.getProjectsByClientID(id);
        if (projects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Construction> clientConstructions = new ArrayList<>();

        projects.forEach(project -> {
            clientConstructions.addAll(ConstructionService.findByProjectID(project.getId()));
        });

        return new ResponseEntity<>(clientConstructions, HttpStatus.OK);
    }

    @GetMapping("/get/project/{id}")
    public ResponseEntity<List<Construction>> getConstructionsByProjectID(@PathVariable Integer id) {
        return new ResponseEntity<>(ConstructionService.findByProjectID(id), HttpStatus.OK);
    }

    @GetMapping("/get/engineer/{id}")
    public ResponseEntity<List<Construction>> getEngineerConstructions(@PathVariable Integer id) {
        List<Project> projects = ProjectService.getProjectsByEngineerID(id);
        if (projects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Construction> engineerConstructions = new ArrayList<>();

        projects.forEach(project -> {
            engineerConstructions.addAll(ConstructionService.findByProjectID(project.getId()));
        });

        return new ResponseEntity<>(engineerConstructions, HttpStatus.OK);
    }

    @GetMapping("/get/teamConstructions/{teamId}")
    public ResponseEntity<List<Construction>> getConstructionsByTeamID(@PathVariable Integer teamId) {
        List<ConstructionTeam> constructionTeams = ConstructionTeamService.findByTeamID(teamId);
        if (constructionTeams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Construction> teamConstructions = new ArrayList<>();

        constructionTeams.forEach(constructionTeam -> {
            Construction construction = ConstructionService.findById(constructionTeam.getConstruction().getId());
            if (construction != null) {
                teamConstructions.add(construction);
            }
        });

        return new ResponseEntity<>(teamConstructions, HttpStatus.OK);
    }
}

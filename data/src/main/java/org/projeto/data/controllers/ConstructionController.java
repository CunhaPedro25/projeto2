package org.projeto.data.controllers;

import org.projeto.data.entities.Complaint;
import org.projeto.data.entities.Construction;
import org.projeto.data.entities.Project;
import org.projeto.data.services.ComplaintService;
import org.projeto.data.services.ConstructionService;
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
}

package org.projeto.data.controllers;

import org.projeto.data.entities.Project;
import org.projeto.data.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    public final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/budget")
    public ResponseEntity<List<Project>> getClientProjects(Integer ClientID) {
        // Logic to fetch data from a service, database, etc.
        List<Project> budget = projectService.getProjectsByClientID(ClientID);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }
}

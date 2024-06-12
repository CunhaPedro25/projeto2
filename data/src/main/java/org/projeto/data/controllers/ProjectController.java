package org.projeto.data.controllers;

import org.projeto.data.entities.Project;
import org.projeto.data.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    public final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/clientProjects")
    public ResponseEntity<List<Project>> getClientProjects(Integer clientID) {
        List<Project> projects = projectService.getProjectsByClientID(clientID);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
    @GetMapping("/engineerProjects")
    public ResponseEntity<List<Project>> getEngineerProjects(Integer engineerID){
        List<Project> projects  = projectService.getProjectsByEngineerID(engineerID);
        return new ResponseEntity<>(projects,HttpStatus.OK);
    }

    @GetMapping("/projectsByConstructionType")
    public ResponseEntity<List<Project>> getprojectsByConstructionTypes(Integer constructionTypeID){
        List<Project> projects  = projectService.getProjectsByConstructionType(constructionTypeID);
        return new ResponseEntity<>(projects,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addNewProject(@RequestBody Project newProject) {
        try {
            projectService.addNew(newProject);
            return new ResponseEntity<>("Project added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        try {
            projectService.delete(id);
            return new ResponseEntity<>("Project deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

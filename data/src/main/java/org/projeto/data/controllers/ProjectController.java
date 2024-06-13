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
        List<Project> projects = ProjectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/clientProjects/{id}")
    public ResponseEntity<List<Project>> getClientProjects(@PathVariable String id) {
        List<Project> projects = ProjectService.getProjectsByClientID(Integer.valueOf(id));
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
    @GetMapping("/engineerProjects/{id}")
    public ResponseEntity<List<Project>> getEngineerProjects(@PathVariable String id){
        List<Project> projects  = ProjectService.getProjectsByEngineerID(Integer.valueOf(id));
        return new ResponseEntity<>(projects,HttpStatus.OK);
    }

    @GetMapping("/projectsByConstructionType/{id}")
    public ResponseEntity<List<Project>> getprojectsByConstructionTypes(@PathVariable String id){
        List<Project> projects  = ProjectService.getProjectsByConstructionType(Integer.valueOf(id));
        return new ResponseEntity<>(projects,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addNewProject(@RequestBody Project newProject) {
        try {
            ProjectService.addNew(newProject);
            return new ResponseEntity<>("Project added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        try {
            ProjectService.delete(id);
            return new ResponseEntity<>("Project deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

package org.projeto.data.controllers;

import org.projeto.data.entities.Project;
import org.projeto.data.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    public final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Integer id) {
        Project project = ProjectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = ProjectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/get/client/{id}")
    public ResponseEntity<List<Project>> getClientProjects(@PathVariable String id) {
        List<Project> projects = ProjectService.getProjectsByClientID(Integer.valueOf(id));
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/get/engineer/{id}")
    public ResponseEntity<List<Project>> getEngineerProjects(@PathVariable String id){
        List<Project> projects  = ProjectService.getProjectsByEngineerID(Integer.valueOf(id));
        return new ResponseEntity<>(projects,HttpStatus.OK);
    }

    @GetMapping("/get/construction_type/{id}")
    public ResponseEntity<List<Project>> getProjectsByConstructionTypes(@PathVariable String id){
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

    @PutMapping("/update/requirementsState/{id}/{state}")
    public ResponseEntity<String> updateRequirementsState(@PathVariable Integer id, @PathVariable Boolean state) {
        try {
            ProjectService.updateProjectRequirementsState(id, state);
            return new ResponseEntity<>("Budget state updated successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/budgetState/{id}/{state}")
    public ResponseEntity<String> updateBudgetState(@PathVariable Integer id, @PathVariable Boolean state) {
        try {
            ProjectService.updateProjectBudgetState(id, state);
            return new ResponseEntity<>("Budget state updated successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

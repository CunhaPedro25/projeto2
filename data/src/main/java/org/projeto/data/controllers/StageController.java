package org.projeto.data.controllers;

import org.projeto.data.entities.Stage;
import org.projeto.data.services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stage")
public class StageController {
    private final StageService stageService;

    @Autowired
    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @GetMapping("/stages")
    public ResponseEntity<List<Stage>> getAllStages() {
        List<Stage> stages = stageService.getStage();
        return new ResponseEntity<>(stages, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewStage(@RequestBody Stage newStage) {
        stageService.addNew(newStage);
        return new ResponseEntity<>("Stage added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStage(@PathVariable Long id) {
        try {
            stageService.delete(id);
            return new ResponseEntity<>("Stage deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStage(@PathVariable Long id, @RequestBody String desc) {
        try {
            stageService.update(id, desc);
            return new ResponseEntity<>("Stage updated successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/constructionType/{id}")
    public ResponseEntity<List<Stage>> getStagesByConstructionType(@PathVariable Integer id) {
        List<Stage> stages = stageService.findByConstructionType(id);
        return new ResponseEntity<>(stages, HttpStatus.OK);
    }
}

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

    @Autowired
    public StageController(StageService stageService) {
    }

    @GetMapping("/stages")
    public ResponseEntity<List<Stage>> getAllStages() {
        List<Stage> stages = StageService.getStage();
        return new ResponseEntity<>(stages, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewStage(@RequestBody Stage newStage) {
        StageService.addNew(newStage);
        return new ResponseEntity<>("Stage added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStage(@PathVariable Long id) {
        try {
            StageService.delete(id);
            return new ResponseEntity<>("Stage deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStage(@PathVariable Long id, @RequestBody String desc) {
        try {
            StageService.update(id, desc);
            return new ResponseEntity<>("Stage updated successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/constructionType/{id}")
    public ResponseEntity<List<Stage>> getStagesByConstructionType(@PathVariable Integer id) {
        List<Stage> stages = StageService.findByConstructionType(id);
        return new ResponseEntity<>(stages, HttpStatus.OK);
    }
}

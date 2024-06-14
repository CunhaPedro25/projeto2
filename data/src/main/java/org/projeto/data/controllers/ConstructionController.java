package org.projeto.data.controllers;

import org.projeto.data.entities.Complaint;
import org.projeto.data.entities.Construction;
import org.projeto.data.services.ComplaintService;
import org.projeto.data.services.ConstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

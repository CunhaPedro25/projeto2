package org.projeto.data.controllers;

import org.projeto.data.entities.ConstructionType;
import org.projeto.data.services.ConstructionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/constructionType")
public class ConstructionTypeController {
    private final ConstructionTypeService constructionTypeService;

    @Autowired
    public ConstructionTypeController(ConstructionTypeService constructionTypeService) {
        this.constructionTypeService = constructionTypeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewConstructionType(@RequestBody ConstructionType newType) {
        try {
            constructionTypeService.addNew(newType);
            return new ResponseEntity<>("Construction type added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteConstructionType(@PathVariable Long id) {
        try {
            constructionTypeService.delete(id);
            return new ResponseEntity<>("Construction type deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

package org.projeto.data.controllers;

import org.projeto.data.entities.Material;
import org.projeto.data.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/material")
public class MaterialController {
    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Integer id) {
        Material material = materialService.getMaterialByID(id);
        if (material != null) {
            return new ResponseEntity<>(material, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewMaterial(@RequestBody Material newMaterial) {
        try {
            materialService.addNew(newMaterial);
            return new ResponseEntity<>("Material added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMaterial(@PathVariable Long id) {
        try {
            materialService.delete(id);
            return new ResponseEntity<>("Material deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
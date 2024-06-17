package org.projeto.data.controllers;

import org.projeto.data.entities.StockRequest;
import org.projeto.data.services.StockRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stockRequest")
public class StockRequestController {

    @Autowired
    public StockRequestController(StockRequestService stockRequestService) {
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewState(@RequestBody StockRequest newRequest) {
        try {
            StockRequestService.addNew(newRequest);
            return new ResponseEntity<>("Request added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<StockRequest>> getAll() {
        return new ResponseEntity<>(StockRequestService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get/construction/{id}")
    public ResponseEntity<List<StockRequest>> getByConstructionId(@PathVariable Integer id) {
        return new ResponseEntity<>(StockRequestService.getByConstructionId(id), HttpStatus.OK);
    }
}

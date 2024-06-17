package org.projeto.data.controllers;

import org.projeto.data.entities.State;
import org.projeto.data.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/state")
public class StateController {

    @Autowired
    public StateController(StateService stateService) {
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewState(@RequestBody State newState) {
        try {
            StateService.addNew(newState);
            return new ResponseEntity<>("State added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteState(@PathVariable Long id) {
        try {
            StateService.delete(id);
            return new ResponseEntity<>("State deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

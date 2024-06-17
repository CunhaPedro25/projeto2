package org.projeto.data.controllers;

import org.projeto.data.entities.UserType;
import org.projeto.data.services.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userType")
public class UserTypeController {

    @Autowired
    public UserTypeController(UserTypeService userTypeService) {
    }

    @GetMapping("/userTypes")
    public ResponseEntity<List<UserType>> getAllUserTypes() {
        List<UserType> userTypes = UserTypeService.getAllUserTypes();
        return new ResponseEntity<>(userTypes, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewUserType(@RequestBody UserType newUserType) {
        try {
            UserTypeService.addNew(newUserType);
            return new ResponseEntity<>("User type added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserType(@PathVariable Long id) {
        try {
            UserTypeService.delete(id);
            return new ResponseEntity<>("User type deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserType(@PathVariable Long id, @RequestBody String type) {
        try {
            UserTypeService.update(id, type);
            return new ResponseEntity<>("User type updated successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

package org.projeto.data.controllers;

import org.projeto.data.entities.Zipcode;
import org.projeto.data.services.ZipcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zipcode")
public class ZipcodeController {

    @Autowired
    public ZipcodeController(ZipcodeService zipcodeService) {
    }

    @GetMapping("/zipcodes")
    public ResponseEntity<List<Zipcode>> getAllZipcodes() {
        List<Zipcode> zipcodes = ZipcodeService.getZipcodes();
        return new ResponseEntity<>(zipcodes, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewZipcode(@RequestBody Zipcode newZipcode) {
        try {
            ZipcodeService.addNew(newZipcode);
            return new ResponseEntity<>("Zipcode added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteZipcode(@PathVariable Long id) {
        try {
            ZipcodeService.delete(id);
            return new ResponseEntity<>("Zipcode deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateZipcode(@PathVariable Long id, @RequestBody Zipcode updatedZipcode) {
        try {
            ZipcodeService.update(id, updatedZipcode.getDistrict(), updatedZipcode.getCity(), updatedZipcode.getLocale());
            return new ResponseEntity<>("Zipcode updated successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

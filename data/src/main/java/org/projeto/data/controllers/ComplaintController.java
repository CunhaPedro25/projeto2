package org.projeto.data.controllers;

import org.projeto.data.entities.Complaint;
import org.projeto.data.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {
    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }
//    @GetMapping ("/complaintsByID/{id}")
//    public Optional<Complaint> getComplaintByID(@PathVariable Long id){
//        return complaintService.findByID(id);
//
//    }
//    @GetMapping("/complaintsByClient/{id}")
//    public ResponseEntity<List<Complaint>> getComplaintsByClientID(@PathVariable Long id) {
//        List<Complaint> complaints = complaintService.findComplaintsByClientID(id);
//        return new ResponseEntity<>(complaints, HttpStatus.OK);
//    }
    @GetMapping("/complaintsByConstruction")
    public ResponseEntity<List<Complaint>> getComplaintByConstructionID(Integer clientID){
        List<Complaint> complaints  = complaintService.findComplaintByConstructionID(clientID);
        return new ResponseEntity<>(complaints,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewComplaint(@RequestBody Complaint newComplaint) {
        try {
            complaintService.addNew(newComplaint);
            return new ResponseEntity<>("Complaint added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long id) {
        try {
            complaintService.delete(id);
            return new ResponseEntity<>("Complaint deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

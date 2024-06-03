package org.projeto.data.controllers;

import org.projeto.data.entities.Complaint;
import org.projeto.data.entities.Project;
import org.projeto.data.services.ComplaintService;
import org.projeto.data.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {
    public final ComplaintService complaintService;
    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }
    @GetMapping("/complaintsByClient")
    public ResponseEntity<List<Complaint>> getComplaintsByClientID(Integer clientID){
        List<Complaint> complaints  = complaintService.findComplaintsByClientID(clientID);
        return new ResponseEntity<>(complaints,HttpStatus.OK);
    }
    @GetMapping("/complaintsByConstruction")
    public ResponseEntity<List<Complaint>> getComplaintByConstructionID(Integer clientID){
        List<Complaint> complaints  = complaintService.findComplaintByConstructionID(clientID);
        return new ResponseEntity<>(complaints,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewComplaint(@RequestBody Complaint newComplaint) {
        try {
            complaintService.addNew(newComplaint);
            return new ResponseEntity<>("Project added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long id) {
        try {
            complaintService.delete(id);
            return new ResponseEntity<>("Project deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

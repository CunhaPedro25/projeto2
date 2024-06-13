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

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
    }

    @GetMapping ("/complaintsByID/{id}")
    public Optional<Complaint> getComplaintByID(@PathVariable Long id){
        return ComplaintService.findByID(id);

    }
    @GetMapping("/complaintsByClientID/{id}")
    public ResponseEntity<List<Complaint>> getComplaintsByClientID(@PathVariable Long id) {
        List<Complaint> complaints = ComplaintService.findComplaintsByClientID(id);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @GetMapping("/complaintsByConstructionID/{id}")
    public ResponseEntity<List<Complaint>> getComplaintByConstructionID(@PathVariable String id){
        List<Complaint> complaints  = ComplaintService.findComplaintByConstructionID(Integer.valueOf(id));
        return new ResponseEntity<>(complaints,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewComplaint(@RequestBody Complaint newComplaint) {
        try {
            ComplaintService.addNew(newComplaint);
            return new ResponseEntity<>("Complaint added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long id) {
        try {
            ComplaintService.delete(id);
            return new ResponseEntity<>("Complaint deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

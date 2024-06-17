package org.projeto.data.controllers;

import org.projeto.data.entities.Invoice;
import org.projeto.data.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = InvoiceService.getInvoice();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewInvoice(@RequestBody Invoice newInvoice) {
        InvoiceService.addNew(newInvoice);
        return new ResponseEntity<>("Invoice added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Long id) {
        try {
            InvoiceService.delete(id);
            return new ResponseEntity<>("Invoice deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/stage/{stageId}")
    public ResponseEntity<Optional<Invoice>> getInvoiceByStageId(@PathVariable Integer stageId) {
        Optional<Invoice> invoice = InvoiceService.findByStageId(stageId);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @GetMapping("/issueDate")
    public ResponseEntity<List<Invoice>> getInvoicesByIssueDate(@RequestParam LocalDate issueDate) {
        List<Invoice> invoices = InvoiceService.findInvoicesByIssueDate(issueDate);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/get/client/{clientId}")
    public ResponseEntity<List<Invoice>> getInvoicesByClientId(@PathVariable Integer clientId) {
        List<Invoice> invoices = InvoiceService.findInvoicesByClientId(clientId);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PutMapping("/pay/{id}")
    public ResponseEntity<String> payInvoice(@PathVariable Long id) {
        try {
            InvoiceService.payInvoice(id);
            return new ResponseEntity<>("Invoice paid successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

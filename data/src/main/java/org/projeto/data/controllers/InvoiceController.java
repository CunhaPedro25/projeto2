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
@RequestMapping("/api/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getInvoice();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewInvoice(@RequestBody Invoice newInvoice) {
        invoiceService.addNew(newInvoice);
        return new ResponseEntity<>("Invoice added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Long id) {
        try {
            invoiceService.delete(id);
            return new ResponseEntity<>("Invoice deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/stage/{stageId}")
    public ResponseEntity<Optional<Invoice>> getInvoiceByStageId(@PathVariable Integer stageId) {
        Optional<Invoice> invoice = invoiceService.findByStageId(stageId);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @GetMapping("/issueDate")
    public ResponseEntity<List<Invoice>> getInvoicesByIssueDate(@RequestParam LocalDate issueDate) {
        List<Invoice> invoices = invoiceService.findInvoicesByIssueDate(issueDate);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Invoice>> getInvoicesByClientId(@PathVariable Integer clientId) {
        List<Invoice> invoices = invoiceService.findInvoicesByClientId(clientId);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
}

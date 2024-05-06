package org.projeto.data.controllers;

import org.projeto.data.entities.Budget;
import org.projeto.data.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BudgetController {
    public final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/budget")
    public ResponseEntity<List<Budget>> getBudget() {
        // Logic to fetch data from a service, database, etc.
        List<Budget> budget = budgetService.getBudget();
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }
}

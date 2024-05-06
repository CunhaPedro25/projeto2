package org.projeto.data.services;

import org.projeto.data.entities.Budget;
import org.projeto.data.repositories.documents.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
  public final BudgetRepository budgetRepository;

  @Autowired
  public BudgetService(BudgetRepository budgetRepository) {
    this.budgetRepository = budgetRepository;
  }

  public List<Budget> getBudget(){return budgetRepository.findAll();}

  public List<Budget> getAllBudgets(){
    return budgetRepository.findAll();
  }

  public void addNew(Budget newBudget){
    Optional<Budget> existingBudget = budgetRepository.findByProject_IdAndCreateDate(newBudget.getProject().getId(), newBudget.getCreateDate());
    Optional<Budget> exisingFile = budgetRepository.findByFilePath(newBudget.getFilePath());
    if(existingBudget.isPresent() || exisingFile.isPresent()){
      throw new IllegalStateException("Budget already exists");
    }else {
      budgetRepository.save(newBudget);
    }
  }


  public void delete(Long id){
    boolean exists = budgetRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("Budget with id"+id+"does not exist");
    }else{
      budgetRepository.deleteById(id);
    }
  }
}

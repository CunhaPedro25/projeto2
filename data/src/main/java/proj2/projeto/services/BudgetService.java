package proj2.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.entities.Budget;
import proj2.projeto.repositories.documents.BudgetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
  private final BudgetRepository budgetRepository;
  @Autowired
  public BudgetService(BudgetRepository budgetRepository){ this.budgetRepository = budgetRepository;}
  public List<Budget> getBudget(){return budgetRepository.findAll();}
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

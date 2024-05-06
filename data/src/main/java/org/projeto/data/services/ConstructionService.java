package org.projeto.data.services;

import org.projeto.data.entities.Budget;
import org.projeto.data.repositories.documents.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.projeto.data.entities.Construction;
import org.projeto.data.repositories.ConstructionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConstructionService {
  private final ConstructionRepository constructionRepository;
  private final BudgetRepository budgetRepository;

  @Autowired
  public ConstructionService(ConstructionRepository constructionRepository, BudgetRepository budgetRepository) {
    this.constructionRepository = constructionRepository;
    this.budgetRepository = budgetRepository;
  }

  public List<Construction> getConstructions(){ return this.constructionRepository.findAll();}

//  public List<Construction> getConstructionsByClientID(Integer clientID){
//    List<Budget> clientBudgets = this.budgetRepository.findBudgetByClient_id(clientID);
//    System.out.println(clientBudgets);
//    List<Construction> clientConstructions = new ArrayList<>();
//
//    for (Budget budget : clientBudgets) {
//      clientConstructions.add(this.constructionRepository.findByBudget_Id(budget.getId()));
//    }
//    return clientConstructions;
//  }

  public void addNew(Construction newConstruction){
    if (!newConstruction.getTeam().getBusy()){
      throw new IllegalStateException("The Equipa associated with this Construction has currently another Construction assigned");
    }
    this.constructionRepository.save(newConstruction);
  }
  public void delete(Long id){
    boolean exists = this.constructionRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("Construction with id"+id+"does not exist");
    }else{
      this.constructionRepository.deleteById(id);
    }
  }

//  @Transactional
//  public void update(Long id, Stage stage){
//    Construction construction = constructionRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Construction with id "+ id + " does not exist! "));
//
//  }
}

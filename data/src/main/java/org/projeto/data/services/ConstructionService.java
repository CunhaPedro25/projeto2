package org.projeto.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.projeto.data.entities.Construction;
import org.projeto.data.repositories.ConstructionRepository;

import java.util.List;

@Service
public class ConstructionService {
  @Autowired
  private static ConstructionRepository constructionRepository;

  public static List<Construction> getConstructions(){ return constructionRepository.findAll();}

  public static void addNew(Construction newConstruction){
    if (!newConstruction.getTeam().getBusy()){
      throw new IllegalStateException("The Equipa associated with this Construction has currently another Construction assigned");
    }
    constructionRepository.save(newConstruction);
  }
  public static void delete(Long id){
    boolean exists = constructionRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("Construction with id"+id+"does not exist");
    }else{
      constructionRepository.deleteById(id);
    }
  }

//  @Transactional
//  public void update(Long id, Stage stage){
//    Construction construction = constructionRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Construction with id "+ id + " does not exist! "));
//
//  }
}

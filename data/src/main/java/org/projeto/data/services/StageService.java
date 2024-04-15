package org.projeto.data.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.projeto.data.entities.Stage;
import org.projeto.data.repositories.StageRepository;

import java.util.List;
import java.util.Objects;

@Service
public class StageService {
  @Autowired
  private static StageRepository stageRepository;
  public static List<Stage> getEtapas(){ return stageRepository.findAll();}

  public static void addNew(Stage newEtapa){
    stageRepository.save(newEtapa);
  }
  public static void delete(Long id){
    boolean exists = stageRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("Etapa with id"+id+"does not exist");
    }else{
      stageRepository.deleteById(id);
    }
  }
  public static void update(Long id, String desc){
    Stage stage = stageRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Etapa with id "+ id + " does not exist! "));

    if (desc != null && !desc.isEmpty() && !Objects.equals(stage.getDescription(), desc)){
      stage.setDescription(desc);
    }
  }
}

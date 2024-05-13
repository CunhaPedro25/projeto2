package org.projeto.data.services;

import org.projeto.data.entities.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.projeto.data.repositories.StageRepository;

import java.util.List;
import java.util.Objects;

@Service
public class StageService {
  private final StageRepository stageRepository;

  @Autowired
  public StageService(StageRepository stageRepository) {
    this.stageRepository = stageRepository;

  }

  public List<Stage> getStage(){ return this.stageRepository.findAll();}

  public void addNew(Stage newStage){
    this.stageRepository.save(newStage);
  }
  public void delete(Long id){
    boolean exists = this.stageRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("Stage with id"+id+"does not exist");
    }else{
      this.stageRepository.deleteById(id);
    }
  }
  public void update(Long id, String desc){
    Stage stage = this.stageRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Stage with id "+ id + " does not exist! "));

    if (desc != null && !desc.isEmpty() && !Objects.equals(stage.getName(), desc)){
      stage.setName(desc);
    }
  }
}

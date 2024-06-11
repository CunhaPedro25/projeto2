  package org.projeto.data.services;

  import org.projeto.data.entities.Stage;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Service;
  import org.projeto.data.repositories.StageRepository;

  import java.util.List;
  import java.util.Objects;

  @Service
  public class StageService {
    private static StageRepository stageRepository;

    @Autowired
    public StageService(StageRepository stageRepository) {
      StageService.stageRepository = stageRepository;
    }

    public List<Stage> getStage(){ return StageService.stageRepository.findAll();}

    public void addNew(Stage newStage){
      StageService.stageRepository.save(newStage);
    }
    public void delete(Long id){
      boolean exists = StageService.stageRepository.existsById(id);
      if (!exists){
        throw new IllegalStateException("Stage with id"+id+"does not exist");
      }else{
        StageService.stageRepository.deleteById(id);
      }
    }
    public void update(Long id, String desc){
      Stage stage = StageService.stageRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Stage with id "+ id + " does not exist! "));

      if (desc != null && !desc.isEmpty() && !Objects.equals(stage.getName(), desc)){
        stage.setName(desc);
      }
    }
    public List<Stage> findByConstructionType(Integer id){
      return StageService.stageRepository.findByConstructionType_Id(id);
    }
  }

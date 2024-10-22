  package org.projeto.data.services;

  import org.projeto.data.entities.Stage;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Service;
  import org.projeto.data.repositories.StageRepository;

  import java.util.ArrayList;
  import java.util.List;
  import java.util.Objects;

  @Service
  public class StageService {
    private static StageRepository stageRepository;

    @Autowired
    public StageService(StageRepository stageRepository) {
      StageService.stageRepository = stageRepository;
    }

    public static List<Stage> getStage(){ return StageService.stageRepository.findAll();}

    public static void addNew(Stage newStage){
      StageService.stageRepository.save(newStage);
    }
    public static void delete(Long id){
      boolean exists = StageService.stageRepository.existsById(id);
      if (!exists){
        throw new IllegalStateException("Stage with id"+id+"does not exist");
      }else{
        StageService.stageRepository.deleteById(id);
      }
    }
    public static void update(Long id, String desc){
      Stage stage = StageService.stageRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Stage with id "+ id + " does not exist! "));

      if (desc != null && !desc.isEmpty() && !Objects.equals(stage.getName(), desc)){
        stage.setName(desc);
      }
    }
    public static List<Stage> findByConstructionType(Integer id){
      return StageService.stageRepository.findByConstructionType_Id(id);
    }
    public static List<String> getAllStageNames(){
        List<Stage> stages = StageService.stageRepository.findAll();
        List<String> stageNames = new ArrayList<>();
        for (Stage stage : stages){
            stageNames.add(stage.getName());
        }
        return stageNames;
    }

    public static Stage getStageByName(String name) {
        return StageService.stageRepository.findByName(name);
    }
  }

package proj2.projeto.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.entities.Stage;
import proj2.projeto.repositories.StageRepository;

import java.util.List;
import java.util.Objects;

@Service
public class StageService {
  private final StageRepository stageRepository;
  @Autowired
  public StageService(StageRepository stageRepository){ this.stageRepository = stageRepository;}
  public List<Stage> getEtapas(){ return stageRepository.findAll();}

  public void addNew(Stage newEtapa){
    stageRepository.save(newEtapa);
  }
  public void delete(Long id){
    boolean exists = stageRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("Etapa with id"+id+"does not exist");
    }else{
      stageRepository.deleteById(id);
    }
  }
  @Transactional
  public void update(Long id, String desc){
    Stage stage = stageRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Etapa with id "+ id + " does not exist! "));

    if (desc != null && !desc.isEmpty() && !Objects.equals(stage.getDescription(), desc)){
      stage.setDescription(desc);
    }
  }
}

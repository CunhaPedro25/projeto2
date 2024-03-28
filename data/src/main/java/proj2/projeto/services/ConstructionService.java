package proj2.projeto.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.entities.Construction;
import proj2.projeto.entities.Stage;
import proj2.projeto.repositories.ConstructionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ConstructionService {
  private final ConstructionRepository constructionRepository;
  @Autowired
  public ConstructionService(ConstructionRepository constructionRepository){ this.constructionRepository = constructionRepository;}
  public List<Construction> getConstructions(){ return constructionRepository.findAll();}

  public void addNew(Construction newConstruction){
    if (!newConstruction.getTeam().getBusy()){
      throw new IllegalStateException("The Equipa associated with this Construction has currently another Construction assigned");
    }
    constructionRepository.save(newConstruction);
  }
  public void delete(Long ConstructionId){
    boolean exists = constructionRepository.existsById(ConstructionId);
    if (!exists){
      throw new IllegalStateException("Construction with id"+ConstructionId+"does not exist");
    }else{
      constructionRepository.deleteById(ConstructionId);
    }
  }

//  @Transactional
//  public void update(Long id, Stage stage){
//    Construction Construction = constructionRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Construction with id "+ id + " does not exist! "));
//
//  }
}

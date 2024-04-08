package proj2.projeto.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.data.entities.Construction;
import proj2.projeto.data.repositories.ConstructionRepository;

import java.util.List;

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
  public void delete(Long id){
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

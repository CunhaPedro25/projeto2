package proj2.projeto.entities.Obra;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.entities.Obra.Obra;
import proj2.projeto.entities.Obra.ObraRepository;
import proj2.projeto.entities.Obra.Orcamento.Etapa;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ObraService {
  private final proj2.projeto.entities.Obra.ObraRepository ObraRepository;
  @Autowired
  public ObraService(ObraRepository ObraRepository){ this.ObraRepository = ObraRepository;}
  public List<Obra> getObras(){ return ObraRepository.findAll();}

  public void addNew(Obra newObra){
    Optional<Obra> EquipaBusy = ObraRepository.findByEquipa(newObra.getEquipa());
    if (EquipaBusy.isPresent()){
      throw new IllegalStateException("The Equipa associated with this Obra has currently another Obra assigned");
    }
    ObraRepository.save(newObra);
  }
  public void delete(Long ObraId){
    boolean exists = ObraRepository.existsById(ObraId);
    if (!exists){
      throw new IllegalStateException("Obra with id"+ObraId+"does not exist");
    }else{
      ObraRepository.deleteById(ObraId);
    }
  }
  /*
  @Transactional
  public void update(Long id, Etapa etapa){
    Obra Obra = ObraRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Obra with id "+ id + " does not exist! "));

    if (etapa != null && !Objects.equals(Obra.getEtapa().,etapa)) {
      Optional<Obra> ObraOptional = ObraRepository.findByDistrito(distrito);
      if (ObraOptional.isPresent()){
        throw new IllegalStateException("distrito taken");
      }else {
        Obra.setDistrito(distrito);
      }
    }
  }

   */
}

package proj2.projeto.entities.Obra.Orcamento;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EtapaService {
  private final EtapaRepository EtapaRepository;
  @Autowired
  public EtapaService(EtapaRepository EtapaRepository){ this.EtapaRepository = EtapaRepository;}
  public List<Etapa> getEtapas(){ return EtapaRepository.findAll();}

  public void addNew(Etapa newEtapa){
    /*
    Optional<Etapa> EtapaByOrcamento = EtapaRepository.findByOrcamento_Id(newEtapa.getOrcamento().getId());
    if (EtapaByOrcamento.isPresent()){
      throw new IllegalStateException("A etapa que esta a tentar inserir");
    }
     */
    EtapaRepository.save(newEtapa);
  }
  public void delete(Long EtapaId){
    boolean exists = EtapaRepository.existsById(EtapaId);
    if (!exists){
      throw new IllegalStateException("Etapa with id"+EtapaId+"does not exist");
    }else{
      EtapaRepository.deleteById(EtapaId);
    }
  }
  @Transactional
  public void update(Long id,String desc){
    Etapa Etapa = EtapaRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Etapa with id "+ id + " does not exist! "));

    if (desc != null && !desc.isEmpty() && !Objects.equals(Etapa.getDescricao(), desc)){
      Etapa.setDescricao(desc);
    }
  }

}

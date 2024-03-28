package proj2.projeto.entities.Obra.Orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class OrcamentoService {
  private final OrcamentoRepository OrcamentoRepository;
  @Autowired
  public OrcamentoService(OrcamentoRepository OrcamentoRepository){ this.OrcamentoRepository = OrcamentoRepository;}
  public List<Orcamento> getOrcamentos(){return OrcamentoRepository.findAll();}
  public void addNew(Orcamento newOrcamento){

    OrcamentoRepository.save(newOrcamento);
  }
  public void delete(Long OrcamentoId){
    boolean exists = OrcamentoRepository.existsById(OrcamentoId);
    if (!exists){
      throw new IllegalStateException("Orcamento with id"+OrcamentoId+"does not exist");
    }else{
      OrcamentoRepository.deleteById(OrcamentoId);
    }
  }
}

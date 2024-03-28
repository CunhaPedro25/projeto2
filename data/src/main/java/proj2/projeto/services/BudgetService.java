package proj2.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import proj2.projeto.entities.Obra.Orcamento.Orcamento;
import proj2.projeto.entities.Obra.Orcamento.OrcamentoRepository;

import java.util.List;

public class BudgetService {
  private final proj2.projeto.entities.Obra.Orcamento.OrcamentoRepository OrcamentoRepository;
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

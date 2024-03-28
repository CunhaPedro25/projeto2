package proj2.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import proj2.projeto.entities.Obra.Orcamento.Fatura;
import proj2.projeto.entities.Obra.Orcamento.FaturaRepository;

import java.util.List;

public class InvoiceService {
  private final FaturaRepository faturaRepository;
  @Autowired
  public FaturaService(FaturaRepository faturaRepository){ this.faturaRepository = faturaRepository;}
  public List<Fatura> getFaturas(){ return faturaRepository.findAll();}
  //TODO Develop the constraints for saving a new "Fatura"
  public void addNew(Fatura newFatura){
    faturaRepository.save(newFatura);
  }
  public void delete(Long FaturaId){
    boolean exists = faturaRepository.existsById(FaturaId);
    if (!exists){
      throw new IllegalStateException("The Fatura with id"+FaturaId+"does not exist");
    }else{
      faturaRepository.deleteById(FaturaId);
    }
  }
  //TODO Ask how would update work
}

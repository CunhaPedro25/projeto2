package proj2.projeto.entities.Obra.Orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaturaService {
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

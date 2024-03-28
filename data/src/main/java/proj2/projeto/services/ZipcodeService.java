package proj2.projeto.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import proj2.projeto.entities.CodPostal.CodPostal;
import proj2.projeto.entities.CodPostal.CodPostalRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ZipcodeService {
  private final proj2.projeto.entities.CodPostal.CodPostalRepository CodPostalRepository;
  @Autowired
  public CodPostalService(CodPostalRepository CodPostalRepository){ this.CodPostalRepository = CodPostalRepository;}
  public List<CodPostal> getCodPostals(){ return CodPostalRepository.findAll();}

  public void addNew(CodPostal newCodPostal){
    Optional<CodPostal> CodPostalByDistrito = CodPostalRepository.findByDistrito(newCodPostal.getDistrito());
    if (CodPostalByDistrito.isPresent()){
      throw new IllegalStateException("distrito taken");
    }
    CodPostalRepository.save(newCodPostal);
  }
  public void delete(Long CodPostalId){
    boolean exists = CodPostalRepository.existsById(CodPostalId);
    if (!exists){
      throw new IllegalStateException("CodPostal with id"+CodPostalId+"does not exist");
    }else{
      CodPostalRepository.deleteById(CodPostalId);
    }
  }
  @Transactional
  public void update(Long id,String distrito){
    CodPostal CodPostal = CodPostalRepository.findById(id).orElseThrow(()-> new IllegalStateException( "CodPostal with id "+ id + " does not exist! "));

    if (distrito != null && !distrito.isEmpty() && !Objects.equals(CodPostal.getDistrito(),distrito)) {
      Optional<CodPostal> CodPostalOptional = CodPostalRepository.findByDistrito(distrito);
      if (CodPostalOptional.isPresent()){
        throw new IllegalStateException("distrito taken");
      }else {
        CodPostal.setDistrito(distrito);
      }
    }
  }
}

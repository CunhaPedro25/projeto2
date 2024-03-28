package proj2.projeto.entities.Equipa.Obreiro;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ObreiroService {
  private final ObreiroRepository ObreiroRepository;
  @Autowired
  public ObreiroService(ObreiroRepository ObreiroRepository){ this.ObreiroRepository = ObreiroRepository;}
  public List<Obreiro> getObreiros(){return ObreiroRepository.findAll();}
  public void addNew(Obreiro newObreiro){
    Optional<Obreiro> ObreiroByEmail = ObreiroRepository.findByEmail(newObreiro.getEmail());
    Optional<Obreiro> ObreiroByTelemovel = ObreiroRepository.findByTelemovel(newObreiro.getTelemovel());

    if (ObreiroByEmail.isPresent()){
      throw new IllegalStateException("email taken");
    }
    if (ObreiroByTelemovel.isPresent()){
      throw new IllegalStateException("Telemovel number taken");
    }
    ObreiroRepository.save(newObreiro);
  }
  public void delete(Long ObreiroId){
    boolean exists = ObreiroRepository.existsById(ObreiroId);
    if (!exists){
      throw new IllegalStateException("Obreiro with id"+ObreiroId+"does not exist");
    }else{
      ObreiroRepository.deleteById(ObreiroId);
    }
  }
  @Transactional
  public void update(Long id,String name, String email,Integer telemovel){
    Obreiro Obreiro = ObreiroRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Obreiro with id "+ id + " does not exist! "));

    if (name != null && !name.isEmpty() && !Objects.equals(Obreiro.getNome(), name)){
      Obreiro.setNome(name);
    }
    if (telemovel != null && !Objects.equals(Obreiro.getTelemovel(), telemovel)) {
      Optional<Obreiro> ObreiroOptional = ObreiroRepository.findByTelemovel(telemovel);
      if (ObreiroOptional.isPresent()) {
        throw new IllegalStateException("this telemovel is taken");
      } else {
        Obreiro.setTelemovel(telemovel);
      }
    }
    if (email != null && !email.isEmpty() && !Objects.equals(Obreiro.getEmail(),email)) {
      Optional<Obreiro> ObreiroOptional = ObreiroRepository.findByEmail(email);
      if (ObreiroOptional.isPresent()){
        throw new IllegalStateException("email taken");
      }else {
        Obreiro.setEmail(email);
      }
    }
  }
}

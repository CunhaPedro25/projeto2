package proj2.projeto.entities.Engenheiro;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.entities.Engenheiro.Engenheiro;
import proj2.projeto.entities.Engenheiro.Engenheiro;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EngenheiroService {
  private final EngenheiroRepository engenheiroRepository;
  @Autowired
  public EngenheiroService(EngenheiroRepository engenheiroRepository){ this.engenheiroRepository = engenheiroRepository;}
  public List<Engenheiro> getEngenheiros(){return engenheiroRepository.findAll();}
  public void addNew(Engenheiro newEngenheiro){
    Optional<Engenheiro> EngenheiroByEmail = engenheiroRepository.findByEmail(newEngenheiro.getEmail());
    Optional<Engenheiro> EngenheiroByTelemovel = engenheiroRepository.findByTelemovel(newEngenheiro.getTelemovel());

    if (EngenheiroByEmail.isPresent()){
      throw new IllegalStateException("email taken");
    }
    if (EngenheiroByTelemovel.isPresent()){
      throw new IllegalStateException("Telemovel number taken");
    }
    engenheiroRepository.save(newEngenheiro);
  }
  public void delete(Long EngenheiroId){
    boolean exists = engenheiroRepository.existsById(EngenheiroId);
    if (!exists){
      throw new IllegalStateException("Engenheiro with id"+EngenheiroId+"does not exist");
    }else{
      engenheiroRepository.deleteById(EngenheiroId);
    }
  }
  @Transactional
  public void update(Long id,String name, String email,Integer telemovel){
    Engenheiro Engenheiro = engenheiroRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Engenheiro with id "+ id + " does not exist! "));

    if (name != null && !name.isEmpty() && !Objects.equals(Engenheiro.getNome(), name)){
      Engenheiro.setNome(name);
    }
    if (telemovel != null && !Objects.equals(Engenheiro.getTelemovel(), telemovel)){
      Engenheiro.setTelemovel(telemovel);
    }
    if (email != null && !email.isEmpty() && !Objects.equals(Engenheiro.getEmail(),email)) {
      Optional<Engenheiro> EngenheiroOptional = engenheiroRepository.findByEmail(email);
      if (EngenheiroOptional.isPresent()){
        throw new IllegalStateException("email taken");
      }else {
        Engenheiro.setEmail(email);
      }
    }
  }
}

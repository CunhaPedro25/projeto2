package proj2.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import proj2.projeto.entities.Equipa.Equipa;
import proj2.projeto.entities.Equipa.EquipaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TeamService {
  private final EquipaRepository equipaRepository;
  @Autowired
  public EquipaService(EquipaRepository equipaRepository){this.equipaRepository = equipaRepository;}
  public List<Equipa> getEquipas(){ return equipaRepository.findAll();}
  public void addNew(Equipa newEquipa){
    Optional<Equipa> EquipaByChefe = equipaRepository.findByChefe_Id(newEquipa.getChefe().getId());
    if (EquipaByChefe.isEmpty()){
      throw new IllegalStateException("This Equipa has no Chefe!");
    }
    equipaRepository.save(newEquipa);
  }
  public void delete(Long EquipaId){
    boolean exists = equipaRepository.existsById(EquipaId);
    if (!exists){
      throw new IllegalStateException("Equipa with id"+EquipaId+"does not exist");
    }else{
      equipaRepository.deleteById(EquipaId);
    }
  }
  @Transactional
  public void update(Long id, Obreiro chefe){
    Equipa Equipa = equipaRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Equipa with id "+ id + " does not exist! "));

    if (chefe != null && chefe.getId() != null && !Objects.equals(Equipa.getChefe(),chefe)) {
      Optional<Equipa> EquipaOptional = equipaRepository.findByChefe_Id(chefe.getId());
      if (EquipaOptional.isPresent()){
        throw new IllegalStateException("This team already has that Chefe already exists!");
      }else {
        Equipa.setChefe(chefe);
      }
    }
  }
}

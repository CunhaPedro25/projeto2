package proj2.projeto.entities.Obra.Estado;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.entities.Estado.Estado;
import proj2.projeto.entities.Estado.EstadoRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EstadoService {
  private final EstadoRepository EstadoRepository;
  @Autowired
  public EstadoService(EstadoRepository EstadoRepository){ this.EstadoRepository = EstadoRepository;}
  public List<Estado> getEstados(){ return EstadoRepository.findAll();}

  public void addNew(Estado newEstado){
    Optional<Estado> EstadoByDescricao = EstadoRepository.findByDescricao(newEstado.getDescricao());
    if (EstadoByDescricao.isPresent()){
      throw new IllegalStateException("The Estado you are trying to add already exists");
    }
    EstadoRepository.save(newEstado);
  }
  public void delete(Long EstadoId){
    boolean exists = EstadoRepository.existsById(EstadoId);
    if (!exists){
      throw new IllegalStateException("Estado with id"+EstadoId+"does not exist");
    }else{
      EstadoRepository.deleteById(EstadoId);
    }
  }
  @Transactional
  public void update(Long id,String desc){
    Estado Estado = EstadoRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Estado with id "+ id + " does not exist! "));

    if (desc != null && !desc.isEmpty() && !Objects.equals(Estado.getDescricao(),desc)) {
      Optional<Estado> EstadoOptional = EstadoRepository.findByDescricao(desc);
      if (EstadoOptional.isPresent()){
        throw new IllegalStateException("That Estado alrady exists");
      }else {
        Estado.setDescricao(desc);
      }
    }
  }
}

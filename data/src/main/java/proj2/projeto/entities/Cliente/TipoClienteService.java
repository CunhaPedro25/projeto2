package proj2.projeto.entities.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TipoClienteService {
  private final TipoClienteRepository TipoClienteRepository;
  @Autowired
  public TipoClienteService(TipoClienteRepository TipoClienteRepository){ this.TipoClienteRepository = TipoClienteRepository;}
  public List<TipoCliente> getTipoClientes(){ return TipoClienteRepository.findAll();}
  public void addNew(TipoCliente newTipoCliente){
    Optional<TipoCliente> TipoClienteByTipo = TipoClienteRepository.findByTipo(newTipoCliente.getTipo());
    if (TipoClienteByTipo.isPresent()){
      throw new IllegalStateException("email taken");
    }
    TipoClienteRepository.save(newTipoCliente);
  }
  public void delete(Long TipoClienteId){
    boolean exists = TipoClienteRepository.existsById(TipoClienteId);
    if (!exists){
      throw new IllegalStateException("TipoCliente with id"+TipoClienteId+"does not exist");
    }else{
      TipoClienteRepository.deleteById(TipoClienteId);
    }
  }
  @Transactional
  public void update(Long id,String tipo){
    TipoCliente TipoCliente = TipoClienteRepository.findById(id).orElseThrow(()-> new IllegalStateException( "TipoCliente with id "+ id + " does not exist! "));

    if (tipo != null && !tipo.isEmpty() && !Objects.equals(TipoCliente.getTipo(),tipo)) {
      Optional<TipoCliente> TipoClienteOptional = TipoClienteRepository.findByTipo(tipo);
      if (TipoClienteOptional.isPresent()){
        throw new IllegalStateException("Customer type already exists!");
      }else {
        TipoCliente.setTipo(tipo);
      }
    }
  }
}

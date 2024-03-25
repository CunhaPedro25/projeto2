package proj2.projeto.entities.Cliente;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {
  private final ClienteRepository clienteRepository;
  @Autowired
  public ClienteService(ClienteRepository clienteRepository){ this.clienteRepository = clienteRepository;}
  public List<Cliente> getClientes(){ return clienteRepository.findAll();}

  public void addNew(Cliente newCliente){
    Optional<Cliente> clienteByEmail = clienteRepository.findByEmail(newCliente.getEmail());
    if (clienteByEmail.isPresent()){
      throw new IllegalStateException("email taken");
    }
    clienteRepository.save(newCliente);
  }
  public void delete(Long clienteId){
    boolean exists = clienteRepository.existsById(clienteId);
    if (!exists){
      throw new IllegalStateException("cliente with id"+clienteId+"does not exist");
    }else{
      clienteRepository.deleteById(clienteId);
    }
  }
  @Transactional
  public void update(Long id,String name, String email,String password){
    Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new IllegalStateException( "cliente with id "+ id + " does not exist! "));

    if (name != null && !name.isEmpty() && !Objects.equals(cliente.getNome(), name)){
      cliente.setNome(name);
    }
    if (password != null && !password.isEmpty() && !Objects.equals(cliente.getPassword(), password)){
      cliente.setPassword(password);
    }
    if (email != null && !email.isEmpty() && !Objects.equals(cliente.getEmail(),email)) {
      Optional<Cliente> clienteOptional = clienteRepository.findByEmail(email);
      if (clienteOptional.isPresent()){
        throw new IllegalStateException("email taken");
      }else {
        cliente.setEmail(email);
      }
    }
  }
}

package org.projeto.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.projeto.data.entities.enums.ClientType;
import org.projeto.data.repositories.enums.ClientTypeRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientTypeService {
  private final ClientTypeRepository clientTypeRepository;

  @Autowired
  public ClientTypeService(ClientTypeRepository clientTypeRepository){
    this.clientTypeRepository = clientTypeRepository;
  }

  public List<ClientType> getAllClientTypes(){ return clientTypeRepository.findAll();}

  public void addNew(ClientType newClientType){
    Optional<ClientType> ClientType = this.clientTypeRepository.findByType(newClientType.getType());
    if (ClientType.isPresent()){
      throw new IllegalStateException("tipo de cliente already exists");
    }
    this.clientTypeRepository.save(newClientType);
  }

  public void delete(Long id){
    boolean exists = this.clientTypeRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("TipoCliente with id"+id+"does not exist");
    }else{
      this.clientTypeRepository.deleteById(id);
    }
  }

  public void update(Long id, String type){
    ClientType clientType = this.clientTypeRepository.findById(id).orElseThrow(()-> new IllegalStateException( "TipoCliente with id "+ id + " does not exist! "));

    if (type != null && !type.isEmpty() && !Objects.equals(clientType.getType(),type)) {
      Optional<ClientType> TipoClienteOptional = this.clientTypeRepository.findByType(type);
      if (TipoClienteOptional.isPresent()){
        throw new IllegalStateException("Customer type already exists!");
      }else {
        clientType.setType(type);
      }
    }
  }
}

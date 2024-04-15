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
  private static ClientTypeRepository clientTypeRepository;

  @Autowired
  public ClientTypeService(ClientTypeRepository clientTypeRepository){
    ClientTypeService.clientTypeRepository = clientTypeRepository;
  }

  public static List<ClientType> getAllClientTypes(){ return clientTypeRepository.findAll();}

  public static void addNew(ClientType newClientType){
    Optional<ClientType> TipoClienteByTipo = clientTypeRepository.findByType(newClientType.getType());
    if (TipoClienteByTipo.isPresent()){
      throw new IllegalStateException("tipo de cliente already exists");
    }
    clientTypeRepository.save(newClientType);
  }

  public static void delete(Long id){
    boolean exists = clientTypeRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("TipoCliente with id"+id+"does not exist");
    }else{
      clientTypeRepository.deleteById(id);
    }
  }

  public static void update(Long id, String type){
    ClientType clientType = clientTypeRepository.findById(id).orElseThrow(()-> new IllegalStateException( "TipoCliente with id "+ id + " does not exist! "));

    if (type != null && !type.isEmpty() && !Objects.equals(clientType.getType(),type)) {
      Optional<ClientType> TipoClienteOptional = clientTypeRepository.findByType(type);
      if (TipoClienteOptional.isPresent()){
        throw new IllegalStateException("Customer type already exists!");
      }else {
        clientType.setType(type);
      }
    }
  }
}

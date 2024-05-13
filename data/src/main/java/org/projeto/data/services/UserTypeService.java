package org.projeto.data.services;

import org.projeto.data.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.projeto.data.repositories.UserTypeRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserTypeService {
  private final UserTypeRepository userTypeRepository;

  @Autowired
  public UserTypeService(UserTypeRepository userTypeRepository){
    this.userTypeRepository = userTypeRepository;
  }

  public List<UserType> getAllUserTypes(){ return userTypeRepository.findAll();}

  public void addNew(UserType newUserType){
    Optional<UserType> UserType = this.userTypeRepository.findByType(newUserType.getType());
    if (UserType.isPresent()){
      throw new IllegalStateException("tipo de cliente already exists");
    }
    this.userTypeRepository.save(newUserType);
  }

  public void delete(Long id){
    boolean exists = this.userTypeRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("TipoCliente with id"+id+"does not exist");
    }else{
      this.userTypeRepository.deleteById(id);
    }
  }

  public void update(Long id, String type){
    UserType userType = this.userTypeRepository.findById(id).orElseThrow(()-> new IllegalStateException( "TipoCliente with id "+ id + " does not exist! "));

    if (type != null && !type.isEmpty() && !Objects.equals(userType.getType(),type)) {
      Optional<UserType> TipoClienteOptional = this.userTypeRepository.findByType(type);
      if (TipoClienteOptional.isPresent()){
        throw new IllegalStateException("Customer type already exists!");
      }else {
        userType.setType(type);
      }
    }
  }
}

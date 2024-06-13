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
  private static UserTypeRepository userTypeRepository;

  @Autowired
  public UserTypeService(UserTypeRepository userTypeRepository){
    UserTypeService.userTypeRepository = userTypeRepository;
  }

  public static List<UserType> getAllUserTypes(){ return userTypeRepository.findAll();}

  public static void addNew(UserType newUserType){
    Optional<UserType> UserType = UserTypeService.userTypeRepository.findByType(newUserType.getType());
    if (UserType.isPresent()){
      throw new IllegalStateException("tipo de cliente already exists");
    }
    UserTypeService.userTypeRepository.save(newUserType);
  }

  public static void delete(Long id){
    boolean exists = UserTypeService.userTypeRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("TipoCliente with id"+id+"does not exist");
    }else{
      UserTypeService.userTypeRepository.deleteById(id);
    }
  }

  public static void update(Long id, String type){
    UserType userType = UserTypeService.userTypeRepository.findById(id).orElseThrow(()-> new IllegalStateException( "TipoCliente with id "+ id + " does not exist! "));

    if (type != null && !type.isEmpty() && !Objects.equals(userType.getType(),type)) {
      Optional<UserType> TipoClienteOptional = UserTypeService.userTypeRepository.findByType(type);
      if (TipoClienteOptional.isPresent()){
        throw new IllegalStateException("Customer type already exists!");
      }else {
        userType.setType(type);
      }
    }
  }
}

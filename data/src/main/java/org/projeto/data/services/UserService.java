package org.projeto.data.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.projeto.data.entities.User;
import org.projeto.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{
  private static UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository){
    UserService.userRepository = userRepository;
  }

  public static User login(String email, String password) throws Exception {
    User user = UserService.findUserByEmail(email);

    // If user is found and password matches, return the user
    if (user.getPassword().equals(DigestUtils.sha256Hex(password))) {
      return user;
    } else {
      throw new Exception("Invalid password");
    }
  }

  public static void register(User user){
    user.setPassword(DigestUtils.sha256Hex(user.getPassword()));

    userRepository.save(user);
  }

  public static User findUserByEmail(String email) throws Exception {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new Exception("User with email " + email + " not found");
    }
    return user;
  }

  public static List<User> getAllUsers() {
    return userRepository.findAll();
  }
  public static List<User> getUserByTypeID(Integer id){return userRepository.findByUserTypeId(id);}

  public static List<User> getAllClients(){return userRepository.findByUserTypeId(1);}
}

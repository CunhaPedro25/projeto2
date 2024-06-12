package org.projeto.data.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.projeto.data.entities.User;
import org.projeto.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

  private static User findUserByEmail(String email) throws Exception {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new Exception("User with email " + email + " not found");
    }
    return user;
  }

  public static User getById(Long id){
    return userRepository.findById(id).orElse(null);
  }

  public static List<User> getAllUsers() {
    return userRepository.findAll();
  }
}

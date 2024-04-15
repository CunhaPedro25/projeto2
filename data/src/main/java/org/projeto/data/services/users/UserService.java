package org.projeto.data.services.users;

import org.apache.commons.codec.digest.DigestUtils;
import org.projeto.data.entities.users.*;
import org.projeto.data.repositories.users.ClientRepository;
import org.projeto.data.repositories.users.EngineerRepository;
import org.projeto.data.repositories.users.SecretaryRepository;
import org.projeto.data.repositories.users.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService{
  private static ClientRepository clientRepository;
  private static EngineerRepository engineerRepository;
  private static SecretaryRepository secretaryRepository;
  private static WorkerRepository workerRepository;

  @Autowired
  public UserService(ClientRepository clientRepository, EngineerRepository engineerRepository, SecretaryRepository secretaryRepository, WorkerRepository workerRepository){
    UserService.clientRepository = clientRepository;
    UserService.engineerRepository = engineerRepository;
    UserService.secretaryRepository = secretaryRepository;
    UserService.workerRepository = workerRepository;
  }

  public static User login(String email, String password) throws Exception {
    User user = findUserByEmail(email);

    // If user is found and password matches, return the user
    if (user != null && user.getPassword().equals(DigestUtils.sha256Hex(password))) {
      return user;
    } else {
      throw new Exception("Invalid password");
    }
  }

  public static void register(User user) throws Exception{
    user.setPassword(DigestUtils.sha256Hex(user.getPassword()));

    // Determine the type of user and save it to the appropriate repository
    if (user instanceof Client) {
      clientRepository.save((Client) user);
    } else if (user instanceof Engineer) {
      engineerRepository.save((Engineer) user);
    } else if (user instanceof Secretary) {
      secretaryRepository.save((Secretary) user);
    } else if (user instanceof Worker) {
      workerRepository.save((Worker) user);
    } else {
      // Handle unsupported user types or throw an exception
      throw new Exception("Unsupported user type");
    }
  }

  private static User findUserByEmail(String email) throws Exception {
    User user = clientRepository.findByEmail(email);
    if (user == null) {
      user = (User) engineerRepository.findByEmail(email);
    }
    if (user == null) {
      user = secretaryRepository.findByEmail(email);
    }
    if (user == null) {
      user = workerRepository.findByEmail(email);
    }
    if (user == null) {
      throw new Exception("User with email " + email + " not found");
    }
    return user;
  }
}

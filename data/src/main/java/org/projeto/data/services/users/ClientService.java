package org.projeto.data.services.users;

import org.projeto.data.repositories.users.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.projeto.data.entities.Zipcode;
import org.projeto.data.entities.enums.ClientType;
import org.projeto.data.entities.users.Client;
import org.projeto.data.repositories.ZipcodeRepository;
import org.projeto.data.repositories.users.ClientRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {

  private final ClientRepository clientRepository;

  private final ZipcodeRepository zipcodeRepository;

  @Autowired
  public ClientService(ClientRepository clientRepository, ZipcodeRepository zipcodeRepository){
    this.clientRepository = clientRepository;
    this.zipcodeRepository = zipcodeRepository;
  }

  public List<Client> findAll(){
    return this.clientRepository.findAll();
  }

  public void update(Long id, String name, String email, String phone, String address, Integer door, Zipcode zipcode, ClientType type) {
    Client client = this.clientRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exist"));

    if (name != null && !name.isEmpty() && !Objects.equals(client.getName(), name)) {
      client.setName(name);
    }

    if (email != null && !email.isEmpty() && !Objects.equals(client.getEmail(), email)) {
      if (this.clientRepository.existsByEmail(email)) {
        throw new IllegalStateException("Email already taken");
      } else {
        client.setEmail(email);
      }
    }

    if (phone != null && !phone.isEmpty() && !Objects.equals(client.getPhone(), phone)) {
      Optional<Client> userRepositoryByPhone = clientRepository.findByPhone(phone);
      if (userRepositoryByPhone.isPresent()) {
        throw new IllegalStateException("Email already taken");
      } else {
        client.setEmail(email);
      }
    }

    if (address != null && !address.isEmpty() && !Objects.equals(client.getAddress(), address)){
      client.setAddress(address);
    }

    if (door != null && !Objects.equals(client.getDoor(), door)){
      client.setDoor(door);
    }

    if (door != null && !Objects.equals(client.getDoor(), door)){
      client.setDoor(door);
    }

    if (zipcode != null && !Objects.equals(client.getZipcode(), zipcode)) {
      if (!zipcodeRepository.existsById(zipcode.getId())) {
        zipcodeRepository.save(zipcode);
      }

      client.setZipcode(zipcode);
    }
  }
}

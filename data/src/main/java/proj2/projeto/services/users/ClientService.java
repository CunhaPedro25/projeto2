package proj2.projeto.services.users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.entities.Zipcode;
import proj2.projeto.entities.enums.ClientType;
import proj2.projeto.entities.users.Client;
import proj2.projeto.repositories.ZipcodeRepository;
import proj2.projeto.repositories.users.ClientRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService extends UserService<Client> {
  private final ClientRepository clientRepository;
  private final ZipcodeRepository zipcodeRepository;

  @Autowired
  public ClientService(ClientRepository clientRepository,
                       ZipcodeRepository zipcodeRepository) {
    super(clientRepository);
    this.clientRepository = clientRepository;
    this.zipcodeRepository = zipcodeRepository;
  }

  @Transactional
  public void update(Long id, String name, String email, String phone, String address, Integer door, Zipcode zipcode, ClientType type) {
    Client client = clientRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exist"));

    if (name != null && !name.isEmpty() && !Objects.equals(client.getName(), name)) {
      client.setName(name);
    }

    if (email != null && !email.isEmpty() && !Objects.equals(client.getEmail(), email)) {
      Optional<Client> userRepositoryByEmail = clientRepository.findByEmail(email);
      if (userRepositoryByEmail.isPresent()) {
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

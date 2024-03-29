package proj2.projeto.services.users;

import jakarta.transaction.Transactional;
import proj2.projeto.entities.users.User;
import proj2.projeto.repositories.users.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class UserService<T extends User> {
  private final UserRepository<T> userRepository;

  public UserService(UserRepository<T> userRepository) {
    this.userRepository = userRepository;
  }

  public List<T> getUsers() {
    return userRepository.findAll();
  }

  public void addNew(T newUser) {
    Optional<T> userByEmail = userRepository.findByEmail(newUser.getEmail());
    Optional<T> userByPhoneNumber = userRepository.findByPhone(newUser.getPhone());

    if (userByEmail.isPresent()) {
      throw new IllegalStateException("Email already taken");
    }
    if (userByPhoneNumber.isPresent()) {
      throw new IllegalStateException("Phone number already taken");
    }
    userRepository.save(newUser);
  }

  public void delete(Long userId) {
    boolean exists = userRepository.existsById(userId);
    if (!exists) {
      throw new IllegalStateException("User with id " + userId + " does not exist");
    } else {
      userRepository.deleteById(userId);
    }
  }

  @Transactional
  public void update(Long id, String name, String email, String phone) {
    T user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exist"));

    if (name != null && !name.isEmpty() && !Objects.equals(user.getName(), name)) {
      user.setName(name);
    }

    if (email != null && !email.isEmpty() && !Objects.equals(user.getEmail(), email)) {
      Optional<T> userRepositoryByEmail = userRepository.findByEmail(email);
      if (userRepositoryByEmail.isPresent()) {
        throw new IllegalStateException("Email already taken");
      } else {
        user.setEmail(email);
      }
    }

    if (phone != null && !phone.isEmpty() && !Objects.equals(user.getPhone(), phone)) {
      Optional<T> userRepositoryByPhone = userRepository.findByPhone(phone);
      if (userRepositoryByPhone.isPresent()) {
        throw new IllegalStateException("Email already taken");
      } else {
        user.setEmail(email);
      }
    }
  }
}

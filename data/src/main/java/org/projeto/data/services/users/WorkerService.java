package org.projeto.data.services.users;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.projeto.data.entities.Team;
import org.projeto.data.entities.users.Worker;
import org.projeto.data.repositories.users.WorkerRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class WorkerService {
  @Autowired
  private static WorkerRepository workerRepository;


  public static void update(Long id, String name, String email, String phone, Team team) {
    Worker worker = workerRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exist"));

    if (name != null && !name.isEmpty() && !Objects.equals(worker.getName(), name)) {
      worker.setName(name);
    }

    if (email != null && !email.isEmpty() && !Objects.equals(worker.getEmail(), email)) {
      if (workerRepository.existsByEmail(email)) {
        throw new IllegalStateException("Email already taken");
      } else {
        worker.setEmail(email);
      }
    }

    if (phone != null && !phone.isEmpty() && !Objects.equals(worker.getPhone(), phone)) {
      Optional<Worker> userRepositoryByPhone = workerRepository.findByPhone(phone);
      if (userRepositoryByPhone.isPresent()) {
        throw new IllegalStateException("Email already taken");
      } else {
        worker.setEmail(email);
      }
    }

    if (team != null && !Objects.equals(worker.getTeam(), team)){
      worker.setTeam(team);
    }
  }
}

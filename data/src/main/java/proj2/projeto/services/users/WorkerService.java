package proj2.projeto.services.users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.entities.Team;
import proj2.projeto.entities.users.Worker;
import proj2.projeto.repositories.users.WorkerRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class WorkerService extends UserService<Worker> {
  private final WorkerRepository workerRepository;

  @Autowired
  public WorkerService(WorkerRepository workerRepository) {
    super(workerRepository);
    this.workerRepository = workerRepository;
  }

  @Transactional
  public void update(Long id, String name, String email, String phone, Team team) {
    Worker worker = workerRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exist"));

    if (name != null && !name.isEmpty() && !Objects.equals(worker.getName(), name)) {
      worker.setName(name);
    }

    if (email != null && !email.isEmpty() && !Objects.equals(worker.getEmail(), email)) {
      Optional<Worker> userRepositoryByEmail = workerRepository.findByEmail(email);
      if (userRepositoryByEmail.isPresent()) {
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

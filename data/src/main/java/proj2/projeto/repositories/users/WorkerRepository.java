package proj2.projeto.repositories.users;

import org.springframework.stereotype.Repository;
import proj2.projeto.entities.users.Worker;

import java.util.List;

@Repository
public interface WorkerRepository extends UserRepository<Worker> {
  List<Worker> findWorkersByTeam_Id (Integer team);
}

package org.projeto.data.repositories.users;

import org.springframework.stereotype.Repository;
import org.projeto.data.entities.users.Worker;

import java.util.List;

@Repository
public interface WorkerRepository extends UserRepository<Worker> {
  List<Worker> findWorkersByTeam_Id (Integer team);
}

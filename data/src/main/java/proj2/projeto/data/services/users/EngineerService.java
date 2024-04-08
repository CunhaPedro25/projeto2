package proj2.projeto.data.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.data.entities.users.Engineer;
import proj2.projeto.data.repositories.users.EngineerRepository;

@Service
public class EngineerService extends UserService<Engineer> {
  private final EngineerRepository engineerRepository;
  @Autowired
  public EngineerService(EngineerRepository engineerRepository) {
    super(engineerRepository);
    this.engineerRepository = engineerRepository;
  }
}

package proj2.projeto.data.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.data.entities.users.Secretary;
import proj2.projeto.data.repositories.users.SecretaryRepository;

@Service
public class SecretaryService extends UserService<Secretary> {
  private final SecretaryRepository secretaryRepository;
  @Autowired
  public SecretaryService(SecretaryRepository secretaryRepository) {
    super(secretaryRepository);
    this.secretaryRepository = secretaryRepository;
  }
}

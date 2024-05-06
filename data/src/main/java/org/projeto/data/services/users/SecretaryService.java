package org.projeto.data.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.projeto.data.entities.users.Secretary;
import org.projeto.data.repositories.users.SecretaryRepository;

@Service
public class SecretaryService {

  private final SecretaryRepository secretaryRepository;

  @Autowired
  public SecretaryService(SecretaryRepository secretaryRepository) {
    this.secretaryRepository = secretaryRepository;
  }

}

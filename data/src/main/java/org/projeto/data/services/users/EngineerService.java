package org.projeto.data.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.projeto.data.entities.users.Engineer;
import org.projeto.data.repositories.users.EngineerRepository;

@Service
public class EngineerService {
  @Autowired
  private static EngineerRepository engineerRepository;
}
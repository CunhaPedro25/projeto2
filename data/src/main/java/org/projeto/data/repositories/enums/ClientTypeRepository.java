package org.projeto.data.repositories.enums;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.projeto.data.entities.enums.ClientType;

import java.util.Optional;

@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, Long> {
  ClientType findById (Integer id);
  Optional<ClientType> findByType (String type);
}

package org.projeto.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.projeto.data.entities.UserType;

import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
  UserType findById (Integer id);
  Optional<UserType> findByType (String type);
}

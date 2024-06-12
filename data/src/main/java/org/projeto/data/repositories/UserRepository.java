package org.projeto.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.projeto.data.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findById(Integer id);

  User findByEmail(String email);

  boolean existsByEmail(String email);

  @Query("SELECT e FROM #{#entityName} e WHERE e.phone = ?1")
  Optional<User> findByPhone(String phone);

  List<User> findByUserTypeId(Integer id);

}

package org.projeto.data.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.projeto.data.entities.users.User;

import java.util.Optional;

@NoRepositoryBean
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
  Optional<T> findById(Integer id);

  T findByEmail(String email);

  boolean existsByEmail(String email);

  @Query("SELECT e FROM #{#entityName} e WHERE e.phone = ?1")
  Optional<T> findByPhone(String phone);
}

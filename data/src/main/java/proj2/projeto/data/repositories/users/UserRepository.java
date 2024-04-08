package proj2.projeto.data.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import proj2.projeto.data.entities.users.User;

import java.util.Optional;

@NoRepositoryBean
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
  Optional<T> findById(Integer id);

  Optional<T> findByEmail(String email);

  @Query("SELECT e FROM #{#entityName} e WHERE e.phone = ?1")
  Optional<T> findByPhone(String phone);
}

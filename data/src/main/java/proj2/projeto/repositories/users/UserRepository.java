package proj2.projeto.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository<T> extends JpaRepository<T, Long> {
  Optional<T> findById(Integer id);

  Optional<T> findByEmail(String email);

  @Query("SELECT e FROM #{#entityName} e WHERE e.phone = ?1")
  Optional<T> findByPhone(String phone);
}

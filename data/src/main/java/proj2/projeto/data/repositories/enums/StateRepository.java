package proj2.projeto.data.repositories.enums;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj2.projeto.data.entities.enums.State;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
  State findById (Integer id);
  Optional<State> findByDescription(String description);
}

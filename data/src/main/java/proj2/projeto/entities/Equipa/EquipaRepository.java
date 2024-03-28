package proj2.projeto.entities.Equipa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipaRepository extends JpaRepository<Equipa,Long> {
  Equipa findById(Integer id);
  Optional<Equipa> findByChefe_Id(Integer id);
}

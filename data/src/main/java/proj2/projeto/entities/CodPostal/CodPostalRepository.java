package proj2.projeto.entities.CodPostal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodPostalRepository extends JpaRepository<CodPostal,Long> {
}

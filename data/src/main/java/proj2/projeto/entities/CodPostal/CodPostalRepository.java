package proj2.projeto.entities.CodPostal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proj2.projeto.entities.Admin.Admin;

import java.util.Optional;

@Repository
public interface CodPostalRepository extends JpaRepository<CodPostal,Long> {
  CodPostal findById (CodPostal codPostal);
  @Query("SELECT cdp FROM CodPostal cdp WHERE cdp.distrito = ?1")
  Optional<CodPostal> findByDistrito (String distrito);

}

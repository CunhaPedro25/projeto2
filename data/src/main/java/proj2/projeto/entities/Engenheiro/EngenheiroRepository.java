package proj2.projeto.entities.Engenheiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EngenheiroRepository extends JpaRepository<Engenheiro,Long> {
  Engenheiro findById(Integer id);
  @Query("SELECT eng FROM Engenheiro eng WHERE eng.email = ?1")
  Optional<Engenheiro> findByEmail(String email);
  @Query("SELECT eng FROM Engenheiro eng WHERE eng.nome = ?1")
  Optional<Engenheiro> findByNome(String nome);
  @Query("SELECT eng FROM Engenheiro eng WHERE eng.telemovel = ?1")
  Optional<Engenheiro> findByTelemovel(Integer telemovel);

}

package proj2.projeto.entities.Equipa.Obreiro;

import org.springframework.data.jpa.repository.JpaRepository;
import proj2.projeto.entities.Engenheiro.Engenheiro;
import proj2.projeto.entities.Equipa.Equipa;

import java.util.Optional;

public interface ObreiroRepository extends JpaRepository<Obreiro,Long> {
  Obreiro findById(Integer id);
  Optional<Obreiro> findByEmail(String email);
  Optional<Obreiro> findByTelemovel(Integer telemovel);
  Optional<Obreiro> findByEquipaId (Integer equipaId);
  Optional<Obreiro> findByNome(String nome);

}

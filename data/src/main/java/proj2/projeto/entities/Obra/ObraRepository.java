package proj2.projeto.entities.Obra;

import org.springframework.data.jpa.repository.JpaRepository;
import proj2.projeto.entities.Equipa.Equipa;
import proj2.projeto.entities.Equipa.Obreiro.Obreiro;

import java.util.Optional;

public interface ObraRepository extends JpaRepository<Obra,Long> {
  Obra findById(Integer id);
 // Optional<Obra> findByConcluida(Boolean isConcluida); wut?
  Optional<Obra> findByEquipa (Equipa equipa); // or Optional<Obra> findByEquipa (Integer equipaId); ??

}

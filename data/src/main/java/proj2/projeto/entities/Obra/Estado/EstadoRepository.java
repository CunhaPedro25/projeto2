package proj2.projeto.entities.Obra.Estado;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado,Long> {
  Estado findById(Integer id);
  Optional<Estado> findByDescricao(String descricao);

}

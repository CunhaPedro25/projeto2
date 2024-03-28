package proj2.projeto.entities.Obra.Orcamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtapaRepository extends JpaRepository<Etapa,Long> {
  Etapa findById(Integer id);
  Optional<Etapa> findByOrcamento_Id(Integer orcamentoId);

}

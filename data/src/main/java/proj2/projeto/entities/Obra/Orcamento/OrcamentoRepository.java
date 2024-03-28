package proj2.projeto.entities.Obra.Orcamento;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento,Long> {
  Orcamento findById(Integer id);
  Optional<Orcamento> findByCliente_Id(Integer clienteId);
  Optional<Orcamento> findByEngenheiro_Id(Integer engenheiroId);
}

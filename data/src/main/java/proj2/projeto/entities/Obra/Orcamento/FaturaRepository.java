package proj2.projeto.entities.Obra.Orcamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura,Long> {
  Fatura findById(Integer id);
  Optional<Fatura> findByEtapa_Id(Integer id);
  Optional<Fatura> findByDataEmissao(Date dataEmissao);
  Optional<Fatura> findByPago();
  Optional<Fatura> findByClienteAndPago();



}

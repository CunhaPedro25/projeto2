package proj2.projeto.entities.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoClienteRepository extends JpaRepository<TipoCliente,Long> {
  TipoCliente findById(Integer id);
  Optional<TipoCliente> findByTipo (String tipo);


}

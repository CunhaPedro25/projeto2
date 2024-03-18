package proj2.projeto.entities.Cliente;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findById(Integer id);
    Optional<Cliente> findByEmailAndPassword(String email, String password);
}

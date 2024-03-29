package proj2.projeto.repositories.enums;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj2.projeto.entities.enums.ClientType;

import java.util.Optional;

@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, Long> {
  ClientType findById (Integer id);
  Optional<ClientType> findByType (String type);
}

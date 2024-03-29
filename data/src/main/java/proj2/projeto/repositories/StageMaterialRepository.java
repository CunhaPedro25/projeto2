package proj2.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj2.projeto.entities.StageMaterial;
import proj2.projeto.entities.StageMaterialId;

@Repository
public interface StageMaterialRepository  extends JpaRepository<StageMaterial, Long> {
  StageMaterial findById(StageMaterialId id);
}

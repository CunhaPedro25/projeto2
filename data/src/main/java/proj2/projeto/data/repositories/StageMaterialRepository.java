package proj2.projeto.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj2.projeto.data.entities.StageMaterial;
import proj2.projeto.data.entities.StageMaterialId;

@Repository
public interface StageMaterialRepository  extends JpaRepository<StageMaterial, Long> {
  StageMaterial findById(StageMaterialId id);
}

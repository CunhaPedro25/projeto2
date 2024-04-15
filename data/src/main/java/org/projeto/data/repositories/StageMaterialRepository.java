package org.projeto.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.projeto.data.entities.StageMaterial;
import org.projeto.data.entities.StageMaterialId;

@Repository
public interface StageMaterialRepository  extends JpaRepository<StageMaterial, Long> {
  StageMaterial findById(StageMaterialId id);
}

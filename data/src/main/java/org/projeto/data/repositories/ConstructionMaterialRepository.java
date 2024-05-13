package org.projeto.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.projeto.data.entities.ConstructionMaterial;
import org.projeto.data.entities.ConstructionMaterialId;

@Repository
public interface ConstructionMaterialRepository  extends JpaRepository<ConstructionMaterial, Long> {
  ConstructionMaterial findById(ConstructionMaterialId id);
}

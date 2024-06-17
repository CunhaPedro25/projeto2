package org.projeto.data.repositories;

import org.projeto.data.entities.Material;
import org.projeto.data.services.MaterialService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository  extends JpaRepository<Material, Long>  {
  Material findById(Integer id);
  Material findMaterialByName(String name);
}
package org.projeto.data.repositories;

import org.projeto.data.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository  extends JpaRepository<Material, Long>  {
  Material findById(Integer id);
}
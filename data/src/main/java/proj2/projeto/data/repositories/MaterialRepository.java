package proj2.projeto.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj2.projeto.data.entities.Material;

@Repository
public interface MaterialRepository  extends JpaRepository<Material, Long>  {
  Material findById(Integer id);
}
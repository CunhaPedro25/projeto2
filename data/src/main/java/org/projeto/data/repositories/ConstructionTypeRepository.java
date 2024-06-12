package org.projeto.data.repositories;

import org.projeto.data.entities.ConstructionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConstructionTypeRepository extends JpaRepository<ConstructionType, Long> {
    List<ConstructionType> findById(Integer id);

    Optional<ConstructionType> findByType(String type);
}

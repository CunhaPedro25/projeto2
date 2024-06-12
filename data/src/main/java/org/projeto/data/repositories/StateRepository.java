package org.projeto.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.projeto.data.entities.State;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
  State findById (Integer id);
  Optional<State> findByDescription(String description);
}

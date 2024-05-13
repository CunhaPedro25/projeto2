package org.projeto.data.repositories;

import org.projeto.data.entities.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageRepository  extends JpaRepository<Stage, Long> {
  Stage findById(Integer id);
}

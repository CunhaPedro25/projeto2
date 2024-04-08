package proj2.projeto.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj2.projeto.data.entities.Stage;

import java.util.List;

@Repository
public interface StageRepository  extends JpaRepository<Stage, Long> {
  Stage findById(Integer id);

  List<Stage> findStagesByBudget_Id(Integer budget_id);

  List<Stage> findStagesByState_Id(Integer state_id);
}

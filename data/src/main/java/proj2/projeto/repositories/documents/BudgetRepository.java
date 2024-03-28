package proj2.projeto.repositories.documents;

import org.springframework.stereotype.Repository;
import proj2.projeto.entities.Budget;

import java.util.List;

@Repository
public interface BudgetRepository extends DocumentRepository<Budget> {
  List<Budget> findByProject_Id(Integer id);
}

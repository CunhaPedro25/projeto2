package org.projeto.data.repositories.documents;

import org.springframework.stereotype.Repository;
import org.projeto.data.entities.Budget;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends DocumentRepository<Budget> {

  Optional<Budget> findByProject_Id(Integer id);
  List<Budget> findDocumentByProject_Id(Integer id);

  List<Budget> findBudgetByClient_id(Integer id);

  Optional<Budget> findByProject_IdAndCreateDate(Integer project_id, LocalDate createDate);
}

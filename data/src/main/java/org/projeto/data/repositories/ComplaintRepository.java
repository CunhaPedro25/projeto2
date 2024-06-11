package org.projeto.data.repositories;

import org.projeto.data.entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long>{

  // @Query("select c from Complaint c where c.client = ?1")
  List<Complaint> findComplaintsByClient_Id (Long id);

  List<Complaint> findComplaintByConstruction_Id (Integer id);
}

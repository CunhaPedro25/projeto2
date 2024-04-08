package proj2.projeto.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj2.projeto.data.entities.Complaint;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long>{
  Complaint findById (Integer id);

  List<Complaint> findComplaintsByClient_Id (Integer id);

  List<Complaint> findComplaintByConstruction_Id (Integer id);
}

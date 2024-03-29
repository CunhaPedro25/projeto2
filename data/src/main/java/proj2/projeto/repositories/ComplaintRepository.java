package proj2.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj2.projeto.entities.Complaint;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long>{
  Complaint findById (Integer id);

  List<Complaint> findComplaintsByClient_Id (Integer id);

  List<Complaint> findComplaintByConstruction_Id (Integer id);
}

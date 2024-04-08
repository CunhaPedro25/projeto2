package proj2.projeto.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proj2.projeto.data.entities.Invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice, Long> {

  Invoice findById (Integer id);

  Optional<Invoice> findByStage_Id(Integer id);

  List<Invoice> findInvoicesByIssueDate(LocalDate issueDate);

  List<Invoice> findInvoicesByClient_Id (Integer id);

  @Query ("SELECT i FROM Invoice i WHERE i.paid = true")
  List<Invoice> findPaidInvoices();

  @Query ("SELECT i FROM Invoice i WHERE i.paid = false")
  List<Invoice> findUnpaidInvoices();
}

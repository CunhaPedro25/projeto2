package org.projeto.data.services;

import org.projeto.data.entities.Invoice;
import org.projeto.data.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
  private static InvoiceRepository invoiceRepository;

  @Autowired
  public InvoiceService(InvoiceRepository invoiceRepository) {
    InvoiceService.invoiceRepository = invoiceRepository;
  }

  public List<Invoice> getInvoice() {
    return InvoiceService.invoiceRepository.findAll();
  }

  public void addNew(Invoice newInvoice) {
    InvoiceService.invoiceRepository.save(newInvoice);
  }

  public void delete(Long id) {
    boolean exists = InvoiceService.invoiceRepository.existsById(id);
    if (!exists) {
      throw new IllegalStateException("The Invoice with id " + id + " does not exist");
    } else {
      InvoiceService.invoiceRepository.deleteById(id);
    }
  }

  public Optional<Invoice> findByStageId(Integer id) {
    return InvoiceService.invoiceRepository.findByStage_Id(id);
  }

  public List<Invoice> findInvoicesByIssueDate(LocalDate issueDate) {
    return InvoiceService.invoiceRepository.findInvoicesByIssueDate(issueDate);
  }

  public List<Invoice> findInvoicesByClientId(Integer id) {
    return InvoiceService.invoiceRepository.findInvoicesByClient_Id(id);
  }
}

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

  public static List<Invoice> getInvoice() {
    return InvoiceService.invoiceRepository.findAll();
  }

  public static void addNew(Invoice newInvoice) {
    InvoiceService.invoiceRepository.save(newInvoice);
  }

  public static void delete(Long id) {
    boolean exists = InvoiceService.invoiceRepository.existsById(id);
    if (!exists) {
      throw new IllegalStateException("The Invoice with id " + id + " does not exist");
    } else {
      InvoiceService.invoiceRepository.deleteById(id);
    }
  }

  public static Optional<Invoice> findByStageId(Integer id) {
    return InvoiceService.invoiceRepository.findByStage_Id(id);
  }

  public static List<Invoice> findInvoicesByIssueDate(LocalDate issueDate) {
    return InvoiceService.invoiceRepository.findInvoicesByIssueDate(issueDate);
  }

  public static List<Invoice> findInvoicesByClientId(Integer id) {
    return InvoiceService.invoiceRepository.findInvoicesByClient_Id(id);
  }

  public static void payInvoice(Long id) {
    Invoice invoice = InvoiceService.invoiceRepository.findById(id).orElseThrow(() -> new IllegalStateException("The Invoice with id " + id + " does not exist"));
    invoice.setPaid(true);
    InvoiceService.invoiceRepository.save(invoice);
  }
}

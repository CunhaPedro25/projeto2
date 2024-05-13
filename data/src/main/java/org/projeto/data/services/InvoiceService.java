package org.projeto.data.services;

import org.projeto.data.entities.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.projeto.data.repositories.InvoiceRepository;

import java.util.List;

@Service
public class InvoiceService {
  private final InvoiceRepository invoiceRepository;

  @Autowired
  public InvoiceService(InvoiceRepository invoiceRepository) {
    this.invoiceRepository = invoiceRepository;
  }

  public List<Invoice> getInvoice(){ return this.invoiceRepository.findAll();}

  public void addNew(Invoice newInvoice){
    this.invoiceRepository.save(newInvoice);
  }

  public void delete(Long id){
    boolean exists = this.invoiceRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("The Fatura with id"+id+"does not exist");
    }else{
      this.invoiceRepository.deleteById(id);
    }
  }
}

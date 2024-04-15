package org.projeto.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.projeto.data.entities.Invoice;
import org.projeto.data.repositories.InvoiceRepository;

import java.util.List;

@Service
public class InvoiceService {
  @Autowired
  private static InvoiceRepository invoiceRepository;

  public static List<Invoice> getInvoice(){ return invoiceRepository.findAll();}

  public static void addNew(Invoice newInvoice){
    invoiceRepository.save(newInvoice);
  }
  public static void delete(Long id){
    boolean exists = invoiceRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("The Fatura with id"+id+"does not exist");
    }else{
      invoiceRepository.deleteById(id);
    }
  }
}

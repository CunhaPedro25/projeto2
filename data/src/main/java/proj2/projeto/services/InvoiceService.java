package proj2.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.entities.Invoice;
import proj2.projeto.repositories.InvoiceRepository;

import java.util.List;

@Service
public class InvoiceService {
  private final InvoiceRepository invoiceRepository;
  @Autowired
  public InvoiceService(InvoiceRepository invoiceRepository){ this.invoiceRepository = invoiceRepository;}
  public List<Invoice> getInvoice(){ return invoiceRepository.findAll();}

  public void addNew(Invoice newInvoice){
    invoiceRepository.save(newInvoice);
  }
  public void delete(Long id){
    boolean exists = invoiceRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("The Fatura with id"+id+"does not exist");
    }else{
      invoiceRepository.deleteById(id);
    }
  }
}

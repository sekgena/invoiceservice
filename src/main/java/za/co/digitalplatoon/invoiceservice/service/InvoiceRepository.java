package za.co.digitalplatoon.invoiceservice.service;

import org.springframework.stereotype.Repository;
import za.co.digitalplatoon.invoiceservice.invoice.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    //List<Invoice> findById(Long ID);
}
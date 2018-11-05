package za.co.digitalplatoon.invoiceservice.invoice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.digitalplatoon.invoiceservice.service.InvoiceRepository;

@RestController
public class InvoiceController{

  /*  private final InvoiceRepository repository;

    InvoiceController(InvoiceRepository repository) {
        this.repository = repository;
    }
*/

  @Autowired
    InvoiceRepository repository;



    @GetMapping("/Invoices")
    List<Invoice> all() {
        return (List<Invoice>) repository.findAll();
    }

    @PostMapping("/Invoices")
    Invoice newInvoice(@RequestBody Invoice newInvoice) {
        return repository.save(newInvoice);
    }

    // Single item

    @GetMapping("/Invoices/{id}")
    Optional<Invoice> one(@PathVariable Long id) {

        return repository.findById(id);
                //.orElseThrow(() -> new InvoiceNotFoundException(id));
    }

}
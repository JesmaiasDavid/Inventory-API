package inventoryservice.service.admin;

import inventoryservice.domain.admin.Invoice;
import inventoryservice.repository.admin.InvoiceRepository;
import inventoryservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService implements IService<Invoice,Integer> {

    private static InvoiceService service = null;

    @Autowired
    private InvoiceRepository repository;



    public static InvoiceService getService(){
        if (service == null) service = new InvoiceService();
        return service;
    }

    @Override
    public Invoice add(Invoice invoice) {
        return repository.save(invoice);
    }

    @Override
    public Invoice update(Invoice invoice) {
        return repository.save(invoice);
    }

    @Override
    public void delete(Integer integer) {
       repository.deleteById(integer);
    }

    @Override
    public Invoice get(Integer integer) {
        Optional<Invoice> optInvoice = this.repository.findById(integer);
        return optInvoice.orElse(null);
    }

    @Override
    public List<Invoice> getAll() {
        return repository.findAll();
    }
}

package inventoryservice.service.admin;

import inventoryservice.domain.admin.InvoiceProduct;
import inventoryservice.domain.admin.InvoiceProductId;
import inventoryservice.repository.admin.InvoiceProductRepository;
import inventoryservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceProductService implements IService<InvoiceProduct, InvoiceProductId> {

    private static InvoiceProductService service = null;

    @Autowired
    private InvoiceProductRepository repository;



    public static InvoiceProductService getService(){
        if (service == null) service = new InvoiceProductService();
        return service;
    }



    @Override
    public InvoiceProduct add(InvoiceProduct invoiceProduct) {
        return repository.save(invoiceProduct);
    }

    @Override
    public InvoiceProduct update(InvoiceProduct invoiceProduct) {
        return repository.save(invoiceProduct);
    }

    @Override
    public void delete(InvoiceProductId invoiceProductId) {
           repository.deleteById(invoiceProductId);
    }

    @Override
    public InvoiceProduct get(InvoiceProductId invoiceProductId) {
        Optional<InvoiceProduct> optInvoiceProduct = this.repository.findById(invoiceProductId);
        return optInvoiceProduct.orElse(null);
    }

    @Override
    public List<InvoiceProduct> getAll() {
        return repository.findAll();
    }

    public List<Integer> getInvoicebyProductId(int productId){
        return  repository.findInvoiceIdByProductId(productId);
    }

    public List<Integer> getProductbyInvoiceId(int invoiceId){
        return repository.findProductIdByInvoiceId(invoiceId);
    }

    public List<Integer> getQuantityByInvoiceId(int invoiceId){
        return repository.findQuantityIdByInvoiceId(invoiceId);
    }
}

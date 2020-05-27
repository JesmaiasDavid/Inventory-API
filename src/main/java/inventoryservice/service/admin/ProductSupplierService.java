package inventoryservice.service.admin;

import inventoryservice.domain.admin.Product;
import inventoryservice.domain.admin.ProductSupplier;
import inventoryservice.domain.admin.ProductSupplierId;
import inventoryservice.domain.admin.Supplier;
import inventoryservice.repository.admin.ProductRepository;
import inventoryservice.repository.admin.ProductSupplierRepository;
import inventoryservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSupplierService implements IService<ProductSupplier, ProductSupplierId> {

    private static ProductSupplierService service = null;

    @Autowired
    private ProductSupplierRepository repository;



    public static ProductSupplierService getService(){
        if (service == null) service = new ProductSupplierService();
        return service;
    }


    @Override
    public ProductSupplier add(ProductSupplier productSupplier) {
        return repository.save(productSupplier);
    }

    @Override
    public ProductSupplier update(ProductSupplier productSupplier) {
        return repository.save(productSupplier);
    }

    @Override
    public void delete(ProductSupplierId productSupplierId) {
           repository.deleteById(productSupplierId);
    }

    @Override
    public ProductSupplier get(ProductSupplierId productSupplierId) {
        Optional<ProductSupplier> optProductSupplier = this.repository.findById(productSupplierId);
        return optProductSupplier.orElse(null);
    }

    @Override
    public List<ProductSupplier> getAll() {
        return repository.findAll();
    }

    @Override
    public Boolean existsById(ProductSupplierId productSupplierId) {
        return repository.existsById(productSupplierId);
    }

    public List<Product> getProductBySupplier(Supplier supplier){
        return  repository.findProductBySupplier(supplier);
    }


    public List<Supplier> getSupplierByProduct(Product product){
        return  repository.findSupplierByProduct(product);
    }
}

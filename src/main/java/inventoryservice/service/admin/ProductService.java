package inventoryservice.service.admin;

import inventoryservice.domain.admin.Product;
import inventoryservice.repository.admin.ProductRepository;
import inventoryservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IService<Product,Integer> {

    private static ProductService service = null;

    @Autowired
    private ProductRepository repository;



    public static ProductService getService(){
        if (service == null) service = new ProductService();
        return service;
    }



    @Override
    public Product add(Product product) {
        return repository.save(product);
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Integer integer) {
         repository.deleteById(integer);
    }

    @Override
    public Product get(Integer integer) {
        Optional<Product> optProduct = this.repository.findById(integer);
        return optProduct.orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }
}

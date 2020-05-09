package inventoryservice.service.admin;

import inventoryservice.domain.admin.Supplier;
import inventoryservice.repository.admin.SupplierRepository;
import inventoryservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService implements IService<Supplier,Integer> {

    private static SupplierService service = null;

    @Autowired
    private SupplierRepository repository;



    public static SupplierService getService(){
        if (service == null) service = new SupplierService();
        return service;
    }


    @Override
    public Supplier add(Supplier supplier) {
        return repository.save(supplier);
    }

    @Override
    public Supplier update(Supplier supplier) {
        return repository.save(supplier);
    }

    @Override
    public void delete(Integer integer) {
        repository.deleteById(integer);
    }

    @Override
    public Supplier get(Integer integer) {
        Optional<Supplier> optSupplier = this.repository.findById(integer);
        return optSupplier.orElse(null);
    }

    @Override
    public List<Supplier> getAll() {
        return repository.findAll();
    }
}

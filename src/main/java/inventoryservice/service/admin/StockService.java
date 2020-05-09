package inventoryservice.service.admin;

import inventoryservice.domain.admin.Stock;
import inventoryservice.repository.admin.StockRepository;
import inventoryservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService implements IService<Stock,Integer> {

    private static StockService service = null;

    @Autowired
    private StockRepository repository;



    public static StockService getService(){
        if (service == null) service = new StockService();
        return service;
    }



    @Override
    public Stock add(Stock stock) {
        return repository.save(stock);
    }

    @Override
    public Stock update(Stock stock) {
        return repository.save(stock);
    }

    @Override
    public void delete(Integer integer) {
       repository.deleteById(integer);
    }

    @Override
    public Stock get(Integer integer) {
        Optional<Stock> optStock = this.repository.findById(integer);
        return optStock.orElse(null);
    }

    @Override
    public List<Stock> getAll() {
        return repository.findAll();
    }
}

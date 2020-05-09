package inventoryservice.service.admin;

import inventoryservice.domain.admin.Category;
import inventoryservice.repository.admin.CategoryRepository;
import inventoryservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements IService<Category,Integer> {


    private static CategoryService service = null;

    @Autowired
    private CategoryRepository repository;

    public static CategoryService getService(){
        if(service==null) service=new CategoryService();
        return  service;
    }


    @Override
    public Category add(Category category) {
        return repository.save(category);
    }

    @Override
    public Category update(Category category) {
        return repository.save(category);
    }

    @Override
    public void delete(Integer integer) {
      repository.deleteById(integer);
    }

    @Override
    public Category get(Integer integer) {
        Optional<Category> optCategory = this.repository.findById(integer);
        return optCategory.orElse(null);
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }
}

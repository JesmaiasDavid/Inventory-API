package inventoryservice.service.admin;

import inventoryservice.domain.admin.Role;
import inventoryservice.repository.admin.RoleRepository;
import inventoryservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IService<Role,Integer> {

    private static RoleService service = null;

    @Autowired
    private RoleRepository repository;



    public static RoleService getService(){
        if (service == null) service = new RoleService();
        return service;
    }





    @Override
    public Role add(Role role) {
        return repository.save(role);
    }

    @Override
    public Role update(Role role) {
        return repository.save(role);
    }

    @Override
    public void delete(Integer integer) {
         repository.deleteById(integer);
    }

    @Override
    public Role get(Integer integer) {
        Optional<Role> optRole = this.repository.findById(integer);
        return optRole.orElse(null);
    }

    @Override
    public List<Role> getAll() {
        return repository.findAll();
    }

    @Override
    public Boolean existsById(Integer integer) {
        return repository.existsById(integer);
    }
}

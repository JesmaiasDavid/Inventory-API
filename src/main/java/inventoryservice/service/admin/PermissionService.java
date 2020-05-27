package inventoryservice.service.admin;

import inventoryservice.domain.admin.Permission;
import inventoryservice.repository.admin.PermissionRepository;
import inventoryservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IService<Permission,Integer> {

    private static PermissionService service = null;

    @Autowired
    private PermissionRepository repository;



    public static PermissionService getService(){
        if (service == null) service = new PermissionService();
        return service;
    }


    @Override
    public Permission add(Permission permission) {
        return repository.save(permission);
    }

    @Override
    public Permission update(Permission permission) {
        return repository.save(permission);
    }

    @Override
    public void delete(Integer integer) {
     repository.deleteById(integer);
    }

    @Override
    public Permission get(Integer integer) {
        Optional<Permission> optPermission = this.repository.findById(integer);
        return optPermission.orElse(null);
    }

    @Override
    public List<Permission> getAll() {
        return repository.findAll();
    }

    @Override
    public Boolean existsById(Integer integer) {
        return repository.existsById(integer);
    }
}

package inventoryservice.service.admin;

import inventoryservice.domain.admin.RolePermission;
import inventoryservice.domain.admin.RolePermissonId;
import inventoryservice.repository.admin.RolePermissionRepository;
import inventoryservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolePermissionService implements IService<RolePermission, RolePermissonId> {

    private static RolePermissionService service = null;

    @Autowired
    private RolePermissionRepository repository;



    public static RolePermissionService getService(){
        if (service == null) service = new RolePermissionService();
        return service;
    }





    @Override
    public RolePermission add(RolePermission rolePermission) {
        return repository.save(rolePermission);
    }

    @Override
    public RolePermission update(RolePermission rolePermission) {
        return repository.save(rolePermission);
    }

    @Override
    public void delete(RolePermissonId rolePermissonId) {
        repository.deleteById(rolePermissonId);
    }

    @Override
    public RolePermission get(RolePermissonId rolePermissonId) {
        Optional<RolePermission> optRolePermission = this.repository.findById(rolePermissonId);
        return optRolePermission.orElse(null);
    }

    @Override
    public List<RolePermission> getAll() {
        return repository.findAll();
    }


    public List<Integer> getRoleByPermissionId(int permissionId){
        return  repository.findRoleIdByPermissionId(permissionId);
    }

    public List<Integer> getPermissionIdbyRoleId(int roleId){
        return repository.findPermissionIdByRoleId(roleId);
    }
}

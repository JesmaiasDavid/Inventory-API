package inventoryservice.service.admin;

import inventoryservice.domain.admin.Role;
import inventoryservice.domain.admin.RoleUser;
import inventoryservice.domain.admin.RoleUserId;
import inventoryservice.domain.user.User;
import inventoryservice.repository.admin.RoleUserRepository;
import inventoryservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleUserService implements IService<RoleUser, RoleUserId> {

    private static RoleUserService service = null;

    @Autowired
    private RoleUserRepository repository;



    public static RoleUserService getService(){
        if (service == null) service = new RoleUserService();
        return service;
    }


    @Override
    public RoleUser add(RoleUser roleUser) {
        return repository.save(roleUser);
    }

    @Override
    public RoleUser update(RoleUser roleUser) {
        return repository.save(roleUser);
    }

    @Override
    public void delete(RoleUserId roleUserId) {
               repository.deleteById(roleUserId);
    }

    @Override
    public RoleUser get(RoleUserId roleUserId) {
        Optional<RoleUser> optRoleUser = this.repository.findById(roleUserId);
        return optRoleUser.orElse(null);
    }

    @Override
    public List<RoleUser> getAll() {
        return repository.findAll();
    }

    public List<User> getUserByRole(Role role){
        return repository.findUserIdByRole(role);
    }

    public  List<Role> getRoleByUser(User user){
        return  repository.findRoleByUser(user);
    }
}

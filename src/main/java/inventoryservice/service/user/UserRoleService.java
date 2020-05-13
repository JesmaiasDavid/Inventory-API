package inventoryservice.service.user;

import inventoryservice.domain.admin.Role;
import inventoryservice.domain.user.User;
import inventoryservice.domain.user.UserRole;
import inventoryservice.domain.user.UserRoleId;
import inventoryservice.repository.user.UserRoleRepository;
import inventoryservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService implements IService<UserRole, UserRoleId> {

    private static UserRoleService service = null;

    @Autowired
    private UserRoleRepository repository;



    public static UserRoleService getService(){
        if (service == null) service = new UserRoleService();
        return service;
    }

    @Override
    public UserRole add(UserRole userRole) {
        return repository.save(userRole);
    }

    @Override
    public UserRole update(UserRole userRole) {
        return repository.save(userRole);
    }

    @Override
    public void delete(UserRoleId userRoleId) {
        repository.deleteById(userRoleId);
    }

    @Override
    public UserRole get(UserRoleId userRoleId) {
        Optional<UserRole> optUserRole = this.repository.findById(userRoleId);
        return optUserRole.orElse(null);
    }

    @Override
    public List<UserRole> getAll() {
        return repository.findAll();
    }

    public List<Integer> getUserbyRoleId(int roleId){
        return  repository.findUserIdIdByRoleId(roleId);
    }

    public List<Integer> getRolebyUserId(int userId){
        return repository.findRoleIdByUserId(userId);
    }
}

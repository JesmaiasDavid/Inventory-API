package inventoryservice.service.user;

import inventoryservice.domain.user.User;
import inventoryservice.repository.user.UserRepository;
import inventoryservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User,Integer> {

    private static UserService service = null;

    @Autowired
    private UserRepository repository;



    public static UserService getService(){
        if (service == null) service = new UserService();
        return service;
    }


    @Override
    public User add(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(Integer integer) {
        repository.deleteById(integer);

    }

    @Override
    public User get(Integer integer) {
        Optional<User> optUser = this.repository.findById(integer);
        return optUser.orElse(null);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
}

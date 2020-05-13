package inventoryservice.repository.user;

import inventoryservice.domain.admin.Role;
import inventoryservice.domain.user.User;
import inventoryservice.domain.user.UserRole;
import inventoryservice.domain.user.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    @Query("select user from UserRole u where u.role in(?1)")
    List<Integer> findUserIdIdByRoleId(int role);

    @Query("select role from  UserRole u where u.user in(?1)")
    List<Integer> findRoleIdByUserId(int user);

//    @Query("select role from  UserRole u where u.user in(?1)")
//    List<Role> findRoleByUser(User user);
//
//    @Query("select user from  UserRole u where u.role in(?1)")
//    List<User> findUserByRole(Role role);

}

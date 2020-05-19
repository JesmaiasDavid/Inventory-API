package inventoryservice.repository.admin;

import inventoryservice.domain.admin.Role;
import inventoryservice.domain.admin.RoleUser;
import inventoryservice.domain.admin.RoleUserId;
import inventoryservice.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, RoleUserId> {

    @Query("select user from RoleUser u where u.role in(?1)")
    List<User> findUserIdByRole(Role role);

    @Query("select role from  RoleUser u where u.user in(?1)")
    List<Role> findRoleByUser(User user);



}

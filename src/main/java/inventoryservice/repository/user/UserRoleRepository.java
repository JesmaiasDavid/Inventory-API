package inventoryservice.repository.user;

import inventoryservice.domain.user.UserRole;
import inventoryservice.domain.user.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    @Query("select userId from UserRole u where u.roleId in(?1)")
    List<Integer> findUserIdIdByRoleId(int roleId);

    @Query("select roleId from  UserRole u where u.userId in(?1)")
    List<Integer> findRoleIdByUserId(int userId);
}

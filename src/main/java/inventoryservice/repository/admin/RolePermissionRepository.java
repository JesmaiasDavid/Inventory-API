package inventoryservice.repository.admin;

import inventoryservice.domain.admin.RolePermission;
import inventoryservice.domain.admin.RolePermissonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissonId> {

    @Query("select roleId from RolePermission u where u.permissionId in(?1)")
    List<Integer> findRoleIdByPermissionId(int permissionId);

    @Query("select permissionId from  RolePermission u where u.roleId in(?1)")
    List<Integer> findPermissionIdByRoleId(int roleId);
}

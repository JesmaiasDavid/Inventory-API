package inventoryservice.repository.admin;

import inventoryservice.domain.admin.Permission;
import inventoryservice.domain.admin.Role;
import inventoryservice.domain.admin.RolePermission;
import inventoryservice.domain.admin.RolePermissonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissonId> {

    @Query("select role from RolePermission u where u.permission in(?1)")
    List<Role> findRoleIdByPermissionId(Permission permission);

    @Query("select permission from  RolePermission u where u.role in(?1)")
    List<Permission> findPermissionIdByRoleId(Role role);
}

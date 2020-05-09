package inventoryservice.domain.admin;

import java.io.Serializable;
import java.util.Objects;

public class RolePermissonId implements Serializable {

    private int roleId;

    private int permissionId;

    RolePermissonId(){}

    public RolePermissonId(int roleId, int permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RolePermissonId)) return false;
        RolePermissonId that = (RolePermissonId) o;
        return roleId == that.roleId &&
                permissionId == that.permissionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, permissionId);
    }
}

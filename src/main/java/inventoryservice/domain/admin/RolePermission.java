package inventoryservice.domain.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class RolePermission implements Serializable {

    @EmbeddedId
    RolePermissonId id;

    @JsonIgnore
    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_Id")
    private Role role;

    @JsonIgnore
    @MapsId("permissionId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="permission_Id")
    private Permission permission;

    public  RolePermission(){}

    public RolePermission(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
    }

    public RolePermissonId getId() {
        return id;
    }

    public void setId(RolePermissonId id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "id=" + id +
                ", role=" + role +
                ", permission=" + permission +
                '}';
    }
}

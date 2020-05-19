package inventoryservice.domain.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="role_seq")
    @SequenceGenerator(
            name="role_seq",
            sequenceName="role_sequence",
            allocationSize=1)
    private int roleId;

    private String roleName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    private String createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDateTime;

    private String lastModifiedUser;

    @JsonIgnore
    @OneToMany(mappedBy="role")
    private List<RoleUser> roleUsers;

    @JsonIgnore
    @OneToMany(mappedBy="role")
    private List<RolePermission> rolePermissions;

    Role() {
    }

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public List<RoleUser> getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(List<RoleUser> roleUsers) {
        this.roleUsers = roleUsers;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(Date lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }



    public RoleUser addRoleUser(RoleUser roleUser) {
        getRoleUsers().add(roleUser);
        roleUser.setRole(this);

        return roleUser;
    }

    public RoleUser removeRoleUser(RoleUser roleUser) {
        getRoleUsers().remove(roleUser);
        roleUser.setRole(null);
        return roleUser;
    }

    public RolePermission addRolePermission(RolePermission rolePermission) {
        getRolePermissions().add(rolePermission);
        rolePermission.setRole(this);

        return rolePermission;
    }

    public RolePermission removeRolePermission(RolePermission rolePermission) {
        getRolePermissions().remove(rolePermission);
        rolePermission.setRole(null);

        return rolePermission;
    }

    @PreUpdate
    public void preUpdate() {
        lastModifiedDateTime = new Date();
    }


    @PrePersist
    public void prePersist() {
        Date now = new Date();
        lastModifiedDateTime = now;
        createdDateTime=now;
    }


    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", createdUser='" + createdUser + '\'' +
                ", lastModifiedDateTime=" + lastModifiedDateTime +
                ", lastModifiedUser='" + lastModifiedUser + '\'' +
                '}';
    }
}

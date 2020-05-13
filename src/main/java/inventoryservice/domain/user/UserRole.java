package inventoryservice.domain.user;

import inventoryservice.domain.admin.Role;

import javax.persistence.*;

@Entity
public class UserRole {

   @EmbeddedId
   UserRoleId id;

    //bi-directional many-to-one association to User

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name="user_id")
    private User user;

    //bi-directional many-to-one association to Role
    @ManyToOne
    @MapsId("role_id")
    @JoinColumn(name="role_id")
    private Role role;
public UserRole(){}
    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }


    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", user=" + user +
                ", role=" + role +
                '}';
    }
}

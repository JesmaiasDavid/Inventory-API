package inventoryservice.domain.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inventoryservice.domain.user.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class RoleUser implements Serializable {

    @EmbeddedId
    private RoleUserId id;

    @JsonIgnore
    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_Id")
    private Role role;

    @JsonIgnore
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_Id")
    private User user;

    public RoleUser(){}

    public RoleUser(Role role, User user) {
        this.role = role;
        this.user = user;
    }

    public RoleUserId getId() {
        return id;
    }

    public void setId(RoleUserId id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RoleUser{" +
                "id=" + id +
                ", role=" + role +
                ", user=" + user +
                '}';
    }
}

package inventoryservice.domain.admin;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;

    private String roleName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    private String createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDateTime;

    private String lastModifiedUser;

    Role() {
    }

    Role(Builder builder) {
        this.roleId = builder.roleId;
        this.roleName = builder.roleName;
        this.createdDateTime = builder.createdDateTime;
        this.createdUser = builder.createdUser;
        this.lastModifiedDateTime = builder.lastModifiedDateTime;
        this.lastModifiedUser = builder.lastModifiedUser;
    }

    public int getRoleId() {
        return this.roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public Date getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
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

    public static class Builder {

        private int roleId;
        private String roleName;

        private Date createdDateTime;

        private String createdUser;


        private Date lastModifiedDateTime;

        private String lastModifiedUser;

        public Builder roleId(int roleId) {
            this.roleId = roleId;
            return this;
        }

        public Builder roleName(String roleName) {
            this.roleName = roleName;
            return this;
        }

        public Builder createdDateTime(Date createdDateTime) {
            this.createdDateTime = createdDateTime;
            return this;
        }

        public Builder createdUser(String createdUser) {
            this.createdUser = createdUser;
            return this;
        }

        public Builder lastModifiedDateTime(Date lastModifiedDateTime) {
            this.lastModifiedDateTime = lastModifiedDateTime;
            return this;
        }

        public Builder lastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
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

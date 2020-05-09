package inventoryservice.domain.user;


import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private String userAddress;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    private String createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDateTime;

    private String lastModifiedUser;

    User() {
    }

    User(Builder builder) {
        this.userId = builder.userId;
        this.userFirstName = builder.userFirstName;
        this.userLastName = builder.userLastName;
        this.userEmail = builder.userEmail;
        this.userAddress = builder.userAddress;
        this.createdDateTime = builder.createdDateTime;
        this.createdUser = builder.createdUser;
        this.lastModifiedDateTime = builder.lastModifiedDateTime;
        this.lastModifiedUser = builder.lastModifiedUser;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public String getUserFirstName() {
        return this.userFirstName;
    }

    public String getUserLastName() {
        return this.userLastName;
    }

    public String getUserAddress() {
        return this.userAddress;
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

        private int userId;
        private String userEmail;
        private String userFirstName;
        private String userLastName;
        private String userAddress;


        private Date createdDateTime;

        private String createdUser;


        private Date lastModifiedDateTime;

        private String lastModifiedUser;

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder userEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public Builder userFirstName(String userFirstName) {
            this.userFirstName = userFirstName;
            return this;
        }

        public Builder userLastName(String userLastName) {
            this.userLastName = userLastName;
            return this;
        }

        public Builder userAddress(String userAddress) {
            this.userAddress = userAddress;
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

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", createdUser='" + createdUser + '\'' +
                ", lastModifiedDateTime=" + lastModifiedDateTime +
                ", lastModifiedUser='" + lastModifiedUser + '\'' +
                '}';
    }
}

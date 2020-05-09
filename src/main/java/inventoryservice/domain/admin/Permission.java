package inventoryservice.domain.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int permissionId;

    private String permissionName;

    Permission(){}

    Permission(Builder builder){

    }


    public int getPermissionId() {
        return this.permissionId;
    }

    public String getPermissionName() {
        return this.permissionName;
    }

    public static class Builder {

        private int permissionId;
        private String permissionName;

        public Builder permissionId(int permissionId) {
            this.permissionId = permissionId;
            return this;
        }

        public Builder permissionName(String permissionName) {
            this.permissionName = permissionName;
            return this;
        }

        public Permission build(){
            return new Permission(this);
        }
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                '}';
    }
}

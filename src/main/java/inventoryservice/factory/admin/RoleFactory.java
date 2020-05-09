package inventoryservice.factory.admin;

import inventoryservice.domain.admin.Role;

import java.util.Date;

public class RoleFactory {

    public static Role getRole(String role, Date createdDateTime, String createdUser, Date lastModifiedDateTime, String lastModifiedUser){

        return new Role.Builder()
                .roleName(role)
                .createdDateTime(createdDateTime)
                .createdUser(createdUser)
                .lastModifiedDateTime(lastModifiedDateTime)
                .lastModifiedUser(lastModifiedUser)
                .build();
    }
}

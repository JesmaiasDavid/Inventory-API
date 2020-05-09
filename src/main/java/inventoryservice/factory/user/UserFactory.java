package inventoryservice.factory.user;

import inventoryservice.domain.user.User;

import java.util.Date;

public class UserFactory {

    public static User getUser(String userFirstName, String userLastName, String userEmail, String userAddress, Date createdDateTime, String createdUser, Date lastModifiedDateTime, String lastModifiedUser ){

        return new User
                 .Builder()
                .userFirstName(userFirstName)
                .userLastName(userLastName)
                .userEmail(userEmail)
                .userAddress(userAddress)
                .createdDateTime(createdDateTime)
                .createdUser(createdUser)
                .lastModifiedDateTime(lastModifiedDateTime)
                .lastModifiedUser(lastModifiedUser)
                .build();
    }
}

package inventoryservice.factory.admin;

import inventoryservice.domain.admin.Category;

import java.util.Date;

public class CategoryFactory {

    public static Category getCategory(String  categoryName, String createdUser, String lastModifiedUser){

        return  new Category.Builder()
                .name(categoryName)
                .createdUser(createdUser)
                .lastModifiedUser(lastModifiedUser)
                .build();
    }
}

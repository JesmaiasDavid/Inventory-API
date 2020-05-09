package inventoryservice.factory.admin;

import inventoryservice.domain.admin.Product;

import java.util.Date;

public class ProductFactory {

    public static Product getProduct(String productName,double productBuyingPrice,double productSellingPrice,String createdUser, String lastModifiedUser){
        return new Product.Builder()
                .productName(productName)
                .productBuyingPrice(productBuyingPrice)
                .productSellingPrice(productSellingPrice)
                .createdUser(createdUser)
                .lastModifiedUser(lastModifiedUser)
                .build();
    }
}

package inventoryservice.factory.admin;

import inventoryservice.domain.admin.Stock;

import java.util.Date;

public class StockFactory {

    public static Stock getStock(double stockQty, int productId, Date createdDateTime, String createdUser, Date lastModifiedDateTime, String lastModifiedUser ){
        return new Stock.Builder()
                .stockQuantity(stockQty)
                .productId(productId)
                .createdDateTime(createdDateTime)
                .createdUser(createdUser)
                .lastModifiedDateTime(lastModifiedDateTime)
                .lastModifiedUser(lastModifiedUser)
                .build();
    }
}

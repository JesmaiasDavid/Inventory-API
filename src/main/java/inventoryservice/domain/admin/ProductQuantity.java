package inventoryservice.domain.admin;

import java.io.Serializable;

public class ProductQuantity implements Serializable {


    private int productId;

    private int quantity;

    public ProductQuantity() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    @Override
    public String toString() {
        return "ProductQuantity{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}

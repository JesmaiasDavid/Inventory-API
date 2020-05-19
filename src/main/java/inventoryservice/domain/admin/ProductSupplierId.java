package inventoryservice.domain.admin;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductSupplierId implements Serializable {

    private int productId;

    private int supplierId;

    public ProductSupplierId() {
    }

    public ProductSupplierId(int productId, int supplierId) {
        this.productId = productId;
        this.supplierId = supplierId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductSupplierId)) return false;
        ProductSupplierId that = (ProductSupplierId) o;
        return productId == that.productId &&
                supplierId == that.supplierId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, supplierId);
    }
}

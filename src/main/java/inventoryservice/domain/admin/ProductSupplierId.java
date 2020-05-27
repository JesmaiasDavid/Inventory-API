package inventoryservice.domain.admin;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class ProductSupplierId implements Serializable {

    private int productId;

    private int supplierId;

    private Date now;

    public ProductSupplierId() {
    }

    public ProductSupplierId(int productId, int supplierId, Date now) {
        this.productId = productId;
        this.supplierId = supplierId;
        this.now=now;
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

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductSupplierId)) return false;
        ProductSupplierId that = (ProductSupplierId) o;
        return productId == that.productId &&
                supplierId == that.supplierId &&
                Objects.equals(now, that.now);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, supplierId, now);
    }
}

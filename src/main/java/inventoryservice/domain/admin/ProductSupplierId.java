package inventoryservice.domain.admin;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class ProductSupplierId implements Serializable {

    private int productId;

    private int supplierId;

    private Date createdDateTime;

     public ProductSupplierId() {
    }

    public ProductSupplierId(int productId, int supplierId, Date createdDateTime) {
        this.productId = productId;
        this.supplierId = supplierId;
        this.createdDateTime = createdDateTime;
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

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSupplierId that = (ProductSupplierId) o;
        return productId == that.productId &&
                supplierId == that.supplierId &&
                Objects.equals(createdDateTime, that.createdDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, supplierId, createdDateTime);
    }
}

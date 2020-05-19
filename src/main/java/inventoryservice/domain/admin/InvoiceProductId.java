package inventoryservice.domain.admin;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InvoiceProductId implements Serializable {

    private int invoiceId;

    private int productId;

    InvoiceProductId(){}

    public InvoiceProductId(int invoiceId, int productId) {
        this.invoiceId = invoiceId;
        this.productId = productId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceProductId)) return false;
        InvoiceProductId that = (InvoiceProductId) o;
        return invoiceId == that.invoiceId &&
                productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, productId);
    }
}

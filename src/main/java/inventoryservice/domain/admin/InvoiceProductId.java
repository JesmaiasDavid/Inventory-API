package inventoryservice.domain.admin;

import java.io.Serializable;
import java.util.Objects;

public class InvoiceProductId implements Serializable {

    private int invoiceId;

    private int productId;

    InvoiceProductId(){}

    public InvoiceProductId(int invoiceId, int productId) {
        this.invoiceId = invoiceId;
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

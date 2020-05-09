package inventoryservice.domain.admin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(InvoiceProductId.class)
public class InvoiceProduct {

    @Id
    private int invoiceId;

    @Id
    private  int productId;

    private int productQuantity;

    public InvoiceProduct(int invoiceId, int productId, int productQuantity) {
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.productQuantity=productQuantity;
    }

    public InvoiceProduct(){}

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

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "InvoiceProduct{" +
                "invoiceId=" + invoiceId +
                ", productId=" + productId +
                ", productQuantity=" + productQuantity +
                '}';
    }
}

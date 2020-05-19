package inventoryservice.domain.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class InvoiceProduct implements Serializable {

    @EmbeddedId
    private InvoiceProductId id;

    @JsonIgnore
    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_Id")
    private Product product;

    @JsonIgnore
    @MapsId("invoiceId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="invoice_Id")
    private Invoice invoice;


    private int productQuantity;

    public InvoiceProduct(Product product, Invoice invoice, int productQuantity) {
        this.product = product;
        this.invoice = invoice;
        this.productQuantity = productQuantity;
    }

    public InvoiceProduct(){}


    public InvoiceProductId getId() {
        return id;
    }

    public void setId(InvoiceProductId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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
                "id=" + id +
                ", product=" + product +
                ", invoice=" + invoice +
                ", productQuantity=" + productQuantity +
                '}';
    }
}

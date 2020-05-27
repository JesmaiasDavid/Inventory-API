/*

    THERE  IS A PROBLEM WITH THE DATE GENERATED, WHEN THE INVOICE IS UPDATED THE DATE GENERATED FIELD BECOMES NULL

*/
package inventoryservice.domain.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invoice_seq")
    @SequenceGenerator(
            name="invoice_seq",
            sequenceName="invoice_sequence",
            allocationSize=1)
    private int invoiceId;

    private double total;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeGenerated;



    @JsonIgnore
    @OneToMany(mappedBy="invoice")
    private List<InvoiceProduct> invoiceProducts;

    public Invoice(){}

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        dateTimeGenerated=now;
    }

    public List<InvoiceProduct> getInvoiceProducts() {
        return invoiceProducts;
    }

    public void setInvoiceProducts(List<InvoiceProduct> invoiceProducts) {
        this.invoiceProducts = invoiceProducts;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateTimeGenerated() {
        return dateTimeGenerated;
    }

    public void setDateTimeGenerated(Date dateTimeGenerated) {
        this.dateTimeGenerated = dateTimeGenerated;
    }

    public InvoiceProduct addInvoiceProduct(InvoiceProduct invoiceProduct) {
        getInvoiceProducts().add(invoiceProduct);
        invoiceProduct.setInvoice(this);

        return invoiceProduct;
    }

    public InvoiceProduct removeRolePermission(InvoiceProduct invoiceProduct) {
        getInvoiceProducts().remove(invoiceProduct);
        invoiceProduct.setInvoice(null);

        return invoiceProduct;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", dateTimeGenerated=" + dateTimeGenerated +
                '}';
    }
}

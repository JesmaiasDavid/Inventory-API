/*

    THERE  IS A PROBLEM WITH THE DATE GENERATED, WHEN THE INVOICE IS UPDATED THE DATE GENERATED FIELD BECOMES NULL

*/
package inventoryservice.domain.admin;

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


    public Invoice(){}

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        dateTimeGenerated=now;
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

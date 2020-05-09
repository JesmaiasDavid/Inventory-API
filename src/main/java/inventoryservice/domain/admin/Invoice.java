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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int invoiceId;

    private double total;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeGenerated;


    public Invoice(){}

    public Invoice(Builder builder)
    {
        this.invoiceId=builder.invoiceId;
        this.total=builder.total;
      this.dateTimeGenerated=builder.dateTimeGenerated;
      this.status=builder.status;
    }

    public int getInvoiceId(){
        return this.invoiceId;
    }

    public Date getDateTimeGenerated() {
        return dateTimeGenerated;
    }

    public double getTotal(){
        return this.total;
    }

    public String getStatus() {
        return status;
    }

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        dateTimeGenerated = now;
    }

    public static class Builder{

        private int invoiceId;

        private String status;
        private double total;

        private Date dateTimeGenerated;

        public Builder invoiceId(int invoiceId){
            this.invoiceId=invoiceId;
            return this;
        }

        public Builder status(String status){
            this.status=status;
            return  this;
        }

        public Builder total(double total){
            this.total=total;
            return this;
        }

        public Builder dateTimeGenerated(Date dateTimeGenerated){
            this.dateTimeGenerated=dateTimeGenerated;
            return this;
        }

        public Invoice build(){
            return new Invoice(this);
        }
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

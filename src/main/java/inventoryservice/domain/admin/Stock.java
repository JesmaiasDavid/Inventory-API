package inventoryservice.domain.admin;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int stockId;

    private double stockQuantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    private String createdUser;

    @Temporal(TemporalType.DATE)
    private Date dateStock;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDateTime;

    private String lastModifiedUser;

    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;

    //bi-directional many-to-one association to Category
    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name="supplierId")
    private Supplier supplier;





    Stock(){}

    Stock (Builder builder){

        this.stockId=builder.stockId;
        this.stockQuantity=builder.stockQuantity;
        this.createdDateTime=builder.createdDateTime;
        this.createdUser=builder.createdUser;
        this.lastModifiedDateTime=builder.lastModifiedDateTime;
        this.lastModifiedUser=builder.lastModifiedUser;
        this.dateStock=builder.dateStock;
    }

    public int getStockId(){
        return this.stockId;
    }

    public double getStockQuantity(){
        return this.stockQuantity;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public Date getDateStock() {
        return dateStock;
    }

    public Date getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    @PreUpdate
    public void preUpdate() {
        lastModifiedDateTime = new Date();
    }


    @PrePersist
    public void prePersist() {
        Date now = new Date();
        lastModifiedDateTime = now;
        createdDateTime=now;
        dateStock=now;
    }

    public static class Builder{

        private int stockId;
        private int productId;
        private double stockQuantity;

        private Date createdDateTime;

        private String createdUser;

        private Date dateStock;

        private Date lastModifiedDateTime;

        private String lastModifiedUser;


        public Builder stockId(int stockId){
            this.stockId=stockId;
            return this;
        }

        public Builder productId(int productId){
            this.productId=productId;
            return this;
        }

        public Builder stockQuantity(double stockQuantity){
            this.stockQuantity=stockQuantity;
            return this;
        }

        public Builder createdDateTime(Date createdDateTime){
            this.createdDateTime=createdDateTime;
            return this;
        }

        public Builder createdUser(String createdUser){
            this.createdUser=createdUser;
            return this;
        }

        public Builder lastModifiedDateTime(Date lastModifiedDateTime){
            this.lastModifiedDateTime=lastModifiedDateTime;
            return this;
        }

        public Builder dateStock(Date dateStock){
            this.dateStock=dateStock;
            return  this;
        }

        public Builder lastModifiedUser(String lastModifiedUser){
            this.lastModifiedUser=lastModifiedUser;
            return this;
        }

        public Stock build(){
            return new Stock(this);
        }
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", stockQuantity=" + stockQuantity +
                ", createdDateTime=" + createdDateTime +
                ", createdUser='" + createdUser + '\'' +
                ", dateStock=" + dateStock +
                ", lastModifiedDateTime=" + lastModifiedDateTime +
                ", lastModifiedUser='" + lastModifiedUser + '\'' +
                '}';
    }
}

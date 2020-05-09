package inventoryservice.domain.admin;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    private String productName;

    private double productBuyingPrice;

    private double productSellingPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    private String createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDateTime;

    private String lastModifiedUser;

    //bi-directional many-to-one association to Category
    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;

    @OneToMany(mappedBy="product")
    private List<Stock> stocks;

    @PreUpdate
    public void preUpdate() {
        lastModifiedDateTime = new Date();
    }


    @PrePersist
    public void prePersist() {
        Date now = new Date();
        lastModifiedDateTime = now;
        createdDateTime=now;
    }

    Product() {
    }

    Product(Builder builder) {
        this.productId = builder.productId;
        this.productName = builder.productName;
        this.productSellingPrice = builder.productSellingPrice;
        this.productBuyingPrice=builder.productBuyingPrice;
        this.createdDateTime = builder.createdDateTime;
        this.createdUser = builder.createdUser;
        this.lastModifiedDateTime = builder.lastModifiedDateTime;
        this.lastModifiedUser = builder.lastModifiedUser;
    }

    public int getProductId() {
        return this.productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public Date getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    public double getProductBuyingPrice() {
        return productBuyingPrice;
    }

    public double getProductSellingPrice() {
        return productSellingPrice;
    }


    public List<Stock> getStocks() {
        return stocks;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void addCategory(Category category){
        setCategory(category);
    }

    public static class Builder {

        private int productId;
        private String productName;
        private double productBuyingPrice;
        private double productSellingPrice;
        private Date createdDateTime;
        private String createdUser;
        private Date lastModifiedDateTime;
        private String lastModifiedUser;

        public Builder productId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder productSellingPrice(double productSellingPrice) {
            this.productSellingPrice = productSellingPrice;
            return this;
        }

        public Builder productBuyingPrice(double productBuyingPrice) {
            this.productBuyingPrice = productBuyingPrice;
            return this;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder createdDateTime(Date createdDateTime) {
            this.createdDateTime = createdDateTime;
            return this;
        }

        public Builder createdUser(String createdUser) {
            this.createdUser = createdUser;
            return this;
        }

        public Builder lastModifiedDateTime(Date lastModifiedDateTime) {
            this.lastModifiedDateTime = lastModifiedDateTime;
            return this;
        }

        public Builder lastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }


    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productBuyingPrice=" + productBuyingPrice +
                ", productSellingPrice=" + productSellingPrice +
                ", createdDateTime=" + createdDateTime +
                ", createdUser='" + createdUser + '\'' +
                ", lastModifiedDateTime=" + lastModifiedDateTime +
                ", lastModifiedUser='" + lastModifiedUser + '\'' +
                ", category=" + category +
                ", stocks=" + stocks +
                '}';
    }
}

package inventoryservice.domain.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category  {


    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="category_seq")
    @SequenceGenerator(
          name="category_seq",
         sequenceName="category_sequence",
         allocationSize=1)
    private int id;

    private String categoryName;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    private String createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDateTime;

    private String lastModifiedUser;


    //bi-directional many-to-one association to Product
    @JsonIgnore
    @OneToMany(mappedBy="category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    //bi-directional many-to-one association to Stock
    @OneToMany(mappedBy="category")
    private List<Stock> stocks;



    public Category(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(Date lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }


    public List<Product> getProducts() {
        return products;
    }


    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @JsonIgnore
    public List<Stock> getStocks() {
        return stocks;
    }

    @JsonIgnore
    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }



    public void addProduct(Product product) {
        getProducts().add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product) {
        getProducts().remove(product);
        product.setCategory(null);
    }

    @PreUpdate
    public void preUpdate() {
        lastModifiedDateTime = new Date();
    }


    @PrePersist
    public void prePersist() {
        Date now = new Date();
        createdDateTime=now;
        lastModifiedDateTime = now;
    }



    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + categoryName + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", createdUser='" + createdUser + '\'' +
                ", lastModifiedDateTime=" + lastModifiedDateTime +
                ", lastModifiedUser='" + lastModifiedUser + '\'' +
                '}';
    }
}

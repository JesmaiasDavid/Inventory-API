package inventoryservice.domain.admin;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String categoryName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    private String createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDateTime;

    private String lastModifiedUser;

    //bi-directional many-to-one association to Product
    @OneToMany(mappedBy="category", cascade = CascadeType.ALL)
    private List<Product> products;

    //bi-directional many-to-one association to Stock
    @OneToMany(mappedBy="category")
    private List<Stock> stocks;



    public Category(){}

   public Category(Builder builder)
   {
     this.id=builder.id;
     this.categoryName=builder.categoryName;
     this.createdDateTime=builder.createdDateTime;
     this.createdUser=builder.createdUser;
     this.lastModifiedDateTime=builder.lastModifiedDateTime;
     this.lastModifiedUser=builder.lastModifiedUser;
   }

   public int getId(){
       return this.id;
   }

   public String getCategoryName(){
       return this.categoryName;
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

    public List<Stock> getStocks() {
        return stocks;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public void addProduct(Product product) {
        getProducts().add(product);
        product.setCategory(this);
    }

    @PreUpdate
    public void preUpdate() {
        lastModifiedDateTime = new Date();
    }


    @PrePersist
    public void prePersist() {
        Date now = new Date();
        lastModifiedDateTime = now;
    }



    public static class Builder{

        private int id;
        private String categoryName;

       private Date createdDateTime;

       private String createdUser;


       private Date lastModifiedDateTime;

       private String lastModifiedUser;

        public Builder id(int id){
          this.id=id;
          return this;
        }

        public Builder name(String name){
            this.categoryName=name;
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

       public Builder lastModifiedUser(String lastModifiedUser){
           this.lastModifiedUser=lastModifiedUser;
           return this;
       }

        public Category build(){
            return new Category(this);
        }
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

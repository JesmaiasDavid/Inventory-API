package inventoryservice.domain.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="product_seq")
    @SequenceGenerator(
            name="product_seq",
            sequenceName="product_sequence",
            allocationSize=1)
    private int productId;

    private String productName;

    private double productBuyingPrice;

    private double productSellingPrice;

    private  double productQuantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    private String createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDateTime;

    private String lastModifiedUser;

    //bi-directional many-to-one association to Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryId")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy="product")
    private List<InvoiceProduct> invoiceProducts;

    @OneToMany(mappedBy="product")
    private List<Stock> stocks;


    @JsonIgnore
    @OneToMany(mappedBy="product")
    private List<ProductSupplier>  productSuppliers;

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

    public List<InvoiceProduct> getInvoiceProducts() {
        return invoiceProducts;
    }

    public void setInvoiceProducts(List<InvoiceProduct> invoiceProducts) {
        this.invoiceProducts = invoiceProducts;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductBuyingPrice() {
        return productBuyingPrice;
    }

    public void setProductBuyingPrice(double productBuyingPrice) {
        this.productBuyingPrice = productBuyingPrice;
    }

    public double getProductSellingPrice() {
        return productSellingPrice;
    }

    public void setProductSellingPrice(double productSellingPrice) {
        this.productSellingPrice = productSellingPrice;
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

    public double getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(double productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public List<ProductSupplier> getProductSuppliers() {
        return productSuppliers;
    }

    public void setProductSuppliers(List<ProductSupplier> productSuppliers) {
        this.productSuppliers = productSuppliers;
    }

    public void addProductSupplier(ProductSupplier productSupplier) {
        getProductSuppliers().add(productSupplier);
        productSupplier.setProduct(this);
    }

    public void removeProduct(ProductSupplier productSupplier) {
        getProductSuppliers().remove(productSupplier);
        productSupplier.setProduct(null);
    }

    public InvoiceProduct addInvoiceProduct(InvoiceProduct invoiceProduct) {
        getInvoiceProducts().add(invoiceProduct);
        invoiceProduct.setProduct(this);

        return invoiceProduct;
    }

    public InvoiceProduct removeRolePermission(InvoiceProduct invoiceProduct) {
        getInvoiceProducts().remove(invoiceProduct);
        invoiceProduct.setProduct(null);

        return invoiceProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productBuyingPrice=" + productBuyingPrice +
                ", productSellingPrice=" + productSellingPrice +
                ", productQuantity=" + productQuantity +
                ", createdDateTime=" + createdDateTime +
                ", createdUser='" + createdUser + '\'' +
                ", lastModifiedDateTime=" + lastModifiedDateTime +
                ", lastModifiedUser='" + lastModifiedUser + '\'' +
                ", category=" + category +
                '}';
    }
}

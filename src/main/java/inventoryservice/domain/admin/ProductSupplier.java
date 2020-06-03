package inventoryservice.domain.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductSupplier implements Serializable {

    @EmbeddedId
    private ProductSupplierId id;

    @JsonIgnore
    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @JsonIgnore
    @MapsId("supplierId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

   private double quantitySupplied;

    public ProductSupplier() {
    }

    public ProductSupplier(Product product, Supplier supplier) {
        this.product = product;
        this.supplier = supplier;
    }

    public ProductSupplierId getId() {
        return id;
    }

    public void setId(ProductSupplierId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public double getQuantitySupplied() {
        return quantitySupplied;
    }

    public void setQuantitySupplied(double quantitySupplied) {
        this.quantitySupplied = quantitySupplied;
    }

    @Override
    public String toString() {
        return "ProductSupplier{" +
                "id=" + id +
                ", product=" + product +
                ", supplier=" + supplier +
                ", quantitySupplied=" + quantitySupplied +
                '}';
    }
}

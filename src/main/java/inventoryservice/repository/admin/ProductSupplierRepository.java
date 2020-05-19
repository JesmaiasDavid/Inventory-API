package inventoryservice.repository.admin;

import inventoryservice.domain.admin.Product;
import inventoryservice.domain.admin.ProductSupplier;
import inventoryservice.domain.admin.ProductSupplierId;
import inventoryservice.domain.admin.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, ProductSupplierId> {

    @Query("select product from ProductSupplier u where u.supplier in(?1)")
    List<Product> findProductBySupplier(Supplier supplier);

    @Query("select supplier from ProductSupplier u where u.product in(?1)")
    List<Supplier> findSupplierByProduct(Product product);
}

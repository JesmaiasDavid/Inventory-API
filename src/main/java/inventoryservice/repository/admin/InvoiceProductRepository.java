package inventoryservice.repository.admin;

import inventoryservice.domain.admin.Invoice;
import inventoryservice.domain.admin.InvoiceProduct;
import inventoryservice.domain.admin.InvoiceProductId;
import inventoryservice.domain.admin.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, InvoiceProductId> {

    @Query("select invoice from InvoiceProduct u where u.product in(?1)")
    List<Invoice> findInvoiceIdByProductId(Product product);

    @Query("select product from  InvoiceProduct u where u.invoice in(?1)")
    List<Product> findProductIdByInvoiceId(Invoice invoice);

//    @Query ("select productId, productQuantity from InvoiceProduct u where u.invoiceId in (?1)")
//    List<Integer> findByInvoiceId(int invoiceId);
//
//
//    @Query("select productQuantity from  InvoiceProduct u where u.invoiceId in(?1)")
//    List<Integer> findQuantityIdByInvoiceId(int invoiceId);

}

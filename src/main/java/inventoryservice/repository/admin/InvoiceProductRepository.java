package inventoryservice.repository.admin;

import inventoryservice.domain.admin.InvoiceProduct;
import inventoryservice.domain.admin.InvoiceProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, InvoiceProductId> {

    @Query("select invoiceId from InvoiceProduct u where u.productId in(?1)")
    List<Integer> findInvoiceIdByProductId(int productId);

    @Query("select productId from  InvoiceProduct u where u.invoiceId in(?1)")
    List<Integer> findProductIdByInvoiceId(int invoiceId);

    @Query ("select productId, productQuantity from InvoiceProduct u where u.invoiceId in (?1)")
    List<Integer> findByInvoiceId(int invoiceId);


    @Query("select productQuantity from  InvoiceProduct u where u.invoiceId in(?1)")
    List<Integer> findQuantityIdByInvoiceId(int invoiceId);

}

package inventoryservice.factory.admin;

import inventoryservice.domain.admin.Supplier;

import java.util.Date;

public class SupplierFactory {

    public static Supplier getSupplier(String supplierCompany, String supplierName, Date createdDateTime, String createdUser, Date lastModifiedDateTime, String lastModifiedUser ){
        return new Supplier.Builder()
                .supplierCompany(supplierCompany)
                .supplierName(supplierName)
                .createdDateTime(createdDateTime)
                .createdUser(createdUser)
                .lastModifiedDateTime(lastModifiedDateTime)
                .lastModifiedUser(lastModifiedUser)
                .build();
    }
}

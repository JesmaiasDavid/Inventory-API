package inventoryservice.factory.admin;

import inventoryservice.domain.admin.Invoice;

import java.util.Date;

public class InvoiceFactory {

    public static Invoice getInvoice(double invoiceTotal, String status,Date dateTimeGenerated){

        return  new Invoice.Builder()
                .total(invoiceTotal)
                .status(status)
                .dateTimeGenerated(dateTimeGenerated)
                .build();
    }
}

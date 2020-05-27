package inventoryservice.controller.admin;

import inventoryservice.domain.admin.*;
import inventoryservice.service.admin.InvoiceProductService;
import inventoryservice.service.admin.InvoiceService;
import inventoryservice.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InvoiceProductController {

    @Autowired
    private InvoiceProductService service;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/invoices/{invoiceId}/products", consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody List<ProductQuantity> productQuantities, @PathVariable int invoiceId) {
        Invoice invoice = invoiceService.get(invoiceId);
        List <ResponseObject> responseObjects=new ArrayList<>();
        InvoiceProduct invoiceProduct=new InvoiceProduct();
        ResponseObject responseObject = new ResponseObject(HttpStatus.OK.toString(), "InvoiceProduct Created Successfully");
        if (invoice == null) {
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, invoice  does not exist!");
        } else if (productQuantities == null) {
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Please provide a list of product Id and the quantity to buy!");
        }  else {

            for (int i=0;i<productQuantities.size();i++) {

                int productId;
                productId=productQuantities.get(i).getProductId();
                Product product=productService.get(productId);

                if(productService.existsById(productId)!=true){
                    responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
                    responseObject.setResponseDescription("This product does not exist"+product.getProductId());
                    responseObject.setResponse(null);
                    responseObjects.add(responseObject);
                }else
                if (service.existsById(new InvoiceProductId(invoice.getInvoiceId(), productId))==true){
                    responseObject.setResponseCode(HttpStatus.FORBIDDEN.toString());
                    responseObject.setResponseDescription("Invoice Product already exists"+product.getProductId());
                    responseObject.setResponse(null);
                    responseObjects.add(responseObject);
                } else {

                    invoiceProduct.setId(new InvoiceProductId(invoice.getInvoiceId(),productId));
                    invoiceProduct.setProductQuantity(productQuantities.get(i).getQuantity());
                    invoice.addInvoiceProduct(invoiceProduct);
                    product.addInvoiceProduct(invoiceProduct);
                    service.add(invoiceProduct);


                    if ("successful".equals(invoice.getStatus())) {
                        double quantityInStock = product.getProductQuantity();
                        double quantityToBuy = invoiceProduct.getProductQuantity();
                        double difference = quantityInStock - quantityToBuy;
                        product.setProductQuantity(difference);
                        productService.update(product);
                    }
                    responseObject.setResponseCode(HttpStatus.CREATED.toString());
                    responseObject.setResponseDescription("InvoiceProduct Created Successfully"+product.getProductId());
                    responseObject.setResponse(invoiceProduct);
                    responseObjects.add(responseObject);
                }

            }
        }

        for (int i=0;i<productQuantities.size();i++) {
            System.out.println(productQuantities.get(i).getProductId());
        }
        System.out.println(invoiceProduct);
        return ResponseEntity.ok(responseObjects);
    }

    @GetMapping("/products/{productId}/invoices")
    @ResponseBody
    public ResponseEntity getInvoiceByProductId(@PathVariable int productId) {
        List<Integer> listId= new ArrayList<>();
        Product product=productService.get(productId);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Invoice Ids Found Successfully");
        if (product==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this product id does not exist!");
        }else{

            List<Invoice> list=service.getInvoicebyProductId(product);
            for (int i=0;i<list.size();i++){
                listId.add(list.get(i).getInvoiceId());
            }
            responseObject.setResponse(listId);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/invoices/{invoiceId}/products")
    @ResponseBody
    public ResponseEntity getProductByInvoiceId(@PathVariable int invoiceId) {
        List<Integer> listId= new ArrayList<>();
        Invoice invoice=invoiceService.get(invoiceId);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Product Ids Found Successfully");
        if (invoice==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this invoice id does not exist!");
        }else{

            List<Product> list=service.getProductbyInvoiceId(invoice);
            for (int i=0;i<list.size();i++){
                listId.add(list.get(i).getProductId());
            }
            responseObject.setResponse(listId);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/invoices/products")
    @ResponseBody
    public ResponseEntity getAll() {
        List<InvoiceProduct> invoiceProducts = service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"All invoiceProducts Found Successfully");
        if (invoiceProducts==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, invoices not found!");
        }else{

            responseObject.setResponse(invoiceProducts);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/products/invoices")
    @ResponseBody
    public ResponseEntity getAlll() {
        List<InvoiceProduct> invoiceProducts = service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"All invoiceProducts Found Successfully");
        if (invoiceProducts==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, invoices not found!");
        }else{

            responseObject.setResponse(invoiceProducts);
        }
        return ResponseEntity.ok(responseObject);
    }



}

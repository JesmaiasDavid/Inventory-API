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
//@RequestMapping("/invoiceproduct")
public class InvoiceProductController {

    @Autowired
    private InvoiceProductService service;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/invoices/{invoiceId}/products/{productId}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody InvoiceProduct invoiceProduct,@PathVariable int invoiceId, @PathVariable int productId) {
        Invoice invoice = invoiceService.get(invoiceId);
        Product product = productService.get(productId);
        String quantity = Double.toString(invoiceProduct.getProductQuantity());

        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"InvoiceProduct Created Successfully");
        if(invoice==null ){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, invoice  does not exist!");
        }else
            if(product==null){
                responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
                responseObject.setResponseDescription("Sorry, product  does not exist!");
        }
        else
          if (quantity==null ){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a product quantity!");
        }else {


              invoiceProduct.setId(new InvoiceProductId(invoice.getInvoiceId(),product.getProductId()));
              invoice.addInvoiceProduct(invoiceProduct);
              product.addInvoiceProduct(invoiceProduct);
              service.add(invoiceProduct);


              if("successful".equals(invoice.getStatus())){
                  double quantityInStock=product.getProductQuantity();
                  double quantityToBuy=invoiceProduct.getProductQuantity();
                  double difference=quantityInStock-quantityToBuy;
                  product.setProductQuantity(difference);
                  productService.update(product);
              }
              responseObject.setResponse(invoiceProduct);
        }

        System.out.println(invoiceProduct);
        return ResponseEntity.ok(responseObject);
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

//    @GetMapping("/getQuantity/{invoiceId}")
//    @ResponseBody
//    public ResponseEntity getQuantityByInvoice(@PathVariable int invoiceId) {
//        Invoice invoice=invoiceService.get(invoiceId);
//        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Invoice Product Found Successfully");
//        if (invoice==null){
//            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
//            responseObject.setResponseDescription("Sorry, this invoice id does not exist!");
//        }else{
//
//            List<Integer> list=service.getQuantityByInvoiceId(invoiceId);
//            responseObject.setResponse(list);
//        }
//        return ResponseEntity.ok(responseObject);
//    }

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

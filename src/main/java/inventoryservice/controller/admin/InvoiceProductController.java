package inventoryservice.controller.admin;

import inventoryservice.domain.admin.Invoice;
import inventoryservice.domain.admin.InvoiceProduct;
import inventoryservice.domain.admin.Product;
import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.service.admin.InvoiceProductService;
import inventoryservice.service.admin.InvoiceService;
import inventoryservice.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoiceproduct")
public class InvoiceProductController {

    @Autowired
    private InvoiceProductService service;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/create",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody InvoiceProduct invoiceProduct) {
        Invoice invoice = invoiceService.get(invoiceProduct.getInvoiceId());
        Product product = productService.get(invoiceProduct.getProductId());
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
            service.add(invoiceProduct);
            responseObject.setResponse(invoiceProduct);
        }

        System.out.println(invoiceProduct.toString());
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/getInvoiceByProductId/{productId}")
    @ResponseBody
    public ResponseEntity getInvoiceByProductId(@PathVariable int productId) {
        Product product=productService.get(productId);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Invoice Ids Found Successfully");
        if (product==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this product id does not exist!");
        }else{

            List<Integer> list=service.getInvoicebyProductId(productId);
            responseObject.setResponse(list);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/getProductbyInvoiceId/{invoiceId}")
    @ResponseBody
    public ResponseEntity getProductByInvoiceId(@PathVariable int invoiceId) {
        Invoice invoice=invoiceService.get(invoiceId);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Product Ids Found Successfully");
        if (invoice==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this invoice id does not exist!");
        }else{

            List<Integer> list=service.getProductbyInvoiceId(invoiceId);
            responseObject.setResponse(list);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/getQuantity/{invoiceId}")
    @ResponseBody
    public ResponseEntity getQuantityByInvoice(@PathVariable int invoiceId) {
        Invoice invoice=invoiceService.get(invoiceId);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Invoice Product Found Successfully");
        if (invoice==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this invoice id does not exist!");
        }else{

            List<Integer> list=service.getQuantityByInvoiceId(invoiceId);
            responseObject.setResponse(list);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/get/all")
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



}

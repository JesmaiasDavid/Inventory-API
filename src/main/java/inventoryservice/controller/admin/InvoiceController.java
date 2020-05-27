package inventoryservice.controller.admin;

import inventoryservice.domain.admin.Category;
import inventoryservice.domain.admin.Invoice;
import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.service.admin.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @PostMapping(value = "/invoices",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody Invoice invoice) {
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Invoice Created Successfully");
        String total= Double.toString(invoice.getTotal());
        if (total==null|| invoice.getStatus()==null ){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Enter a total and/or status");
        }else {
            service.add(invoice);
            responseObject.setResponse(invoice);
        }

        System.out.println(invoice.toString());
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping(value = "/invoices/{id}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody Invoice invoice, @PathVariable int id) {
        Invoice invoice1=service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Invoice Updated Successfully");
        String total= Double.toString(invoice.getTotal());

        if(invoice1==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this invoice does not exist!");
        }else
        if  (total==null|| invoice.getStatus()==null ){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Enter a total and/or status");
        }else {

            if (service.existsById(invoice.getInvoiceId())==true){
                responseObject.setResponseCode(HttpStatus.FORBIDDEN.toString());
                responseObject.setResponseDescription("Invoice already exists");
            }else {
            invoice.setInvoiceId(invoice1.getInvoiceId());
            invoice.setDateTimeGenerated(invoice1.getDateTimeGenerated());
            service.add(invoice);
            responseObject.setResponse(invoice);}
        }

        System.out.println(invoice.toString());
        return ResponseEntity.ok(responseObject);
    }


    @DeleteMapping("/invoices/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
        Invoice invoice=service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Invoice Deleted Successfully");
        if (invoice==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this invoice does not exist!");
        }else{
            service.delete(id);
        }
        return ResponseEntity.ok(responseObject);

    }

    @GetMapping("/invoices/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        Invoice invoice=service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Invoice Found Successfully");
        if (invoice==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this invoice does not exist!");
        }else{

            responseObject.setResponse(invoice);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/invoices")
    @ResponseBody
    public ResponseEntity getAll() {
        List<Invoice> invoices = service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"All invoices Found Successfully");
        if (invoices==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, invoices not found!");
        }else{

            responseObject.setResponse(invoices);
        }
        return ResponseEntity.ok(responseObject);
    }
}

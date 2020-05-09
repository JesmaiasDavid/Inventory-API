package inventoryservice.controller.admin;

import inventoryservice.domain.admin.Category;
import inventoryservice.domain.admin.Invoice;
import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.factory.admin.ResponseObjectFactory;
import inventoryservice.service.admin.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @PostMapping(value = "/create",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody Invoice invoice) {
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Invoice Created Successfully");
        String total= Double.toString(invoice.getTotal());
        if (total==null ){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Total value cannot be null");
        }else {
            service.add(invoice);
            responseObject.setResponse(invoice);
        }

        System.out.println(invoice.toString());
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping(value = "/update",consumes = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody Invoice invoice) {
        Invoice invoice1=service.get(invoice.getInvoiceId());
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Invoice Created Successfully");
        String total= Double.toString(invoice.getTotal());

        if(invoice1==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this invoice does not exist!");
        }else
        if (total==null ){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Total value cannot be null");
        }else {
            service.add(invoice);
            responseObject.setResponse(invoice);
        }

        System.out.println(invoice.toString());
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
        Invoice invoice=service.get(id);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Invoice Deleted Successfully");
        if (invoice==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this invoice does not exist!");
        }else{
            service.delete(id);
        }
        return ResponseEntity.ok(responseObject);

    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        Invoice invoice=service.get(id);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Invoice Found Successfully");
        if (invoice==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this invoice does not exist!");
        }else{

            responseObject.setResponse(invoice);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/get/all")
    @ResponseBody
    public ResponseEntity getAll() {
        List<Invoice> invoices = service.getAll();
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"All invoices Found Successfully");
        if (invoices==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, invoices not found!");
        }else{

            responseObject.setResponse(invoices);
        }
        return ResponseEntity.ok(responseObject);
    }
}

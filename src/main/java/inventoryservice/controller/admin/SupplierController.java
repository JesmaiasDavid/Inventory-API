package inventoryservice.controller.admin;

import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.domain.admin.Supplier;
import inventoryservice.service.admin.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class SupplierController {

    @Autowired
    private SupplierService service;

    @PostMapping(value = "/suppliers",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody Supplier supplier) {

        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Supplier Created Successfully");
        if (supplier.getSupplierName()==null ||supplier.getSupplierCompany()==null ||supplier.getLastModifiedUser()==null || supplier.getCreatedUser()==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a suppier name and/or company name and/or created user and/or last modified user ");
        }else {

            if (service.existsById(supplier.getSupplierId())==true){
                responseObject.setResponseCode(HttpStatus.FORBIDDEN.toString());
                responseObject.setResponseDescription("Supplier already exists");
            }else {
            service.add(supplier);
            responseObject.setResponse(supplier);}
        }

        System.out.println(supplier.toString());
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping(value = "/suppliers/{id}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody Supplier supplier,@PathVariable int id) {
        Supplier supplier1 = service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Supplier Updated Successfully");

        if(supplier1==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this supplier does not exist!");
        }else
        if (supplier.getSupplierName()==null ||supplier.getSupplierCompany()==null ||supplier.getLastModifiedUser()==null ){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a supplier name and/or company name and/or created user and/or last modified user ");
        }else {
            supplier.setSupplierId(supplier1.getSupplierId());
            supplier.setCreatedUser(supplier1.getCreatedUser());
            supplier.setCreatedDateTime(supplier1.getCreatedDateTime());
            supplier.setLastModifiedDateTime(new Date());
            service.add(supplier);
            responseObject.setResponse(supplier);
        }

        System.out.println(supplier.toString());
        return ResponseEntity.ok(responseObject);
    }


    @DeleteMapping("/suppliers/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
       Supplier supplier=service.get(id);
        ResponseObject responseObject=new ResponseObject(HttpStatus.OK.toString(),"Supplier Deleted Successfully");
        if (supplier==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this supplier does not exist!");
        }else{
            service.delete(id);
        }
        return ResponseEntity.ok(responseObject);

    }

    @GetMapping("/suppliers/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        Supplier supplier=service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Supplier Found Successfully");
        if (supplier==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this supplier does not exist!");
        }else{

            responseObject.setResponse(supplier);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/suppliers")
    @ResponseBody
    public ResponseEntity getAll() {
        List<Supplier> suppliers= service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Suppliers Found Successfully");
        if (suppliers==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, suppliers not found!");
        }else{

            responseObject.setResponse(suppliers);
        }
        return ResponseEntity.ok(responseObject);
    }
}

package inventoryservice.controller.admin;

import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.domain.admin.Stock;
import inventoryservice.factory.admin.ResponseObjectFactory;
import inventoryservice.service.admin.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService service;

    @PostMapping(value = "/create",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody Stock stock) {

        String stockQuantity=Double.toString(stock.getStockQuantity());

        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Stock Created Successfully");
        if (stockQuantity==null ||stock.getCreatedUser()==null ||stock.getLastModifiedUser()==null ){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a stock quantity and/or created user and/or last modified user ");
        }else {
            service.add(stock);
            responseObject.setResponse(stock);
        }

        System.out.println(stock.toString());
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping(value = "/update",consumes = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody Stock stock) {
        Stock stock1 = service.get(stock.getStockId());
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Stock Updated Successfully");
        String stockQuantity=Double.toString(stock.getStockQuantity());

        if(stock1==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this stock does not exist!");
        }else
        if (stockQuantity==null ||stock.getCreatedUser()==null ||stock.getLastModifiedUser()==null ){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a stock quantity and/or created user and/or last modified user ");
        }else {
            service.add(stock);
            responseObject.setResponse(stock);
        }

        System.out.println(stock.toString());
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
        Stock stock= service.get(id);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Stock Deleted Successfully");
        if (stock==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this stock does not exist!");
        }else{
            service.delete(id);
        }
        return ResponseEntity.ok(responseObject);

    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        Stock stock= service.get(id);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Stock Found Successfully");
        if (stock==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this stock does not exist!");
        }else{

            responseObject.setResponse(stock);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/get/all")
    @ResponseBody
    public ResponseEntity getAll() {
        List<Stock> stocks= service.getAll();
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Stock Found Successfully");
        if (stocks==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, stocks not found!");
        }else{

            responseObject.setResponse(stocks);
        }
        return ResponseEntity.ok(responseObject);
    }
}

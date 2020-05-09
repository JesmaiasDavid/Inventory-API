package inventoryservice.controller.admin;

import inventoryservice.domain.admin.Product;
import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.factory.admin.ResponseObjectFactory;
import inventoryservice.service.admin.CategoryService;
import inventoryservice.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/create",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody Product product) {
        String buyingPrice=Double.toString(product.getProductBuyingPrice());
        String sellingPrice=Double.toString(product.getProductSellingPrice());

        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Product Created Successfully");
        if (product.getProductName()==null || product.getCreatedUser()==null || product.getLastModifiedUser()==null || buyingPrice==null || sellingPrice==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name and/or created user and/or last modified user and/or buying price and/or selling price!");
        }else {

            product.addCategory(categoryService.get(31));
            service.add(product);
            responseObject.setResponse(product);
        }

        System.out.println(product.toString());
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping(value = "/update",consumes = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody Product product) {
        Product product1 = service.get(product.getProductId());
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Product Updated Successfully");
        String buyingPrice=Double.toString(product.getProductBuyingPrice());
        String sellingPrice=Double.toString(product.getProductSellingPrice());

        if(product1==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this product does not exist!");
        }else
        if (product.getProductName()==null || product.getCreatedUser()==null || product.getLastModifiedUser()==null || buyingPrice==null || sellingPrice==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name and/or created user and/or last modified user and/or buying price and/or selling price!");
        }else {
            service.add(product);
            responseObject.setResponse(product);
        }

        System.out.println(product.toString());
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
        Product product= service.get(id);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Permission Deleted Successfully");
        if (product==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this product does not exist!");
        }else{
            service.delete(id);
        }
        return ResponseEntity.ok(responseObject);

    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        Product product = service.get(id);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Product Found Successfully");
        if (product==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this product does not exist!");
        }else{

            responseObject.setResponse(product);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/get/all")
    @ResponseBody
    public ResponseEntity getAll() {
        List<Product> products= service.getAll();
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Product Found Successfully");
        if (products==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, products not found!");
        }else{

            responseObject.setResponse(products);
        }
        return ResponseEntity.ok(responseObject);
    }
}

package inventoryservice.controller.admin;

import inventoryservice.domain.admin.Category;
import inventoryservice.domain.admin.Product;
import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.service.admin.CategoryService;
import inventoryservice.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
//@RequestMapping("/categories")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/categories/{categoryId}/products",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody Product product,@PathVariable int categoryId) {
        String buyingPrice=Double.toString(product.getProductBuyingPrice());
        String sellingPrice=Double.toString(product.getProductSellingPrice());
        Category category= categoryService.get(categoryId);

        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Product Created Successfully");
        if (category==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this category does not exist!");
        }else
        if (product.getProductName()==null || product.getCreatedUser()==null || product.getLastModifiedUser()==null || buyingPrice==null || sellingPrice==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name and/or created user and/or last modified user and/or buying price and/or selling price!");
        }else {

           category.addProduct(product);
            service.add(product);
            responseObject.setResponse(product);
        }

        System.out.println(product.toString());
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping(value = "/products/{id}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody Product product,@PathVariable int id) {
        Product product1 = service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Product Updated Successfully");
        String buyingPrice=Double.toString(product.getProductBuyingPrice());
        String sellingPrice=Double.toString(product.getProductSellingPrice());

        if(product1==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this product does not exist!");
        }else
        if (product.getProductName()==null  || product.getLastModifiedUser()==null || buyingPrice==null || sellingPrice==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name and/or created user and/or last modified user and/or buying price and/or selling price!");
        }else {
            product.setProductId(product1.getProductId());
            product.setCreatedUser(product1.getCreatedUser());
            product.setCreatedDateTime(product1.getCreatedDateTime());
            product.setLastModifiedDateTime(new Date());
            product.setCategory(product1.getCategory());
            service.add(product);
            responseObject.setResponse(product);
        }

        System.out.println(product.toString());
        return ResponseEntity.ok(responseObject);
    }


    @DeleteMapping("/products/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
        Product product= service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Permission Deleted Successfully");
        if (product==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this product does not exist!");
        }else{
            Category category =product.getCategory();
            category.removeProduct(product);
            service.delete(id);
        }
        return ResponseEntity.ok(responseObject);

    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        Product product = service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Product Found Successfully");
        if (product==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this product does not exist!");
        }else{

            responseObject.setResponse(product);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/products")
    @ResponseBody
    public ResponseEntity getAll() {
        List<Product> products= service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Product Found Successfully");
        if (products==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, products not found!");
        }else{

            responseObject.setResponse(products);
        }
        return ResponseEntity.ok(responseObject);
    }
}

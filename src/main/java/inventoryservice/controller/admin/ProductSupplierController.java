package inventoryservice.controller.admin;

import inventoryservice.domain.admin.*;
import inventoryservice.service.admin.ProductService;
import inventoryservice.service.admin.ProductSupplierService;
import inventoryservice.service.admin.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductSupplierController {

    @Autowired
    private ProductSupplierService service;

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    @PostMapping(value = "products/{productId}/suppliers/{supplierId}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody ProductSupplier productSupplier,@PathVariable int productId, @PathVariable int supplierId) {
        Product product = productService.get(productId);
        Supplier supplier= supplierService.get(supplierId);
        String quantitySupplied = Double.toString(productSupplier.getQuantitySupplied());

        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Product Supplier Created Successfully");
        if(supplier==null ){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, supplier  does not exist!");
        }else
        if(product==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, product  does not exist!");
        }
        else
        if (quantitySupplied==null ){
            responseObject.setResponseCode(HttpStatus.BAD_REQUEST.toString());
            responseObject.setResponseDescription("Please provide a product quantity to be supplied");
        }else {

            productSupplier.setId(new ProductSupplierId(product.getProductId(),supplier.getSupplierId()));
            product.addProductSupplier(productSupplier);
            supplier.addProductSupplier(productSupplier);
            service.add(productSupplier);
            responseObject.setResponse(productSupplier);

            double quantityToSupply = productSupplier.getQuantitySupplied();
            double quantityOnStock = product.getProductQuantity();
            double sum = quantityOnStock + quantityToSupply;
            product.setProductQuantity(sum);
            productService.update(product);
            System.out.println(product);

        }

        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/products/{id}/suppliers")
    @ResponseBody
    public ResponseEntity getSuppliersByProduct(@PathVariable int id) {
        Product product = productService.get(id);
        List<Integer>listId=new ArrayList<>();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Suppliers Ids Found Successfully");
        if (product==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this product does not exist!");
        }else{

            List<Supplier> list =service.getSupplierByProduct(product);

            for(int i=0;i<list.size();i++){
                listId.add(list.get(i).getSupplierId());
            }
            responseObject.setResponse(listId);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/suppliers/{id}/products")
    @ResponseBody
    public ResponseEntity getProductsBySupplier(@PathVariable int id) {
        Supplier supplier = supplierService.get(id);
        List<Integer>listId=new ArrayList<>();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Products Ids Found Successfully");
        if (supplier==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this supplier does not exist!");
        }else{

            List<Product> list =service.getProductBySupplier(supplier);

            for(int i=0;i<list.size();i++){
                listId.add(list.get(i).getProductId());
            }
            responseObject.setResponse(listId);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/suppliers/products")
    @ResponseBody
    public ResponseEntity getAll() {
        List<ProductSupplier> productSuppliers = service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"All RoleUsers Found Successfully");
        if (productSuppliers==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, RoleUsers not found!");
        }else{

            responseObject.setResponse(productSuppliers);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/products/suppliers")
    @ResponseBody
    public ResponseEntity getAlll() {
        List<ProductSupplier> productSuppliers = service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"All RoleUsers Found Successfully");
        if (productSuppliers==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, RoleUsers not found!");
        }else{

            responseObject.setResponse(productSuppliers);
        }
        return ResponseEntity.ok(responseObject);
    }
}

/*

    THERE  IS A PROBLEM WITH THE DATE CREATED AND LAST MODIFIED DATE

*/

package inventoryservice.controller.admin;

import inventoryservice.domain.admin.Category;
import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.factory.admin.ResponseObjectFactory;
import inventoryservice.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping(value = "/create",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody Category category) {
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Category Created Successfully");
        if (category.getCategoryName()==null || category.getCreatedUser()==null || category.getLastModifiedUser()==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name and/or created user and/or last  modified user!");
        }else {
            service.add(category);
            responseObject.setResponse(category);
        }

        System.out.println(category.toString());
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping(value = "/update",consumes = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody Category category) {
        Category cat = service.get(category.getId());
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Category Updated Successfully");

        if(cat==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this category does not exist!");
        }else
        if (category.getCategoryName()==null || category.getCreatedUser()==null || category.getLastModifiedUser()==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name and/or created user and/or last  modified user!");
        }else {
            service.add(category);
            responseObject.setResponse(category);
        }

        System.out.println(category.toString());
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
        Category category = service.get(id);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Category Deleted Successfully");
        if (category==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this category does not exist!");
        }else{
            service.delete(id);
        }
        return ResponseEntity.ok(responseObject);

    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        Category category = service.get(id);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Category Found Successfully");
        if (category==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this category does not exist!");
        }else{

            responseObject.setResponse(category);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/get/all")
    @ResponseBody
    public ResponseEntity getAll() {
        List<Category> categories = service.getAll();
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"All Categories Found Successfully");
        if (categories==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, categories not found!");
        }else{

            responseObject.setResponse(categories);
        }
        return ResponseEntity.ok(responseObject);
    }


}

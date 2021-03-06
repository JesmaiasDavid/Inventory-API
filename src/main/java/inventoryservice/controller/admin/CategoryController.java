/*

    THERE  IS A PROBLEM WITH THE DATE CREATED AND LAST MODIFIED DATE

*/

package inventoryservice.controller.admin;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import  javax.json.JsonPatch;
import javax.json.JsonException;
import com.github.fge.jsonpatch.JsonPatchException;
import inventoryservice.domain.admin.Category;
import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonStructure;
import javax.json.JsonValue;
import java.util.Date;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping(value = "/categories",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody Category category) {
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Category Created Successfully");
        if (category.getCategoryName()==null || category.getCreatedUser()==null || category.getLastModifiedUser()==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name and/or created user and/or last  modified user!");
        }else {
            if (service.existsById(category.getId())==true){
                responseObject.setResponseCode(HttpStatus.FORBIDDEN.toString());
                responseObject.setResponseDescription("Category already exists");
            }else {
                service.add(category);
                responseObject.setResponse(category);
            }
        }

        System.out.println(category.toString());
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping(value = "/categories/{id}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody Category category,@PathVariable int id) {

        Category cat = service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Category Updated Successfully");

        if(cat==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this category does not exist!");
        }else
        if (category.getCategoryName()==null  || category.getLastModifiedUser()==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name  and/or last  modified user!");
        }else {
            category.setId(cat.getId());
            category.setCreatedDateTime(cat.getCreatedDateTime());
            category.setLastModifiedDateTime(new Date());
            category.setCreatedUser(cat.getCreatedUser());
            service.add(category);
            responseObject.setResponse(category);
        }

        System.out.println(category.toString());
        return ResponseEntity.ok(responseObject);
    }


    @DeleteMapping("/categories/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
        Category category = service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Category Deleted Successfully");
        if (category==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this category does not exist!");
        }else{
            service.delete(id);
        }
        return ResponseEntity.ok(responseObject);

    }

    @GetMapping("/categories/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        Category category = service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Category Found Successfully");
        if (category==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this category does not exist!");
        }else{

            responseObject.setResponse(category);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/categories")
    @ResponseBody
    public ResponseEntity getAll() {
        List<Category> categories = service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"All Categories Found Successfully");
        if (categories==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, categories not found!");
        }else{

            responseObject.setResponse(categories);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PatchMapping(value = "/categories/{id}", consumes = "application/json-patch+json")
    @ResponseBody
    public ResponseEntity updatePartially(@PathVariable int id, @RequestBody JsonPatch patch) {
        Category category = service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Category Updated Successfully");
        if(category==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, category not found!");
        }else{
               Category category1=patchCategory(patch,category);
               responseObject.setResponse(category1);
        }
            return ResponseEntity.ok(responseObject);
    }

    private Category patchCategory(JsonPatch patch, Category targetCategory)  {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonStructure target = objectMapper.convertValue(targetCategory, JsonStructure.class);
         JsonValue patchedCategory=patch.apply(target);
         Category modifiedCategory=objectMapper.convertValue(patchedCategory,Category.class);
         service.update(modifiedCategory);
         return  modifiedCategory;
    }

}

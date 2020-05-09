package inventoryservice.controller.admin;

import inventoryservice.domain.admin.Permission;
import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.factory.admin.ResponseObjectFactory;
import inventoryservice.service.admin.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {


    @Autowired
    private PermissionService service;

    @PostMapping(value = "/create",consumes = "application/x-www-form-urlencoded")
    @ResponseBody
    public ResponseEntity create(@RequestBody Permission permission) {
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Permission Created Successfully");
        if (permission.getPermissionName()==null ){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a nam!");
        }else {
            service.add(permission);
            responseObject.setResponse(permission);
        }

        System.out.println(permission.toString());
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping(value = "/update",consumes = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody Permission permission) {
        Permission permission1 = service.get(permission.getPermissionId());
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Permission Updated Successfully");

        if(permission1==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this permission does not exist!");
        }else
        if (permission.getPermissionName()==null ){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name!");
        }else {
            service.add(permission);
            responseObject.setResponse(permission);
        }

        System.out.println(permission.toString());
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
        Permission permission= service.get(id);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Permission Deleted Successfully");
        if (permission==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this permission does not exist!");
        }else{
            service.delete(id);
        }
        return ResponseEntity.ok(responseObject);

    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        Permission permission= service.get(id);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Permission Found Successfully");
        if (permission==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this permission does not exist!");
        }else{

            responseObject.setResponse(permission);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/get/all")
    @ResponseBody
    public ResponseEntity getAll() {
        List<Permission> permissions = service.getAll();
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"All Permissiona Found Successfully");
        if (permissions==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, permissions not found!");
        }else{

            responseObject.setResponse(permissions);
        }
        return ResponseEntity.ok(responseObject);
    }

}

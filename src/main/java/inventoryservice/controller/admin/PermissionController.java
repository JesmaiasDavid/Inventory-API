package inventoryservice.controller.admin;

import inventoryservice.domain.admin.Permission;
import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.service.admin.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class PermissionController {


    @Autowired
    private PermissionService service;

    @PostMapping(value = "/permissions",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody Permission permission) {
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Permission Created Successfully");
        if (permission.getPermissionName()==null || permission.getCreatedUser()==null|| permission.getLastModifiedUser()==null ){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name and/or created user and/or last modified user!");
        }else {
            if (service.existsById(permission.getPermissionId())==true){
                responseObject.setResponseCode(HttpStatus.FORBIDDEN.toString());
                responseObject.setResponseDescription("Permission already exists");
            }else {
            service.add(permission);
            responseObject.setResponse(permission);}
        }

        System.out.println(permission.toString());
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping(value = "/permissions/{id}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody Permission permission, @PathVariable int id) {
        Permission permission1 = service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Permission Updated Successfully");

        if(permission1==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this permission does not exist!");
        }else
        if (permission.getPermissionName()==null || permission.getLastModifiedUser()==null ){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name!");
        }else {
            permission.setPermissionId(permission1.getPermissionId());
            permission.setCreatedUser(permission1.getCreatedUser());
            permission.setCreatedDateTime(permission1.getCreatedDateTime());
            permission.setLastModifiedDateTime(new Date());
            service.add(permission);
            responseObject.setResponse(permission);
        }

        System.out.println(permission.toString());
        return ResponseEntity.ok(responseObject);
    }


    @DeleteMapping("/permissions/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
        Permission permission= service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Permission Deleted Successfully");
        if (permission==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this permission does not exist!");
        }else{
            service.delete(id);
        }
        return ResponseEntity.ok(responseObject);

    }

    @GetMapping("/permissions/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        Permission permission= service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Permission Found Successfully");
        if (permission==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this permission does not exist!");
        }else{

            responseObject.setResponse(permission);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/permissions")
    @ResponseBody
    public ResponseEntity getAll() {
        List<Permission> permissions = service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"All Permissiona Found Successfully");
        if (permissions==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, permissions not found!");
        }else{

            responseObject.setResponse(permissions);
        }
        return ResponseEntity.ok(responseObject);
    }

}

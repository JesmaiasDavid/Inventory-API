package inventoryservice.controller.admin;

import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.domain.admin.Role;
import inventoryservice.factory.admin.ResponseObjectFactory;
import inventoryservice.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;

    @PostMapping(value = "/create",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody Role role) {


        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Role Created Successfully");
        if (role.getRoleName()==null || role.getCreatedUser()==null || role.getLastModifiedUser()==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name and/or created user and/or last modified user!");
        }else {
            service.add(role);
            responseObject.setResponse(role);
        }

        System.out.println(role.toString());
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping(value = "/update",consumes = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody Role role) {
        Role role1 = service.get(role.getRoleId());
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Role Updated Successfully");

        if(role1==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this role does not exist!");
        }else if (role.getRoleName()==null || role.getCreatedUser()==null || role.getLastModifiedUser()==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name and/or created user and/or last modified user!");
        }else {
            service.add(role);
            responseObject.setResponse(role);
        }

        System.out.println(role.toString());
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
                Role role = service.get(id);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Role Deleted Successfully");
        if (role==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this role does not exist!");
        }else{
            service.delete(id);
        }
        return ResponseEntity.ok(responseObject);

    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        Role role = service.get(id);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Role Found Successfully");
        if (role==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this role does not exist!");
        }else{

            responseObject.setResponse(role);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/get/all")
    @ResponseBody
    public ResponseEntity getAll() {
        List<Role> roles= service.getAll();
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Roles Found Successfully");
        if (roles==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, roles not found!");
        }else{

            responseObject.setResponse(roles);
        }
        return ResponseEntity.ok(responseObject);
    }
}

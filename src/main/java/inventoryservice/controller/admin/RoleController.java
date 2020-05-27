package inventoryservice.controller.admin;

import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.domain.admin.Role;
import inventoryservice.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService service;

    @PostMapping(value = "/roles",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody Role role) {

        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Role Created Successfuly");
        if (role.getRoleName()==null || role.getCreatedUser()==null || role.getLastModifiedUser()==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name and/or created user and/or last modified user!");
        }else {
            if (service.existsById(role.getRoleId())==true){
                responseObject.setResponseCode(HttpStatus.FORBIDDEN.toString());
                responseObject.setResponseDescription("Role already exists");
            }else {
            service.add(role);
            responseObject.setResponse(role);}
        }

        System.out.println(role.toString());
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping(value = "/roles/{id}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody Role role, @PathVariable int id) {
        Role role1 = service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Role Updated Successfully");

        if(role1==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this role does not exist!");
        }else if (role.getRoleName()==null  || role.getLastModifiedUser()==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a name and/or created user and/or last modified user!");
        }else {
            role.setRoleId(role1.getRoleId());
            role.setCreatedUser(role1.getCreatedUser());
            role.setLastModifiedDateTime(new Date());
            role.setCreatedDateTime(role1.getCreatedDateTime());
            service.add(role);
            responseObject.setResponse(role);
        }

        System.out.println(role.toString());
        return ResponseEntity.ok(responseObject);
    }


    @DeleteMapping("/roles/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
        Role role = service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Role Deleted Successfully");
        if (role==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this role does not exist!");
        }else{
            service.delete(id);
        }
        return ResponseEntity.ok(responseObject);

    }

    @GetMapping("/roles/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        Role role = service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Role Found Successfully");
        if (role==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this role does not exist!");
        }else{

            responseObject.setResponse(role);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/roles")
    @ResponseBody
    public ResponseEntity getAll() {
        List<Role> roles= service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Roles Found Successfully");
        if (roles==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, roles not found!");
        }else{

            responseObject.setResponse(roles);
        }
        return ResponseEntity.ok(responseObject);
    }
}

package inventoryservice.controller.user;

import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.domain.admin.Role;
import inventoryservice.domain.user.User;
import inventoryservice.domain.user.UserRole;
import inventoryservice.factory.admin.ResponseObjectFactory;
import inventoryservice.service.admin.RoleService;
import inventoryservice.service.user.UserRoleService;
import inventoryservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userrole")
public class UserRoleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService service;

    @PostMapping(value = "/create",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody UserRole userRole) {

        Role role = roleService.get(userRole.getRoleId());
        User user = userService.get(userRole.getUserId());

        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"UserRole Created Successfully");
        if(role==null ){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, role not found!");
        }else
        if(user==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, user not found!");
        }else {
            service.add(userRole);
            responseObject.setResponse(userRole);
        }

        System.out.println(userRole.toString());
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/getUserByRoleId/{roleId}")
    @ResponseBody
    public ResponseEntity getUserbyRoleId(@PathVariable int roleId) {
        Role role=roleService.get(roleId);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"User Ids Found Successfully");
        if (role==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, role not found!");
        }else{

            List<Integer> list=service.getUserbyRoleId(roleId);
            responseObject.setResponse(list);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/getRoleByUserId/{userId}")
    @ResponseBody
    public ResponseEntity getRoleByUserId(@PathVariable int userId) {
       User user=userService.get(userId);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Role Ids Found Successfully");
        if (user==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, user not found!");
        }else{

            List<Integer> list=service.getRolebyUserId(userId);
            responseObject.setResponse(list);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/get/all")
    @ResponseBody
    public ResponseEntity getAll() {
        List<UserRole> userRoles = service.getAll();
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"All userroles Found Successfully");
        if (userRoles==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, userroles not found!");
        }else{

            responseObject.setResponse(userRoles);
        }
        return ResponseEntity.ok(responseObject);
    }

}

package inventoryservice.controller.admin;

import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.domain.admin.Role;
import inventoryservice.domain.admin.RoleUser;
import inventoryservice.domain.admin.RoleUserId;
import inventoryservice.domain.user.User;
import inventoryservice.service.admin.RoleService;

import inventoryservice.service.admin.RoleUserService;
import inventoryservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

@Autowired
private RoleUserService service;

    @PostMapping(value = "/users/{userId}/roles/{roleId}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@PathVariable int userId,@PathVariable int roleId) {

        Role role = roleService.get(roleId);
        User user = userService.get(userId);

        ResponseObject responseObject=new ResponseObject(HttpStatus.OK.toString(),"User Role Created Successfully");
        if(role==null ){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, role not found!");
        }else
        if(user==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, user not found!");
        }else {
            if (service.existsById(new RoleUserId(role.getRoleId(),user.getUserId()))==true){
                responseObject.setResponseCode(HttpStatus.FORBIDDEN.toString());
                responseObject.setResponseDescription("Role User already exists");
            }else {
            RoleUser roleUser = new RoleUser(role,user);
            roleUser.setId(new RoleUserId(role.getRoleId(),user.getUserId()));

            role.addRoleUser(roleUser);
            user.addRoleUser(roleUser);

            service.add(roleUser);
            responseObject.setResponse(roleUser);}
        }

        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/roles/{roleId}/users")
    @ResponseBody
    public ResponseEntity getUserbyRoleId(@PathVariable int roleId) {
        Role role=roleService.get(roleId);
        List<Integer> listId=new ArrayList<>();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"User Ids Found Successfully");
        if (role==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, role not found!");
        }else{

            List <User> list=service.getUserByRole(role);

            for (int i=0;i<list.size();i++){
                listId.add(list.get(i).getUserId());
            }
           responseObject.setResponse(listId);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/users/{userId}/roles")
    @ResponseBody
    public ResponseEntity getRoleByUserId(@PathVariable int userId) {
       User user=userService.get(userId);
        List<Integer> listId=new ArrayList<>();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Role Ids Found Successfully");
        if (user==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, user not found!");
        }else{

            List<Role> list=service.getRoleByUser(user);
            for (int i=0;i<list.size();i++){
                listId.add(list.get(i).getRoleId());
            }
            responseObject.setResponse(listId);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/users/roles")
    @ResponseBody
    public ResponseEntity getAll() {
        List<RoleUser> roleUsers = service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Role Ids Found Successfully");
        if (roleUsers==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, user not found!");
        }else{

            responseObject.setResponse(roleUsers);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/roles/users")
    @ResponseBody
    public ResponseEntity getAlll() {
        List<RoleUser> roleUsers = service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Role Ids Found Successfully");
        if (roleUsers==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, user not found!");
        }else{

            responseObject.setResponse(roleUsers);
        }
        return ResponseEntity.ok(responseObject);
    }




}

package inventoryservice.controller.user;

import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.domain.user.User;
import inventoryservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    private UserService service;


    @PostMapping(value = "/users",consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody User user){

        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"User Created Successfully");
        if (user.getUserFirstName()==null || user.getUserLastName()==null || user.getUserAddress()==null || user.getUserEmail()==null ||user.getLastModifiedUser()==null || user.getCreatedUser()==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a first name and/or last name and/or email and/or address and/or created user and/or last modified user !");
        }else {
            if (service.existsById(user.getUserId())==true){
                responseObject.setResponseCode(HttpStatus.FORBIDDEN.toString());
                responseObject.setResponseDescription("User already exists");
            }else {
            service.add(user);
            responseObject.setResponse(user);}
        }
        System.out.println(user.toString());
        return ResponseEntity.ok(responseObject);
    }


    @PutMapping(value = "/users/{id}")
    @ResponseBody
    public ResponseEntity update(@RequestBody User user , @PathVariable int id){

        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"User Updated Successfully");

        User user1 = service.get(id);
        if (user1==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this user does not exist!");
        }
        else if (user.getUserFirstName()==null || user.getUserLastName()==null || user.getUserAddress()==null || user.getUserEmail()==null ||user.getLastModifiedUser()==null ){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a first name and/or last name and/or email and/or address and/or created user and/or last modified user !");
        }else {

            user.setUserId(user1.getUserId());
            user.setCreatedUser(user1.getCreatedUser());
            user.setCreatedDateTime(user1.getCreatedDateTime());
            user.setLastModifiedDateTime(new Date());
            service.add(user);
            responseObject.setResponse(user);
        }
        System.out.println(user.toString());
        return ResponseEntity.ok(responseObject);
    }


    @DeleteMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
        User user=service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"User Deleted Successfully");
        if (user==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this user does not exist!");
        }else{
            service.delete(id);
        }
        return ResponseEntity.ok(responseObject);

    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        User user=service.get(id);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"User Found Successfully");
        if (user==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this user does not exist!");
        }else{

            responseObject.setResponse(user);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity getAll() {
        List<User> users= service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Users Found Successfully");
        if (users==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, users not found!");
        }else{

            responseObject.setResponse(users);
        }
        return ResponseEntity.ok(responseObject);
    }

}
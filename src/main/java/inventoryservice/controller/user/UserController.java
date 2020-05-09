package inventoryservice.controller.user;

import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.domain.user.User;
import inventoryservice.factory.admin.ResponseObjectFactory;
import inventoryservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService service;


    @PostMapping(value = "/create",consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody User user){

        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"User Created Successfully");
        if (user.getUserFirstName()==null || user.getUserLastName()==null || user.getUserAddress()==null || user.getUserEmail()==null ||user.getLastModifiedUser()==null || user.getCreatedUser()==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a first name and/or last name and/or email and/or address and/or created user and/or last modified user !");
        }else {
            service.add(user);
            responseObject.setResponse(user);
        }
        System.out.println(user.toString());
        return ResponseEntity.ok(responseObject);
    }


    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity update(@RequestBody User user ){

        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"User Updated Successfully");

        User user1 = service.get(user.getUserId());
        if (user1==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this user does not exist!");
        }
        else if (user.getUserFirstName()==null || user.getUserLastName()==null || user.getUserAddress()==null || user.getUserEmail()==null ||user.getLastModifiedUser()==null || user.getCreatedUser()==null){
            responseObject.setResponseCode(HttpStatus.PRECONDITION_FAILED.toString());
            responseObject.setResponseDescription("Please provide a first name and/or last name and/or email and/or address and/or created user and/or last modified user !");
        }else {
            service.add(user);
            responseObject.setResponse(user);
        }
        System.out.println(user.toString());
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable int id) {
        User user=service.get(id);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"User Deleted Successfully");
        if (user==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this user does not exist!");
        }else{
            service.delete(id);
        }
        return ResponseEntity.ok(responseObject);

    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        User user=service.get(id);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"User Found Successfully");
        if (user==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, this user does not exist!");
        }else{

            responseObject.setResponse(user);
        }
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/get/all")
    @ResponseBody
    public ResponseEntity getAll() {
        List<User> users= service.getAll();
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Users Found Successfully");
        if (users==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, users not found!");
        }else{

            responseObject.setResponse(users);
        }
        return ResponseEntity.ok(responseObject);
    }

}

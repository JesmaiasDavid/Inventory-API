package inventoryservice.controller.admin;

import inventoryservice.domain.admin.Permission;
import inventoryservice.domain.admin.ResponseObject;
import inventoryservice.domain.admin.Role;
import inventoryservice.domain.admin.RolePermission;
import inventoryservice.factory.admin.ResponseObjectFactory;
import inventoryservice.service.admin.PermissionService;
import inventoryservice.service.admin.RolePermissionService;
import inventoryservice.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rolepermission")
public class RolePermissionController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService service;

    @PostMapping(value = "/create",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@RequestBody RolePermission rolePermission) {

        Role role = roleService.get(rolePermission.getRoleId());
        Permission permission = permissionService.get(rolePermission.getPermissionId());

        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"RolePermission Created Successfully");
        if(role==null ){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, role not found!");
        }else
        if(permission==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, permission not found!");
        }else {
            service.add(rolePermission);
            responseObject.setResponse(rolePermission);
        }

        System.out.println(rolePermission.toString());
        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/getRoleByPermissionId/{permissionId}")
    @ResponseBody
    public ResponseEntity getRoleByPermissionId(@PathVariable int permissionId) {
        Permission permission=permissionService.get(permissionId);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Role Ids Found Successfully");
        if (permission==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, permission not found!");
        }else{

            List<Integer> list=service.getRoleByPermissionId(permissionId);
            responseObject.setResponse(list);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/getPermissionByRoleId/{roleId}")
    @ResponseBody
    public ResponseEntity getPermissionByRoleId(@PathVariable int roleId) {
        Role role=roleService.get(roleId);
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"Permission Ids Found Successfully");
        if (role==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, role not found!");
        }else{

            List<Integer> list=service.getPermissionIdbyRoleId(roleId);
            responseObject.setResponse(list);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/get/all")
    @ResponseBody
    public ResponseEntity getAll() {
        List<RolePermission> rolePermissions = service.getAll();
        ResponseObject responseObject= ResponseObjectFactory.getResponseObject(HttpStatus.OK.toString(),"All rolepermissions Found Successfully");
        if (rolePermissions==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, rolepermissions not found!");
        }else{

            responseObject.setResponse(rolePermissions);
        }
        return ResponseEntity.ok(responseObject);
    }

}

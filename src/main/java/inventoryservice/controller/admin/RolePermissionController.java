package inventoryservice.controller.admin;

import inventoryservice.domain.admin.*;
import inventoryservice.service.admin.PermissionService;
import inventoryservice.service.admin.RolePermissionService;
import inventoryservice.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RolePermissionController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService service;

    @PostMapping(value = "/roles/{roleId}/permissions/{permissionId}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity create(@PathVariable int roleId,@PathVariable int permissionId) {

        Role role = roleService.get(roleId);
        Permission permission = permissionService.get(permissionId);

        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"RolePermission Created Successfully");
        if(role==null ){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, role not found!");
        }else
        if(permission==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, permission not found!");
        }else {
            if (service.existsById(new RolePermissonId(role.getRoleId(),permission.getPermissionId()))==true){
                responseObject.setResponseCode(HttpStatus.FORBIDDEN.toString());
                responseObject.setResponseDescription("Role Permission already exists");
            }else {
            RolePermission rolePermission = new RolePermission(role,permission);

            rolePermission.setId(new RolePermissonId(role.getRoleId(),permission.getPermissionId()));
            role.addRolePermission(rolePermission);
            permission.addRolePermission(rolePermission);
            service.add(rolePermission);
            responseObject.setResponse(rolePermission);}
        }

        return ResponseEntity.ok(responseObject);
    }


    @GetMapping("/permissions/{permissionId}/roles")
    @ResponseBody
    public ResponseEntity getRoleByPermissionId(@PathVariable int permissionId) {
        List<Integer> listId = new ArrayList<>();
        Permission permission=permissionService.get(permissionId);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Role Ids Found Successfully");
        if (permission==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, permission not found!");
        }else{

            List<Role> list=service.getRolePermission(permission);

            for (int i=0;i<list.size();i++){
                listId.add(list.get(i).getRoleId());
            }
            responseObject.setResponse(listId);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/roles/{roleId}/permissions")
    @ResponseBody
    public ResponseEntity getPermissionByRoleId(@PathVariable int roleId) {
        List<Integer> listId = new ArrayList<>();
        Role role=roleService.get(roleId);
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"Permission Ids Found Successfully");
        if (role==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, role not found!");
        }else{

            List<Permission> list=service.getPermissionbyRole(role);
            for (int i=0;i<list.size();i++){
                listId.add(list.get(i).getPermissionId());
            }
            responseObject.setResponse(listId);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/roles/permissions")
    @ResponseBody
    public ResponseEntity getAll() {
        List<RolePermission> rolePermissions = service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"All rolepermissions Found Successfully");
        if (rolePermissions==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, rolepermissions not found!");
        }else{

            responseObject.setResponse(rolePermissions);
        }
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/permissions/roles")
    @ResponseBody
    public ResponseEntity getAlll() {
        List<RolePermission> rolePermissions = service.getAll();
        ResponseObject responseObject= new ResponseObject(HttpStatus.OK.toString(),"All rolepermissions Found Successfully");
        if (rolePermissions==null){
            responseObject.setResponseCode(HttpStatus.NOT_FOUND.toString());
            responseObject.setResponseDescription("Sorry, rolepermissions not found!");
        }else{

            responseObject.setResponse(rolePermissions);
        }
        return ResponseEntity.ok(responseObject);
    }

}

package inventoryservice.factory.admin;

import inventoryservice.domain.admin.Permission;

public class PermissionFactory {

    public static Permission getPermission(String permissionName){
        return new Permission.Builder()
                .permissionName(permissionName)
                .build();
    }
}

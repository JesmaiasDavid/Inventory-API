package inventoryservice.factory.admin;

import inventoryservice.domain.admin.ResponseObject;

public class ResponseObjectFactory {

    public static ResponseObject getResponseObject(String responseCode, String responseDescription){
        return new ResponseObject(responseCode,responseDescription);
    }

}

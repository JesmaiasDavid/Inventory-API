# inventoryservice

## Inventory Mircoservice Using JAVA SPRING

#### 1. Create a MySQL Database

#### 2.Go to application.properties and substitute "serviceinventory" for your database name 

#### 3.You can find 4 packages namely: 
####     **Domain** - contains the models(entities)
####     **Repository** - interface that implements the JPA Repository(allows you to do CRUD and more)
####     **Service** - implements the repository methods
####     **Controller** - where the routes can be found

#### 4. Inside the Controller we have annotations that describe the endpoint and the http method (example: **@PostMapping(value = "/invoices",consumes = "application/json")**)

#### 5. When you use "POST" and "PUT" the fields createdDateTime and lastModifiedDateTime are automatically generated and updated

#### 6. Get and Delete Methods require an Identfier to be retrieve and delete.

#### 7. POST for RoleUser and RolePermission send an empty Payload.


#### POST InvoiceProduct -> invoices/{invoiceId}/products and POST ProductSupplier->suppliers/{supplierId}/products
#### Example:

##### [
#####      {
#####       productId:1,
#####       quantity:100
#####       },
#####       {
#####       productId:2,
#####       quantity:200
#####       }
##### ]

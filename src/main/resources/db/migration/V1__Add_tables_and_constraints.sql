/*
* Version: 1
* Description: creating all the tables and their primary and foreing keys
*/

/* Category Table */
CREATE TABLE category (
	id INT AUTO_INCREMENT PRIMARY KEY ,
	category_name varchar(50) not null,
	created_user varchar(50) not null,
	last_modified_user varchar(50) not null,
	created_date_time datetime not null,
    last_modified_date_time datetime not null
);

CREATE TABLE category_sequence (next_val INT NOT NULL);
 INSERT INTO category_sequence VALUES (1);

/* Invoice Table */
CREATE TABLE invoice (
      invoice_id INT AUTO_INCREMENT PRIMARY KEY ,
       total double not null,
     status varchar(50) not null,
     date_time_generated datetime not null
);

CREATE TABLE invoice_sequence (next_val INT NOT NULL);
INSERT INTO invoice_sequence VALUES (1);


/*Product Table*/
CREATE TABLE product (
                          product_id INT AUTO_INCREMENT PRIMARY KEY ,
                          product_name varchar(50) not null,
                          product_buying_price double not null,
                          product_selling_price double  not null,
                          product_quantity double not null,
                          created_date_time datetime not null,
                          created_user varchar(50) not null,
                          last_modified_date_time datetime not null,
                          last_modified_user varchar(50) not null,
                          category_id INT not null ,
                          FOREIGN KEY (category_id) REFERENCES  category(id)

);
CREATE TABLE product_sequence (next_val INT NOT NULL);
INSERT INTO product_sequence VALUES (1);


/* Permission Table*/
CREATE TABLE permission (
                         permission_id INT AUTO_INCREMENT PRIMARY KEY ,
                         permission_name varchar(50) not null,
                         created_date_time datetime not null,
                         created_user varchar(50) not null,
                         last_modified_date_time datetime not null,
                         last_modified_user varchar(50) not null

);
CREATE TABLE permission_sequence (next_val INT NOT NULL);
INSERT INTO permission_sequence VALUES (1);


/*Role Table*/
CREATE TABLE role (
                         role_id INT AUTO_INCREMENT PRIMARY KEY ,
                         role_name varchar(50) not null,
                         created_date_time datetime not null,
                         created_user varchar(50) not null,
                         last_modified_date_time datetime not null,
                         last_modified_user varchar(50) not null

);
CREATE TABLE role_sequence (next_val INT NOT NULL);
INSERT INTO role_sequence VALUES (1);


/*Supplier Table*/
CREATE TABLE supplier (
                         supplier_id INT AUTO_INCREMENT PRIMARY KEY ,
                         supplier_name varchar(50) not null,
                         supplier_company varchar(50) not null,
                         created_date_time datetime not null,
                         created_user varchar(50) not null,
                         last_modified_date_time datetime not null,
                         last_modified_user varchar(50) not null

);
CREATE TABLE supplier_sequence (next_val INT NOT NULL);
INSERT INTO supplier_sequence VALUES (1);


/*User Table*/
CREATE TABLE user (
                         user_id INT AUTO_INCREMENT PRIMARY KEY ,
                         user_email varchar(50) not null,
                         user_first_name varchar(50) not null,
                         user_last_name varchar(50) not null,
                         user_address varchar(50) not null,
                         created_date_time datetime not null,
                         created_user varchar(50) not null,
                         last_modified_date_time datetime not null,
                         last_modified_user varchar(50) not null

);
CREATE TABLE user_sequence (next_val INT NOT NULL);
INSERT INTO user_sequence VALUES (1);

/* Invoice Supplier Table*/
CREATE  TABLE  invoice_product(

       invoice_id int NOT NULL,
       product_id int NOT NULL,
       product_quantity int NOT NULL,
        CONSTRAINT pk_invoice_product PRIMARY KEY (invoice_id,product_id),
        CONSTRAINT fk_invoice_id FOREIGN KEY (invoice_id)
        REFERENCES invoice(invoice_id),
       CONSTRAINT fk_product_id_ip FOREIGN KEY (product_id)
           REFERENCES product(product_id)

);

/* Product Supplier Table */
CREATE  TABLE  product_supplier(


                                  product_id int NOT NULL,
                                  supplier_id int NOT NULL,
                                  created_date_time datetime NOT NULL ,
                                  quantity_supplied int NOT NULL,
                                  CONSTRAINT pk_product_supplier PRIMARY KEY (product_id,supplier_id),
                                  CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id)
                                      REFERENCES supplier(supplier_id),
                                  CONSTRAINT fk_product_id_ps FOREIGN KEY (product_id)
                                      REFERENCES product(product_id)

);


/* Role Permission Table */
CREATE  TABLE  role_permission(

                                  role_id int NOT NULL,
                                   permission_id int NOT NULL,
                                   CONSTRAINT pk_role_permission PRIMARY KEY (role_id,permission_id),
                                   CONSTRAINT fk_role_id FOREIGN KEY (role_id)
                                       REFERENCES role(role_id),
                                   CONSTRAINT fk_permission_id FOREIGN KEY (permission_id)
                                       REFERENCES permission(permission_id)

);


/* Role User Table */
CREATE  TABLE  role_user(


                            role_id int NOT NULL,
                                  user_id int NOT NULL,
                                  CONSTRAINT pk_role_user PRIMARY KEY (role_id,user_id),
                                  CONSTRAINT fk_role_id_ru FOREIGN KEY (role_id)
                                      REFERENCES role(role_id),
                                  CONSTRAINT fk_user_id FOREIGN KEY (user_id)
                                      REFERENCES user(user_id)

);

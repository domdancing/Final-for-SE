#Drop Tables
DROP TABLE IF EXISTS invoice_items;
DROP TABLE IF EXISTS invoices;
DROP TABLE IF EXISTS items;

#Create items table
CREATE TABLE items(
	item_id int AUTO_INCREMENT PRIMARY KEY,
    item_name varchar(100) NOT NULL UNIQUE, 	#Maybe make an index for this since we might be searching it a lot?
    item_price int NOT NULL
);

#Create invoices table
CREATE TABLE invoices (
	invoice_id int AUTO_INCREMENT PRIMARY KEY,
    invoice_name varchar(100) NOT NULL UNIQUE,
    delivery_latitude double NOT NULL,	#Assuming for now that we always ship from the same location, if this changes we can make a default value for start_lat/start_long
    delivery_longitude double NOT NULL,
    customer_name varchar(100) NOT NULL,
    delivery_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

#Create join table for invoices and items (many to many relationship)
#This is also where we keep track of "how many" of a specific item are in a specific invoice.
CREATE TABLE invoice_items(
	invoice_id int,
    item_id int,
    quantity int NOT NULL DEFAULT 1,
    PRIMARY KEY (invoice_id, item_id),
    FOREIGN KEY (invoice_id) REFERENCES invoices (invoice_id)
		ON UPDATE CASCADE,
	FOREIGN KEY (item_id) REFERENCES items (item_id)
		ON UPDATE CASCADE
);

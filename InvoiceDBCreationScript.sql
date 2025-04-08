DROP TABLE IF EXISTS items;
CREATE TABLE items(
	item_id int AUTO_INCREMENT PRIMARY KEY,
    item_name varchar(100) NOT NULL,
    item_price int NOT NULL
);

DROP TABLE IF EXISTS invoices;
CREATE TABLE invoices (
	invoice_id int AUTO_INCREMENT PRIMARY KEY,
    delivery_miles int NOT NULL,
    customer_id int 	#Will be the foreign key for the customers table
);

#Joining the invoices and items table (many to many relationship)
DROP TABLE IF EXISTS invoice_items;
CREATE TABLE invoice_items(
	invoice_id int,
    item_id int,
    PRIMARY KEY (invoice_id, item_id),
    FOREIGN KEY (invoice_id) REFERENCES invoices (invoice_id)
		ON UPDATE CASCADE,
	FOREIGN KEY (item_id) REFERENCES items (item_id)
		ON UPDATE CASCADE
);

#We'll also need to make a customers table (a one-to-many relationship)


#N - Okay, I'll be using this space here to add some test data. 
# ---

INSERT INTO items(item_name, item_price)
VALUES
    ('Water Bottle', 15)
    ('Light Jacket', 25)
    ('Apple', 1);

INSERT INTO invoices(delivery_miles, customer_id)
    (30, 'Abigail"s Apples')
    (500, 'The Dirt Man')
    (773, 'Subset Industries');


# ---


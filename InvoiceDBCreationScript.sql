DROP TABLE IF EXISTS items;
CREATE TABLE items(
	item_id int AUTO_INCREMENT PRIMARY KEY,
    item_name varchar(100) NOT NULL,
    item_price double NOT NULL
);

DROP TABLE IF EXISTS invoices;
CREATE TABLE invoices (
	invoice_id int AUTO_INCREMENT PRIMARY KEY,
    delivery_miles int NOT NULL,
    customer_name varchar(100) NOT NULL,
    invoice_date timestamp DEFAULT CURRENT_TIMESTAMP
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
    ('Water Bottle', 15),
    ('Light Jacket', 25),
    ('Apple', 1);

INSERT INTO invoices(delivery_miles, customer_id)
VALUES
    (30, 'Abigail"s Apples'),
    (500, 'The Dirt Man'),
    (773, 'Subset Industries');


# ---






#Create View
#Create hashmap that uses invoice_id as a key to a reference to invoice object, 
#If the invoice id hasn't been added to the hashmap yet (simply see if the hasmap entry exists yet) then add it to the array of invoice objects
#pass in the item information as an item object and increment the invoice object's total price

DROP VIEW IF EXISTS formatted_invoices;
CREATE VIEW formatted_invoices AS
	SELECT iv.invoice_id AS id, iv.invoice_date AS date, 
		iv.customer_name AS cust_name, i.item_name AS item_name, 
        i.item_price AS item_price, iv.delivery_miles AS miles
    FROM invoices iv JOIN invoice_items ii
		ON iv.invoice_id = ii.invoice_id
        JOIN items i
        ON i.item_id = ii.item_id
    ;
    
    #public Invoice(String invoiceNumber, LocalDate date, 
		#String clientName, List<Item> items, 
		#double latitude, double longitude) {

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dominick valentine
 */
public class ConnectToDatabase {
    
// hi 
    //Hey, this is Ernie!
    //Hey, this is Ryan!

    private static final String URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12771146";
            
            
    private static final String USER = "sql12771146";
    private static final String PASSWORD = "XDSQkgzNe9";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
   
    public static void checkAddItem(Item item, String invoiceName) {
        //Check the items table to see if an item with this name has already been added (item names are unique)
        //If it hasn't, add it to the invoice table
        
        //Then, regardless of if we needed to add it, we have a separate query that searches for the same item name *again*
        //since that item name should defenitely (hopefully) be in the items table now.
        //We grab the item_id from the result
        
        //We then search for the invoice based on it's unique invoice_name, grab it's invoice_id
        //RUN THIS METHOD *AFTER* CREATING THE INVOICE WITH SAVE INVOICE OR ELSE THE invoice_name WON'T BE FOUND!
        
        //Finally, add the invoice_id and item_id to a new row in the item_invoices table.
    }
    
   public static void saveInvoice(Invoice invoice) {
    try (Connection conn = getConnection()) {
        String sql = "INSERT INTO invoices (invoice_name, delivery_latitude, delivery_longitude, customer_name, delivery_date) " +
                     "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        // Setting the values from the invoice object
        pstmt.setString(1, invoice.getInvoiceName()); // invoice_name
        pstmt.setDouble(2, invoice.getLatitude()); // delivery_latitude
        pstmt.setDouble(3, invoice.getLongitude()); // delivery_latitude
        pstmt.setString(4, invoice.getClientName()); // customer_name
        pstmt.setTimestamp(5, invoice.getDate()); // delivery_date
        
        pstmt.executeUpdate();
        
        //This didn't work, but I'm going to leave it here commented out for now
        /*
        //Get the generated id of the invoice we just inserted
        PreparedStatement lidStmnt = conn.prepareStatement("SELECT LAST_INSERT_ID()");
        ResultSet lidrs = lidStmnt.executeQuery();
        long insertId = lidrs.getLong(1);
        
        //Add items if any; we should also have a separate function to attach items to existing invoices
        ArrayList<Item> items = invoice.getItems();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            
            //Get Item Id; once we make item creation, we should save the item_id so we don't have to search this every time
            String itemQuery = "SELECT item_id FROM items WHERE name = " + item.getName();
            PreparedStatement itemStmnt = conn.prepareStatement(itemQuery);
            ResultSet itemrs = itemStmnt.executeQuery();
            long itemId = itemrs.getLong(1);
            
            //Match this invoice's id with this item's id.
            String itemInvoiceSql = "INSERT INTO invoice_items (invoice_id, item_id) " +
                     "VALUES (?, ?)";
            PreparedStatement itemInvoicePstmt = conn.prepareStatement(itemInvoiceSql);
            
            itemInvoicePstmt.setLong(1, insertId);
            itemInvoicePstmt.setLong(2, itemId);
            
            itemInvoicePstmt.executeUpdate();
        }
        */
        
        System.out.println("Saved invoice to DB.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public static ArrayList<Invoice> getAllInvoices() {
        try {
            //Make query and place the results in resulSet
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String queryString = "SELECT * FROM formatted_invoices";
            ResultSet resultSet = statement.executeQuery(queryString);
            
            HashMap<Integer, Invoice> invoiceMap = new HashMap<Integer, Invoice>();
            ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
            
            //Iterate through resultSet 
            while (resultSet.next()) {
             
                //Get invoiceID
                int invoiceId = resultSet.getInt("id");
                
                //Create item from this result
                String itemName = resultSet.getString("item_name");
                double itemPrice = resultSet.getDouble("item_price");
                Item item = new Item(itemName, itemPrice);
                
                if (invoiceMap.containsKey(invoiceId)) {
                    //Just add item to the existing invoices
                    Invoice addItemInvoice = invoiceMap.get(invoiceId);
                    addItemInvoice.addItem(item);
                } else {
                    //Create new invoice, add the item to it, and add the new invoice to the Array and the Hashmap
                    
                    //Create invoice object
                    //TO DO
                     String invoiceName = "" + invoiceId;
                    
                    Invoice invoice = null; //new Invoice(invoice_number, )
                    invoice.addItem(item);
                    //END OF TO DO
                    
                    //Add to Array List and Hash Map
                    invoiceList.add(invoice);
                    invoiceMap.put(invoiceId, invoice);
                }
                
            }
            
            //Return list of all invoices
            return invoiceList;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Should never reach here
        return null;
    }
}



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
                
                //Just for reference, ignore the comment below
                //Invoice(String invoiceNumber, LocalDate date, String clientName, List<Item> items, double latitude, double longitude) {
                
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
                     String invoiceNumber = "" + invoiceId;
                    
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



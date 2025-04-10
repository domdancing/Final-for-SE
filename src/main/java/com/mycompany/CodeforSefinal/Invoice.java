/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal;

import java.sql.Timestamp;
/**
 *
 * @author tangs
 */import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class Invoice {
    private String clientName;
    private String invoiceName;
    private Timestamp date; //Change local date to timestamp
    private ArrayList<Item> items;
    private double latitude;
    private double longitude;
    private double shippingPrice;
    private double totalPrice;
    private double distance;

    private static final String START_LOCATION = "-72.7945,42.1315"; // Warehouse or origin (WSU)
    private static final double COST_PER_KM = 0.75;

    public Invoice(String invoiceName, Timestamp date, String clientName, ArrayList<Item> items, double latitude, double longitude) {
        this.invoiceName = invoiceName;
        this.date = date;
        this.clientName = clientName;
        this.items = items;
        this.latitude = latitude;
        this.longitude = longitude;
        
        
        this.distance = calculateDistance(latitude, longitude);
        this.shippingPrice = calculateShippingPrice();
        this.totalPrice = calculateTotalPrice();
    }
 
    private double calculateDistance(double lat, double lon) {
        String destination = lon + "," + lat;
        try {
            String response = OpenRouteServiceAPI.getDistance(START_LOCATION, destination);
            return RouteParser.extractDistanceInKm(response); // assuming it returns distance in km
        } catch (Exception e) {
            System.out.println("Error calculating distance: " + e.getMessage());
            return 0;
        }
    }

    private double calculateShippingPrice() {
        return distance * COST_PER_KM;
    }

    private double calculateTotalPrice() {
        double itemTotal = 0;
        for (Item item : items) {
            itemTotal += item.getPrice();
        }
        return itemTotal + shippingPrice;
    }
    
    public String getInvoiceName() {
        return invoiceName;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getClientName() {
        return clientName;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    
    public void addItem(Item item) {
        items.add(item);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getDistance() {
        return distance;
    }
}

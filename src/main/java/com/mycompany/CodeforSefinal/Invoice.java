/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal;

/**
 *
 * @author tangs
 */import java.util.List;
import java.time.LocalDate;

public class Invoice {
    private String clientName;
    private String invoiceNumber;
    private LocalDate date;
    private List<Item> items;
    private double latitude;
    private double longitude;
    private double shippingPrice;
    private double totalPrice;

    private static final String START_LOCATION = "-72.7945,42.1315"; // Warehouse or origin (WSU)
    private static final double COST_PER_KM = 0.75;

   public Invoice(String invoiceNumber, LocalDate date, String clientName, List<Item> items, double latitude, double longitude) {
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.clientName = clientName;
        this.items = items;
        this.latitude = latitude;
        this.longitude = longitude;
        this.shippingPrice = calculateShippingPrice(latitude, longitude);
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateShippingPrice(double lat, double lon) {
        String destination = lon + "," + lat;
        try {
            String response = OpenRouteServiceAPI.getDistance(START_LOCATION, destination);
            double distance = RouteParser.extractDistanceInKm(response); // assuming it returns distance in km
            return distance * COST_PER_KM;
        } catch (Exception e) {
            System.out.println("Error calculating shipping price: " + e.getMessage());
            return 0;
        }
    }

    private double calculateTotalPrice() {
        double itemTotal = 0;
        for (Item item : items) {
            itemTotal += item.getPrice();
        }
        return itemTotal + shippingPrice;
    }
    
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getClientName() {
        return clientName;
    }

    public List<Item> getItems() {
        return items;
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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal;

/**
 *
 * @author tangs
 */
import java.util.List;

public class Invoice {
    private String clientName;
    private List<Item> items;
    private double latitude;
    private double longitude;
    private double shippingPrice;
    private double totalPrice;

    public Invoice(String clientName, List<Item> items, double latitude, double longitude) {
        this.clientName = clientName;
        this.items = items;
        this.latitude = latitude;
        this.longitude = longitude;
        this.shippingPrice = calculateShippingPrice(latitude, longitude);
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateShippingPrice(double lat, double lon) {
        // Simple placeholder for shipping price calculation
        double distance = Math.sqrt(Math.pow(lat, 2) + Math.pow(lon, 2));
        return distance * 0.5; // Cost per unit of distance
    }

    private double calculateTotalPrice() {
        double itemTotal = 0;
        for (Item item : items) {
            itemTotal += item.getPrice();
        }
        return itemTotal + shippingPrice;
    }

    // Getters (optional setters if needed)

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

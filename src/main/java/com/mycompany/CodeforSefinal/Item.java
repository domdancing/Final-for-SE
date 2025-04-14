/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal;

public class Item {
    private long itemId;
    private String name;
    private double price;

    public Item(long itemId, String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double newPrice) {
        price = newPrice;
    }
    
    public double getPrice() {
        return price;
    }


    public long getItemId() {
        return itemId;
    }


    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
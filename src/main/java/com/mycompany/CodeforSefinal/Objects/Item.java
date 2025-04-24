package com.mycompany.CodeforSefinal.Objects;

public abstract class Item {
    private int itemId;
    private String name;
    private double price;

    public Item(int itemId, String name, double price) {
        this.itemId = itemId;
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


    public int getItemId() {
        return itemId;
    }


    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
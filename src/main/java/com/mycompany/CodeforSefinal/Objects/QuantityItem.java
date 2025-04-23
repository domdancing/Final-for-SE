package com.mycompany.CodeforSefinal.Objects;

import com.mycompany.CodeforSefinal.Objects.Item;

public class QuantityItem extends Item {
    
    private int quantity;
    
    //Default quantity
    public QuantityItem(long itemId, String name, double price) {
        super(itemId, name, price);
        quantity = 1;
    }
    
     //Custom quantity
    public QuantityItem(long itemId, String name, double price, int quantity) {
        super(itemId, name, price);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}

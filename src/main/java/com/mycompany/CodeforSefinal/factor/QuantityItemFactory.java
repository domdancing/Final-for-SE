/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal.factor;

import com.mycompany.CodeforSefinal.Objects.Item;
import com.mycompany.CodeforSefinal.Objects.QuantityItem;

/**
 *
 * @author tangs
 */
public class QuantityItemFactory implements ItemFactory {
    private int quantity;

    public QuantityItemFactory(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public Item createItem(long itemId, String name, double price) {
        return new QuantityItem(itemId, name, price, quantity);
    }
}

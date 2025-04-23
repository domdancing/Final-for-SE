/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal.factor;

import com.mycompany.CodeforSefinal.Objects.Item;
import com.mycompany.CodeforSefinal.Objects.ReferenceItem;

/**
 *
 * @author tangs
 */
public class ReferenceItemFactory implements ItemFactory {
    
    @Override
    public Item createItem(long itemId, String name, double price) {
        return new ReferenceItem(itemId, name, price);
    }

   
}
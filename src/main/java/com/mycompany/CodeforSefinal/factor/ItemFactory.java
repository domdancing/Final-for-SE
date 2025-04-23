/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.CodeforSefinal.factor;

import com.mycompany.CodeforSefinal.Objects.Item;

/**
 *
 * @author tangs
 */
public interface ItemFactory {
     Item createItem(long itemId, String name, double price);
}

/*
samples 
ItemFactory referenceFactory = new ReferenceItemFactory();
Item item1 = referenceFactory.createItem(1L, "Washing Machine", 200.0);

ItemFactory quantityFactory = new QuantityItemFactory(3);
Item item2 = quantityFactory.createItem(2L, "Microwave", 150.0);




*/
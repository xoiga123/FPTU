
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hoai Bao
 */

// Purpose: information of a fruit object with it's constructors
public class Fruit {
    int id;
    String name;
    int price;
    int quantity;
    String origin;
    int quantityCustomer;
    CheckInput ci = new CheckInput();
    
    // Purpose: constructor to make a fruit object
    // Author: baonvhhe141782
    // 09/03/2020
    public Fruit() {
        System.out.print("Enter fruit ID: ");
        this.id = ci.checkInt(0, Integer.MAX_VALUE); // ID in range 0 - 2,147,483,647
        System.out.print("Enter fruit name: ");
        this.name = ci.checkString(); // name is a string
        System.out.print("Enter fruit price: ");
        this.price = ci.checkInt(0, Integer.MAX_VALUE); // price in range 0 - 2,147,483,647
        System.out.print("Enter fruit quantity: ");
        this.quantity = ci.checkInt(0, Integer.MAX_VALUE); // quantity in range 0 - 2,147,483,647
        System.out.print("Enter fruit origin: ");
        this.origin = ci.checkString(); // origin is a string
    }
    
    // Purpose: constructor of a fruit that a customer bought, with fewer information needed (no need for ID, shop quantity and origin)
    // Author: baonvhhe141782
    // 09/03/2020
    public Fruit(String name, int price, int quantityCustomer){
        this.name = name;
        this.price = price;
        this.quantityCustomer = quantityCustomer;
    }
    
    
}

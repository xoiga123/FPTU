
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hoai Bao
 */

// Purpose: program to manage a fruit shop
public class J1LP0023 {
    CheckInput ci = new CheckInput();
    ArrayList<Fruit> fruitList = new ArrayList<>(); // fruit list of the shop
    Hashtable<String, ArrayList> customerOrder = new Hashtable<>(); // store orders of customers
    
    // Purpose: display the menu and options
    // Author: baonvhhe141782
    // 09/03/2020
    void menu(){
        while (true){ // keep looping until exit is chosen
            System.out.println();
            System.out.println("FRUIT SHOP SYSTEM");
            System.out.println("1. Create fruit");
            System.out.println("2. View orders");
            System.out.println("3. Shopping (for buyer)");
            System.out.println("4. Exit");
            System.out.println("Please choose an option.\n");
            
            int x = ci.checkInt(1, 4); // only choose an option from 1 to 4
            switch (x){
                case 1:
                    create();
                    continue;
                case 2:
                    viewOrder();
                    continue;
                case 3:
                    shopping();
                    continue;
                case 4:
                    return;
            }
        }   
    }
    
    // Purpose: add a fruit to the fruit list of the shop
    // Author: baonvhhe141782
    // 09/03/2020
    void create(){
        do{
            fruitList.add(new Fruit()); // add a fruit to fruitList
            int IDCheck = fruitList.get(fruitList.size()-1).id;
            for (int i=0; i<fruitList.size()-1; i++){
                if (fruitList.get(i).id == IDCheck){
                    System.out.println("There is already a fruit with that ID in the list! Deleting the one you just put in...");
                    fruitList.remove(fruitList.size()-1);
                }
            }
            System.out.println("Do you want to continue (Y/N)?");
        } while (ci.checkAgreeOrDeny("Y", "N")); // check if the user want to continue or not
        displayFruit(); // display fruit list
    }
    
    // Purpose: display all the fruits in the fruit list
    // Author: baonvhhe141782
    // 09/03/2020
    void displayFruit(){
        System.out.println("List of fruit:");
        System.out.println("| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ |");
        for (Fruit fr : fruitList){
            System.out.println(String.format(" %1$-12d %2$-18s %3$-14s %4$-13d", fr.id, fr.name, fr.origin, fr.price)); // formatting each field with limited space, and align each field to the left
        }
    }
    
    // Purpose: display the buying list of a customer and total money needed
    // Author: baonvhhe141782
    // 09/03/2020
    void displayBuyingList(ArrayList<Fruit> buyingList){
        int sum = 0; // the total money needed to pay for the buying list
        System.out.println("   Product   | Quantity | Price | Amount");
        for (Fruit fr : buyingList){
            sum += fr.quantityCustomer * fr.price; // add the order money to the total
            System.out.println(String.format("%1$-13s %2$-10d %3$-7s %4$s", fr.name, fr.quantityCustomer, Integer.toString(fr.price) + "$", Integer.toString(fr.quantityCustomer*fr.price) + "$")); // formatting each field with limited space, and align each field to the left
        }
        System.out.println("Total: " + sum + "$");
    }
    
    // Purpose: view all the orders of the shop
    // Author: baonvhhe141782
    // 09/03/2020
    void viewOrder(){
        String personName; // the name of each customer
        if (customerOrder.isEmpty()){
            System.out.println("No one has bought anything yet!");
        }
        Enumeration e = customerOrder.keys(); // use enumeration to traverse the hashtable
        while (e.hasMoreElements()){ // haven't read all orders yet
            personName = (String) e.nextElement();
            System.out.println("Customer: " + personName);
            displayBuyingList(customerOrder.get(personName)); // display the buying list of that customer
        }
    }
    
    // Purpose: view the fruit list and shop for some fruit
    // Author: baonvhhe141782
    // 09/03/2020
    void shopping(){
        ArrayList<Fruit> buyingList = new ArrayList<>(); // buying list of a customer
        while (true){
            displayFruit();
            System.out.println("Please select fruit ID:");
            int chosen = ci.checkInt(0, Integer.MAX_VALUE); // fruit ID is between 0 - 2,147,483,647
            Fruit fr = null;
            for (Fruit i : fruitList){
                if (i.id == chosen) fr = i; // found the fruit with the ID that user inputted
            }
            if (fr == null){ // can't find the fruit with the ID that user inputted
                System.out.println("There is no item with ID " + chosen);
                return;
            }
            while (true){ // check that the shop has enough of that fruit for the user
                System.out.println("There is " + fr.quantity + " " + fr.name + " remaining in the shop.");
                System.out.println("Please input quantity:");
                chosen = ci.checkInt(0, Integer.MAX_VALUE); // customer quantity is between 0 - 2,147,483,647
                if (chosen == 0){ // did they inputted 0 just for fun? or an alternative safe exit if the fruit quantity of the shop is 0
                    System.out.println("Stop breaking my program, why did you buy 0?");
                    return;
                }
                if (fr.quantity < chosen){ // the shop doesn't have enough
                    System.out.println("There is not enough quantity, only " + fr.quantity + " available.");
                    continue;
                }
                break;
            }
            fr.quantity -= chosen; // decrease the quantity of that fruit the shop has
            buyingList.add(new Fruit(fr.name, fr.price, chosen)); // add that fruit to the buying list of customer
            System.out.println("Do you want to order now (Y/N)?");
            if (!ci.checkAgreeOrDeny("Y", "N")) continue; // if they don't want to order now then keep buying
            displayBuyingList(buyingList); // if the customer decided to finish the order, display the order the last time
            System.out.println("Please enter your name:");
            String personName = ci.checkString();
            customerOrder.put(personName, buyingList); // add the customer with his/her buying list to the shop order.
            return;
        }
    }
    
    // Purpose: run the program
    // Author: baonvhhe141782
    // 09/03/2020
    public static void main(String args[]){
        J1LP0023 run = new J1LP0023(); // create an instance of the class
        run.menu(); // run the menu method
    }
}

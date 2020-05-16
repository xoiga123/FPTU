/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0070;

/**
 *
 * @author Hoai Bao
 */

// Purpose: login system of a bank
public class Main {
    CheckInput ci = new CheckInput(); // manage input of user
    EBank e; // an instance of class ebank

    // Purpose: display the menu and options
    // Author: baonvhhe141782
    // 18/03/2020
    void menu(){
        System.out.println("-------Login Program-------");
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
        System.out.print("Please choose one option: ");
        int choice = ci.checkInt(1, 3); // can only choose between 1-3
        switch (choice){
            case 1:
                e = new EBank("VN"); // vietnamese interface
                return;
            case 2:
                e = new EBank("EN"); // english interface
                return;
            case 3:
                return; // exit
        }
    }
    
    // Purpose: run the program
    // Author: baonvhhe141782
    // 18/03/2020
    public static void main(String[] args) {
        Main test = new Main();
        test.menu(); // run the program
    }
    
}

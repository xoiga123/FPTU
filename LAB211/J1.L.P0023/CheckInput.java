
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hoai Bao
 */

// Purpose: manage input of the user
public class CheckInput {
    Scanner sc = new Scanner(System.in); // a scanner object for input
    
    // Purpose: check if the user inputted an integer between min and max
    // Author: baonvhhe141782
    // 09/03/2020
    int checkInt(int min, int max){
        int x; // x is the integer that the user will input
        while (true){ // keep looping if user doesn't input integer correctly
            if (!sc.hasNextInt()){ // if the next data in buffer is not an integer
                System.out.println("Please enter an integer!");
                sc.next(); // advance the scanner
                continue;
            }
            x = sc.nextInt(); // integer is received
            sc.nextLine(); // advance the scanner because nextInt() does not read the newline character
            if (x < min || x > max){ // integer is not in min max range
                System.out.println("Please enter a number between " + min + " and " + max);
                continue;
            }
            return x;
        }
    }
    
    // Purpose: reusing the scanner object, does not have to declare it elsewhere
    // Author: baonvhhe141782
    // 09/03/2020
    String checkString(){
        return sc.nextLine();
    }
    
    // Purpose: check if the user agrees or denies
    // Author: baonvhhe141782
    // 09/03/2020
    Boolean checkAgreeOrDeny(String agree, String deny){
        String x; // string that the user will input
        while (true){ // keep looping until the user agrees or disagrees
            x = sc.nextLine();
            x = x.toUpperCase(); // convert to uppercase to cover more cases
            if (x.equals(agree)) return true;
            if (x.equals(deny)) return false;
            System.out.println("Please enter " + agree + " or " + deny); // if the method didn't return, print error and loop again
        }
    }
}

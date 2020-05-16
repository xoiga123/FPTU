/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Hoai Bao
 */
public class checkExpression {
    Stack st = new Stack();
    String s = new String();
    Scanner sc = new Scanner(System.in);
    String opening = "({[";
    String closing = ")}]";
    
    void input(){
        System.out.println("Enter a string: ");
        s = sc.nextLine();
    }
    
    boolean check(){
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (opening.contains("" + ch)) st.push(ch);
            else{
                if (closing.contains("" + ch)){
                    if (st.isEmpty() || opening.indexOf((char) st.pop()) != closing.indexOf(ch)) return false;
                }
            }
        }
        if (!st.isEmpty()) return false;
        return true;
    }
    
    public static void main(String[] args) {
        checkExpression test = new checkExpression();
        test.input();
        if (test.check()){
            System.out.println("Valid");
        }
        else System.out.println("Invalid");
    }
    
}

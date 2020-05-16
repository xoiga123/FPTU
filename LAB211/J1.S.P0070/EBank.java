/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0070;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hoai Bao
 */

// Purpose: a class imitating a bank system
public class EBank {
    CheckInput ci = new CheckInput(); // manage input of user
    ResourceBundle messages; // which language the user is using
    String accountNumber = null;
    String password = null;
    String captchaGenerate = null;
    String captchaInput = null;
    String temp;
    
    // Purpose: a constructor to set local language then run the program
    // Author: baonvhhe141782
    // 18/03/2020
    public EBank(String locale) {
        if (locale.equals("VN")) setLocale(new Locale("VN"));
        else setLocale(new Locale("EN"));
        run();
    }
    
    // Purpose: run the program
    // Author: baonvhhe141782
    // 18/03/2020
    void run(){
        while (accountNumber == null){ // keep looping until user entered correct format for account number
            System.out.print(messages.getString("accountInput")); 
            temp = ci.checkString();
            accountNumber = checkAccountNumber(temp); // if the user entered correctly, return that string, else null
        }
        while (password == null){ // keep looping until user entered correct format for password
            System.out.print(messages.getString("passwordInput"));
            temp = ci.checkString();
            password = checkPassword(temp); // same as above
        }
        
        while (captchaInput == null){ // keep looping until user entered a character of the captcha correctly
            captchaGenerate = generateCaptcha(); // generate the captcha and store it
            System.out.print(messages.getString("captchaInput"));
            temp = ci.checkString();
            captchaInput = checkCaptcha(temp, captchaGenerate); // same as above
        }
        System.out.println(messages.getString("loginSuccessful"));
    }
    
    // Purpose: set the locale identifier to specify a language
    // Author: baonvhhe141782
    // 18/03/2020
    void setLocale(Locale locale){
        messages = ResourceBundle.getBundle("resources/messages", locale);
    }
    
    // Purpose: check if user entered correct format for account number
    // Author: baonvhhe141782
    // 18/03/2020
    String checkAccountNumber(String accountNumber){
        if (accountNumber.matches("\\d{10}")) return accountNumber; // a number of length 10
        System.out.println(messages.getString("accountError"));
        return null;
    }
    
    // Purpose: check if user entered correct format for password
    // Author: baonvhhe141782
    // 18/03/2020
    String checkPassword(String password){
        String enoughLength = "[a-zA-Z0-9]{8,31}"; // length is between 8-31
        String containNumber = ".*[0-9].*"; // contain a number
        String containLetter = ".*[a-zA-Z].*"; // contain a letter
        if (password.matches(enoughLength) && password.matches(containNumber) && password.matches(containLetter)) return password; // all satisfied
        System.out.println(messages.getString("passwordError"));
        return null;
    }
    
    // Purpose: generate a captcha of length 5
    // Author: baonvhhe141782
    // 18/03/2020
    String generateCaptcha(){
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rand = new Random(); // to generate random integers
        String result = ""; // the captcha which will be generated
        for (int i = 0; i < 5; i++) {
            result += alphabet.charAt(rand.nextInt(alphabet.length())); // add a random character from alphabet to the captcha
        }
        System.out.println(messages.getString("captchaGenerate") + result);
        return result;
    }
    
    // Purpose: check if user entered a character in captcha
    // Author: baonvhhe141782
    // 18/03/2020
    String checkCaptcha(String captchaInput, String captchaGenerate){
        if (captchaGenerate.equals(captchaInput)) return captchaInput; // if the user entered the whole captcha
        System.out.println(messages.getString("captchaError"));
        return null;
    }
}

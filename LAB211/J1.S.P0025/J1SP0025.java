/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0025;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Hoai Bao
 */

// Purpose: Read text file and normalize the text, then write to another file
public class J1SP0025 {
    Pattern p; // to use regex, easy for manipulating strings
    Matcher m; // must go with pattern to use regex
    StringBuffer content; // the content of the input text file
    BufferedReader br = null; // a buffered reader to read from a file
    BufferedWriter bw = null; // a buffered writer to write to a file
    
    // Purpose: the flow of the program
    // Author: baonvhhe141782
    // 11/03/2020
    void start(){
        read();
        content = normalizing(content);
        write();
    }
    
    // Purpose: read the content of the input file into a StringBuffer
    // Author: baonvhhe141782
    // 11/03/2020
    void read(){
        try{
            br = new BufferedReader(new FileReader(new File("input.txt"))); // read from file input.txt
            content = new StringBuffer(br.lines().collect(Collectors.joining(System.lineSeparator()))); // read all lines into a single string, so we have to join the newlines
            System.out.println(content);
        }
        catch (FileNotFoundException ex1){
            System.out.println("File input.txt not found!");
        }
        catch (IOException ex2){
            System.out.println("Cannot read file input.txt!");
        }
        finally{
            try{
                br.close(); // close the buffered reader
            }
            catch (IOException ex3){
                System.out.println("Cannot close file input.txt!");
            }
        }
    }
    
    // Purpose: write the content after normalizing to a text file
    // Author: baonvhhe141782
    // 11/03/2020
    void write(){
        try{
            bw = new BufferedWriter(new FileWriter("output.txt")); // write to file output.txt
            bw.write(content.toString()); // convert from StringBuffer to String first
            System.out.println("Normalized:");
            System.out.println(content);
        }
        catch (IOException ex4){
            System.out.println("Cannot write to file output.txt!");
        }
        finally{
            try{
                bw.close(); // close the buffered writer
            }
            catch (IOException ex5){
                System.out.println("Cannot close file output.txt!");
            }
        }
    }
    
    // Purpose: packing all the steps to normalize the content
    // Author: baonvhhe141782
    // 11/03/2020
    StringBuffer normalizing(StringBuffer text){
        text = oneSpaceBetweenWords(text);
        text = upperCaseAfterDot(text);
        text = noSpacesAroundQuote(text);
        text = firstCharUpperCase(text);
        text = noBlankSpace(text);
        text = noSpaceBeforeDot(text);
        text = dotAtTheEnd(text);
        return text;
    }
    
    // Purpose: only one space between words
    // Author: baonvhhe141782
    // 11/03/2020
    StringBuffer oneSpaceBetweenWords(StringBuffer text){
        p = Pattern.compile(" {2,}"); // regex to find atleast 2 spaces together
        m = p.matcher(text);
        return new StringBuffer(m.replaceAll(" ")); // replace them with only 1 space
    }
    
    // Purpose: first character of word after dot is in Uppercase and other words are in lower case (Still, there are special cases like names, which must be capitalized. Is there a way to handle them?)
    // Author: baonvhhe141782
    // 11/03/2020
    StringBuffer upperCaseAfterDot(StringBuffer text){
        p = Pattern.compile("\\. *([a-zA-Z])([a-zA-Z]*)"); // regex to find a dot followed by zero or more spaces, then group the first char to uppercase and group the following chars to lowercase
        m = p.matcher(text);
        StringBuffer result = new StringBuffer();
        
        while(m.find()){ // keep finding until there is no more
            m.appendReplacement(result, ". " + m.group(1).toUpperCase() + m.group(2).toLowerCase()); // replace with a dot with 1 space, then an uppercase char, and then all lowercase.
        }
        m.appendTail(result); // append the rest after last regex found
        return result;
    }
    
    // Purpose: no spaces before and after sentence or word phrases in quotes (“”)
    // Author: baonvhhe141782
    // 11/03/2020
    StringBuffer noSpacesAroundQuote(StringBuffer text){
        p = Pattern.compile("\" *(.*?) *\""); // regex to find a quote, then zero or more spaces, then atleast one string of anything having the most length possible, then zero or more spaces, and a quote
        m = p.matcher(text);
        StringBuffer result = new StringBuffer();
        
        while(m.find()){
            m.appendReplacement(result, "\"" + m.group(1) + "\""); // replace with a quote, the non-whitespace chars found, and a quote
        }
        m.appendTail(result);
        return result;
    }
    
    // Purpose: first character of word in first line is in Uppercase
    // Author: baonvhhe141782
    // 11/03/2020
    StringBuffer firstCharUpperCase(StringBuffer text){
        p = Pattern.compile("\\W*(\\w)"); // regex to find zero or more non-word char, then group a word char after that
        m = p.matcher(text);
        StringBuffer result = new StringBuffer();
        
        while(m.find()){
            m.appendReplacement(result, m.group(1).toUpperCase()); // replace the word char we found in uppercase
            break; // we only do this once, at the beginning of the first line (since there a no dots before it).
        }
        m.appendTail(result);
        return result;
    }
    
    // Purpose: no blank line between lines
    // Author: baonvhhe141782
    // 11/03/2020
    StringBuffer noBlankSpace(StringBuffer text){
        p = Pattern.compile("\\s{2,}"); // regex to find atleast 2 whitespace (including newline) chars
        m = p.matcher(text);
        StringBuffer result = new StringBuffer();
        
        while(m.find()){
            m.appendReplacement(result, "\n"); // replace them with only 1 newline
        }
        m.appendTail(result);
        return result;
    }
    
    // Purpose: no space between comma or dot and word in front of it
    // Author: baonvhhe141782
    // 11/03/2020
    StringBuffer noSpaceBeforeDot(StringBuffer text){
        p = Pattern.compile(" *([,.:])"); // regex to find zero or more spaces, then group either a comma or a dot or a colon
        m = p.matcher(text);
        StringBuffer result = new StringBuffer();
        
        while(m.find()){
            m.appendReplacement(result, m.group(1)); // replace them with whatever delimiter we found
        }
        m.appendTail(result);
        return result;
    }
    
    // Purpose: must have dot at the end of text
    // Author: baonvhhe141782
    // 11/03/2020
    StringBuffer dotAtTheEnd(StringBuffer text){
        // we basically don't need regex here to make it simple, but could be achievable for complex cases
        if (text.charAt(text.length()-1) != '.') text.insert(text.length(), '.'); // if the last char is not a dot, insert a dot after it
        return text;
    }
    
    // Purpose: run the program
    // Author: baonvhhe141782
    // 11/03/2020
    public static void main(String[] args) {
        J1SP0025 run = new J1SP0025(); // create an instance of the class
        run.start(); // run the program
    }
}

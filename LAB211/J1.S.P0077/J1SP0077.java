/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0077;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoai Bao
 */

// Purpose: list and search file by content
public class J1SP0077 {
    CheckInput ci = new CheckInput(); // manage input of user

    // Purpose: display menu and options
    // Author: baonvhhe141782
    // 20/03/2020
    void menu() throws IOException{
        String fileSource; // path of file
        String word; // word needs finding
        String source; // path of folder
        
        while (true){
            System.out.println("=========== Word Program ===========");
            System.out.println("1. Count Word In File");
            System.out.println("2. Find File By Word");
            System.out.println("3. Exit");
            int choice = ci.checkInt(1, 3); // can only choose between 1-3
            switch (choice){
                case 1:
                    System.out.println("-------- Count Word --------");
                    System.out.print("Enter Path: ");
                    fileSource = ci.checkString();
                    System.out.print("Enter Word: ");
                    word = ci.checkString();
                    int count = countWordInFile(fileSource, word); // number of the word we found in that file
                    System.out.println("Count: " + count);
                    continue;
                case 2:
                    System.out.println("-------- Find File By Word --------");
                    System.out.print("Enter Path: ");
                    source = ci.checkString();
                    System.out.print("Enter Word: ");
                    word = ci.checkString();
                    System.out.println("------------ File Name ------------");
                    List<String> files = getFileNameContainsWordInDirectory(source, word); // a list of files that contain the word we have to find
                    if (files == null){ // no file with that word found
                        System.out.println("No file with word " + word + " found.");
                        continue;
                    }
                    for (String name: files){ // print out all the file names
                        System.out.println(name);
                    }
                    continue;
                case 3: // exit
                    return;
            }
        }
    }
    
    // Purpose: count number of word we have to find in a file
    // Author: baonvhhe141782
    // 20/03/2020
    int countWordInFile(String fileSource, String word) throws FileNotFoundException, IOException{
        if (!ci.checkExists(fileSource)) return 0; // the file doesn't exist
        int count = 0;
        File f = new File(fileSource); // connect to that file
        FileReader fr = new FileReader(f); // help with reading 
        BufferedReader br = new BufferedReader(fr); // help with reading
        String s; // a line in that file
        while ((s = br.readLine()) != null){ // keep reading new lines
            System.out.println(s); // print out a line
            for (String w: s.split("\\W")){ // for words in a line
                if (w.equals(word)) count++; // if we found the word, increase the counter
            }
        }
        return count;
    }
    
    // Purpose: get a list of all file names that contain the word we have to find
    // Author: baonvhhe141782
    // 20/03/2020
    List<String> getFileNameContainsWordInDirectory(String source, String word) throws FileNotFoundException, IOException{
        if (!ci.checkExists(source)) return null; // the folder doesn't exist
        List<String> result = new ArrayList(); // initiate the result array list
        File folder = new File(source); // connect to that folder
        File[] allFiles = folder.listFiles(); // all files inside that folder
        String s; // a line in a file
        for (File file: allFiles){ // for every file inside
            Boolean found = false; // if we found the word, add it to result and skip to next file
            FileReader fr = new FileReader(file); // help with reading
            BufferedReader br = new BufferedReader(fr); // help with reading
            while ((s = br.readLine()) != null){ // for every line
                if (found) break;
                for (String w: s.split("\\W")){ // for every word inside a line
                    if (w.equals(word)){ // found the word
                        result.add(file.getName()); // add the file name to the result list
                        found = true;
                        break; // skip to next file
                    }
                }
            }
        }
        return result;
    }
    
    // Purpose: run the program
    // Author: baonvhhe141782
    // 20/03/2020
    public static void main(String[] args) throws IOException {
        J1SP0077 run = new J1SP0077();
        run.menu(); // just run
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0075;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoai Bao
 */

// Handle file program
public class J1SP0075 {
    CheckInput ci = new CheckInput(); // manage input
    
    // Purpose: display menu and options
    // Author: baonvhhe141782
    // 23/03/2020
    void menu() throws IOException{
        String path;
        
        while (true){ // keep displaying menu until exit is selected
            System.out.println("======== File Processing ========");
            System.out.println("1. Check path");
            System.out.println("2. Get file name with type java");
            System.out.println("3. Get file with size greater than input");
            System.out.println("4. Write more content to file");
            System.out.println("5. Read file and count characters");
            System.out.println("6. Exit");
            System.out.print("Please choose one option: ");
            int choice = ci.checkInt(1, 6); // can only choose between 1-6
            switch (choice){
                case 1:
                    System.out.println("-------- Check path --------");
                    System.out.println("Enter path:");
                    path = ci.checkString();
                    checkInputPath(path);
                    continue;
                case 2:
                    System.out.println("-------- Get file name with type java --------");
                    path = ci.checkExists(); // make sure that path exists
                    List<String> result = getAllFileNameJavaInDirectory(path);
                    for (String name: result){
                        System.out.println(name); // print out the name of those files with java extension
                    }
                    System.out.println("Found " + result.size() + " file!");
                    continue;
                case 3:
                    System.out.println("-------- Get file with size greater than input --------");
                    System.out.println("Enter size in KB (integer):");
                    int size = ci.checkInt(0, Integer.MAX_VALUE); // size is an integer
                    path = ci.checkExists(); // make sure that path exists
                    File[] result3 = getFileWithSizeGreaterThanInput(path, size);
                    continue;
                case 4:
                    System.out.println("-------- Write more content to file --------");
                    System.out.println("Enter content:");
                    String content = ci.checkString();
                    path = ci.checkExists(); // make sure that path exists
                    Boolean done = appendContentToFile(path, content); 
                    if (done) System.out.println("Write done.");
                    else System.out.println("Something went wrong.");
                    continue;
                case 5:
                    System.out.println("-------- Read file and count characters --------");
                    path = ci.checkExists(); // make sure that path exists
                    int count = countWord(path);
                    System.out.println("Number of words: " + count);
                    continue;
                case 6: // exit
                    return;
            }
        }
    }
    
    // Purpose: check if the path exists, and if it does, check if it is a directory or a file
    // Author: baonvhhe141782
    // 23/03/2020
    void checkInputPath(String path){
        File file = new File(path); // connect to that path
            if (!file.exists()){ // if it doesn't exist
                System.out.println("Path doesn't exists.");
                return;
            }
            if (file.isDirectory()){ // if it exists and is a directory
                System.out.println("Path to directory.");
                return;
            }
            System.out.println("Path to file."); // it exists and is a file
            return;
    }
    
    // Purpose: return a list of file names which has java extension in the path (.java)
    // Author: baonvhhe141782
    // 23/03/2020
    List<String> getAllFileNameJavaInDirectory(String path){
        List<String> result = new ArrayList<>(); // create an empty list
        File directory = new File(path); // connect to that path
        File[] files = directory.listFiles(); // all the files in that folder
        for (File file: files){ // for every file
            String fileName = file.getName(); // get the name
            int lastDot = fileName.lastIndexOf("."); // find the extension
            if (fileName.substring(lastDot + 1).equals("java")) result.add(fileName); // if the extension is "java", add it to the result list
        }
        return result;
    }
    
    // Purpose: return a list of files which has size in KB greater than inputted
    // Author: baonvhhe141782
    // 23/03/2020
    File[] getFileWithSizeGreaterThanInput(String path, int size){
        File directory = new File(path); // connect to that folder
        File[] files = directory.listFiles(); // all the files in that folder
        File[] result = new File[files.length]; // initiate a list of file which has maximum length is the number of files in that folder
        int count = 0; // number of files which has size in KB greater than inputted
        size = size * 1024; // size inputted is in KB, size returned from length() method is in bytes
        for (File file: files){ // for every file
            if (file.length() > size){ // if it is larger than inputted
                result[count] = file; // add it to result list
                count++; // increase the count
            } 
        }
        for (int i = 0; i < count; i++){
            System.out.println(result[i].getName()); // print name of all those files in result list
        }
        System.out.println("Found " + count + " file!");
        return result;
    }
    
    // Purpose: write more content into a text file
    // Author: baonvhhe141782
    // 23/03/2020
    boolean appendContentToFile(String path, String contentInput){
        try{
            FileWriter fr = new FileWriter(path, true); // true for append mode
            BufferedWriter br = new BufferedWriter(fr); // help with writing
            br.write(contentInput); // write the content to the file
            br.close();  // close the connection
        }
        catch (Exception e){
            return false; // something went wrong
        }
        return true; // all went well
    }
    
    // Purpose: count the number of words in a text file
    // Author: baonvhhe141782
    // 23/03/2020
    int countWord(String path) throws FileNotFoundException, IOException{
        int count = 0; // number of words
        File f = new File(path); // connect to that file
        FileReader fr = new FileReader(f); // help with reading 
        BufferedReader br = new BufferedReader(fr); // help with reading
        String s; // a line in that file
        while ((s = br.readLine()) != null){ // keep reading new lines
            System.out.println(s); // print out a line
            count += s.split("\\W").length; // increase the count by the amount of words in that line
        }
        return count;
    }
    
    // Purpose: run the program
    // Author: baonvhhe141782
    // 23/03/2020
    public static void main(String[] args) throws IOException {
        J1SP0075 run = new J1SP0075();
        run.menu();
    }
}

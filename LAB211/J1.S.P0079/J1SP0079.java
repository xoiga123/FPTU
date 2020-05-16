/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0079;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Hoai Bao
 */

// Purpose: a program to zip and unzip file
public class J1SP0079 {
    CheckInput ci = new CheckInput(); // manage input
    
    // Purpose: display the menu and options
    // Author: baonvhhe141782
    // 16/03/2020
    void menu() throws IOException{
        while (true){ // keeps looping until the user chooses exit
            int choice;
            System.out.println("========= Zipper program =========");
            System.out.println("1. Compression");
            System.out.println("2. Extraction");
            System.out.println("3. Exit");
            System.out.print("Please choose one option: ");
            choice = ci.checkInt(1, 3); // can only choose between 1 - 3
            switch (choice){
                case 1: // compress
                    System.out.println("Enter source folder:");
                    String pathSrc = ci.checkExists(); // making sure that the source folder exists
                    System.out.println("Enter name of zip file:");
                    String fileZipName = ci.checkString();
                    System.out.println("Enter destination folder:");
                    String pathCompress = ci.checkString();
                    if(compressTo(pathSrc, fileZipName, pathCompress)) System.out.println("Compress successfully");
                    else System.out.println("Compress failed");
                    continue;
                case 2: // extract
                    System.out.println("Enter path of zip file:");
                    String pathZipFile = ci.checkExists(); // making sure that the zip file exists
                    System.out.println("Enter destination folder:");
                    String pathExtract = ci.checkString();
                    if(extractTo(pathZipFile, pathExtract)) System.out.println("Extract successfully");
                    else System.out.println("Extract failed");
                    continue;
                case 3: // exit
                    return; 
            }
        }
    }
    
    // Purpose: create some instances to help with the compress method and return the result
    // First para: source folder
    // Second para: name of zip file
    // Third para: path of zip file
    // Author: baonvhhe141782
    // 16/03/2020
    boolean compressTo(String pathSrc, String fileZipName, String pathCompress) throws FileNotFoundException, IOException{
        try (FileOutputStream fos = new FileOutputStream(pathCompress + File.separator + fileZipName); // create an output file, which is the zip file
                ZipOutputStream zipOut = new ZipOutputStream(fos)) { // zip output stream is connected to that zip file
            File files = new File(pathSrc); // files is connected to the folder pathSrc
            compress(files, files.getName(), zipOut); // call the compress method
        }
        catch (Exception e){
            return false; // something went wrong
        }
        return true; // all went well
    }
    
    // Purpose: use recursion to completely compress all subfolders inside the source folder
    // First para: a folder/file
    // Second para: name of that folder/file
    // Third para: the zip output stream that we created before
    // Author: baonvhhe141782
    // 16/03/2020
    void compress(File files, String filesName, ZipOutputStream zipOut) throws IOException{  
        if (files.isDirectory()){ // if it is a folder
            zipOut.putNextEntry(new ZipEntry(filesName + "/")); // in the zip file, create a folder with that folder's name and a slash (to indicate it's a folder)
            zipOut.closeEntry(); // close the entry
            File[] children = files.listFiles(); // all the files/folders inside the folder passed as parameter
            for (File child : children){
                compress(child, filesName + "/" + child.getName(), zipOut); // compress all the files/folders inside
            }
        }
        else{ // if it is a file
            FileInputStream fis = new FileInputStream(files); // to get data from file
            ZipEntry zipEntry = new ZipEntry(filesName); // create a zip entry
            zipOut.putNextEntry(zipEntry); // passing that zip entry to the zip output stream
            byte[] buffer = new byte[2048];
            int length;
            while ((length = fis.read(buffer)) >= 0) zipOut.write(buffer, 0, length); // basically just writing data to the zip output stream
            fis.close(); // close the connection to file
        }
    }
    
    // Purpose: extract a zip file
    // First para: path of that zip file
    // Second para: path to extract to
    // Author: baonvhhe141782
    // 16/03/2020
    boolean extractTo(String pathZipFile, String pathExtract) throws FileNotFoundException, IOException{
        try{
            File destDir = new File(pathExtract); // create a connection to the path to be extracted to
            if (!destDir.exists()) { // if the path doesn't exist
                destDir.mkdir(); // create it
            }
            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(pathZipFile)); // a zip input stream to read from the zip file

            ZipEntry entry = zipIn.getNextEntry(); // create a zip entry
            while (entry != null) { // iterates over entries in the zip file
                String filePath = pathExtract + File.separator + entry.getName(); // path to be extracted
                if (!entry.isDirectory()) { // if the entry is a file, extracts it
                    new File(filePath).getParentFile().mkdirs(); // make a connection to a file to extract to and also create the parent directories
                    extractFile(zipIn, filePath); // extract it
                } else { // if the entry is a folder, make a folder
                    File dir = new File(filePath); // make a connection to a folder
                    dir.mkdirs(); // make the folder
                }
                zipIn.closeEntry(); // close the zip entry
                entry = zipIn.getNextEntry(); // pass in the next zip entry
            }
            zipIn.close(); // close the zip input stream
        }
        catch (Exception e){
            return false; // something went wrong
        }
        return true; // all went well
    }

    // Purpose: help extracting a file
    // First para: the zip input stream that we created which has the data of the zip file
    // Second para: the file path to extract that file to
    // Author: baonvhhe141782
    // 16/03/2020
    void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath)); // stream to write data to file
        byte[] bytesIn = new byte[2048];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read); // basically writes data to a file
        }
        bos.close(); // close the stream
    }
    
    // Purpose: run the program
    // Author: baonvhhe141782
    // 16/03/2020
    public static void main(String[] args) throws IOException {
        J1SP0079 run = new J1SP0079(); // create an instance
        run.menu(); // run
    }
    
}

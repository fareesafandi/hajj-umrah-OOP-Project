package org.wanderers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.util.ArrayList;

import org.wanderers.RegistrationService;
import org.wanderers.Account;

public class DataStorage {
   /*
   Method to store the instance of objects to txt file
   - this class will receive Arraylists of objects from other class
   -  
   */

   public static final String DELIMITER = "|"; 

   public DataStorage() {

   }
  
   private ArrayList<Account> accountCollection;
   //how do we get account collection/instance of account? 
   
   public void saveToFile(ArrayList<Account> accounts) {
    //save all the instance in txt file

    /*REMOVE AFTER TEST */
    this.accountCollection = accounts; 
    /*REMOVE AFTER TEST */

    loadFromFile();
    File file = new File("Data.txt"); 
    
    try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(file))){
        
        for(int i = 0;i < accountCollection.size();i++) {

            //iterating through the saved instances to save it.
            dataWriter.write(accountCollection.get(i).toFileFormat());
            if(file.exists()) {
                System.out.println("Data: "+ accountCollection.get(i).toFileFormat()+ "Successfully Saved!");
            }

            
        }
        
        dataWriter.close();
    } catch(IOException e) {
       System.out.println("Error reading file: " + e.getMessage()); 
    }

   }

   public void loadFromFile() {
    
    File file = new File("Data.txt"); 
    Account account = new Account(); 

    try (BufferedReader dataReader = new BufferedReader(new FileReader(file))){
       
       String line; 
       
        while((line = dataReader.readLine()) != null) {

           if(line.trim().isEmpty()) { //removes whitespaces
            continue; 
           }
           
           String[] data = line.split(DELIMITER); 
           String type = data[0].trim(); 
           
           switch (type) {
            case "USER":
                Account newAccount = account.fromFileFormat(line);
                accountCollection.add(newAccount);
                System.out.println("User: " +"["+data[1].trim()+"]"+" Successfully read!");  
                break;
           
            default:
                break;
           }
        }
       
        dataReader.close();
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }    

   }

}

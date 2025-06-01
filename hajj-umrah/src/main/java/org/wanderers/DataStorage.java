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
import org.wanderers.User;

public class DataStorage {
   /*
   Method to store the instance of objects to txt file
   - this class will receive Arraylists of objects from other class
   - This method should read from the file first when initializing: Data Integrity Preservation.
   ISSUES:
   - [1/6/2025]Data get overwrited everytime new instances of DataStorage is made 
   */

   //Collection of instances
   private ArrayList<User> UserCollection;
   //how do we get account collection/instance of account? 

   public static final String DELIMITER = "\\|"; 

   public DataStorage() {
    //making sure the ArrayList is not empty
    this.UserCollection = new ArrayList<>(); 
    loadFromFile();
   }
   
   //Class Specific Methods
   /*
    - Instances will be added one-by-one using a Class Specific method
    - Add, Delete property of the account.  
    */

    //Account Class
    public void addAccount(User user) {
        this.UserCollection.add(user); 
    }

    public void deleteAccount(User user) {
        this.UserCollection.remove(user); 
    }

    public ArrayList<User> getUsers() {
        return UserCollection;  
    }

   public void saveToFile() {
    //save all the instance in txt file
    /*
    - This method will be involved in multiple Service Class
    - The parameter should be empty and ArrayList of instance should be pass to 
      DataStorage. 
     */

    File file = new File("Data.txt"); 
    
    try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(file))){
        
        for(int i = 0;i < UserCollection.size();i++) {

            //iterating through the saved instances to save it.
            dataWriter.write(UserCollection.get(i).toFileFormat());
            dataWriter.newLine();

            if(file.exists()) {
                System.out.println("Data: "+ UserCollection.get(i).toFileFormat()+ "Successfully Saved!");
            }
        }
        
        dataWriter.close();
    } catch(IOException e) {
       System.out.println("Error reading file: " + e.getMessage()); 
    }

   }

   public void loadFromFile() {
    
    File file = new File("Data.txt"); 

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
                String userID = data[1]; 
                String name = data[2];  
                String password = data[3];
                int noPhone = Integer.parseInt(data[4]); 
                String email = data[5];

                User newAccount = new User(userID, name, password, noPhone, email); 
                this.UserCollection.add(newAccount);
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

package org.wanderers;

import org.wanderers.User;

import java.util.ArrayList; 
import java.lang.IndexOutOfBoundsException;

public class RegistrationService implements GeneralService {
  
    private ArrayList<User> UserCollection; 
    private DataStorage store;
    public static final String ID_DELIMITER = "\\.";  
    

    public RegistrationService(DataStorage store) {
        this.UserCollection = store.getUsers();
        this.store = store;  
    }

   public void createAccount(String name, String password, int noPhone, String email) {
   
    //Validation methods
    String userID = GenerateID();
    //Check for redundancy


    User newUser = new User(userID, name, password, noPhone, email); 
    
    System.out.println("User Account: " + "[" + userID + "] Successfully created!");
    store.addAccount(newUser);
    store.saveToFile();
   }

   public ArrayList<User> getUserAccountCollection() {
    return UserCollection; 
   }
   
   public String GenerateID() {
    /*
     * ID Naming Convention
     *  - for UserID starts with 'U' and then any 
     *    numbers that starts with 1 separated by period(.) as regex 
     *  - Any FK (Foreign Keys) that are associated with 
     *    a userID PK must have the same number
     *  - the first userID will be: U1 
     */
    String ID = "U.1"; 
    
    for(int i = 0; i < UserCollection.size(); i++) {
        if(ID.equals(UserCollection.get(i).userID)) {
            try {
                String[] fragmentedID =  ID.split(ID_DELIMITER);
                /*Debug*/System.out.println("Debug: fragmentID[]: "+fragmentedID[0] + ", " + fragmentedID[1]);
                int idNumber = Integer.parseInt(fragmentedID[1].trim()) + 1; 
                ID = String.join(".", fragmentedID[0].trim(), Integer.toString(idNumber));  
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Error Generating ID: " + e.getMessage());
            }
        }
    }
    
    return ID; 
   }


   public void findUserID() {
    //find user ID from the collection of account arraylist
   }

}

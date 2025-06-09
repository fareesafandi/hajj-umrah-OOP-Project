package org.wanderers;

import org.wanderers.User;

import java.util.ArrayList; 
import java.lang.IndexOutOfBoundsException;

public class RegistrationService implements GeneralService {
  
    private ArrayList<User> UserCollection; 
    private DataStorage store;
    public static final String ID_DELIMITER = "\\.";  
    
<<<<<<< HEAD
=======
    private String userID; 
>>>>>>> 2f35afc741aabe6bddf2795e06a9cfbcdf2e9903

    public RegistrationService(DataStorage store) {
        this.UserCollection = store.getUsers();
        this.store = store;  
    }

<<<<<<< HEAD
   public void createAccount(String name, String password, int noPhone, String email) {
   
    //Validation methods
    String userID = GenerateID();
    //Check for redundancy


    User newUser = new User(userID, name, password, noPhone, email); 
=======
   public String createAccount(String name, String password, int noPhone, String email, String gender) {
    /*
     * createAccount() method 
     * - method should return a status to indicate the creation of an account.
     * - status indicator should create a response control for UI 
     * - 0 - Account creation successful 
     * - 1 - Account creation failed
     * - The method willl return a String to indicate message in account creation.
     * - the String will be passed to controller/UI class.
     */
    userID = GenerateID();
    //Validation methods
    if(userID == null && name == null && password == null && noPhone == 0 && email == null) {
        System.out.println("Please enter all credential");
    }
    

    //TODO:Validation of data in storage file.
    /* - Check for null field 
     * - Iterate through UserCollection ArrayList
     * - Check for all the details, if all the credential are equal then 
     * the account itself is redundant, prompt the user to login
     */
    for(int i = 0;i < UserCollection.size();i++)  {

        //Check for UserID redundancy 
        if(userID.equals(UserCollection.get(i).userID)) {
            //How will UI response to error message?
            System.out.println("UserID already in used!");
            return "UserID Already in Used!"; 
        }

        if(name.equals(UserCollection.get(i).name)) {
            System.out.println("The name of this user already exist");

            return "The name of this user already exist"; 
        }

        if(password.equals(UserCollection.get(i).password)) {
            System.out.println("Password already exist, please choose another password.");
        }

    }

    User newUser = new User(userID, name, password, noPhone, email, gender); 
>>>>>>> 2f35afc741aabe6bddf2795e06a9cfbcdf2e9903
    
    System.out.println("User Account: " + "[" + userID + "] Successfully created!");
    store.addAccount(newUser);
    store.saveToFile();
<<<<<<< HEAD
=======

    return "Account Creation Successful, Please Login"; //account creation successful
>>>>>>> 2f35afc741aabe6bddf2795e06a9cfbcdf2e9903
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

<<<<<<< HEAD

   public void findUserID() {
    //find user ID from the collection of account arraylist
=======
   public String getGeneratedUserID() {
    return this.userID; 
   }

   public User findUserByID(String userID) {
    //find user ID from the collection of account arraylist
    
    for(int i = 0;i < UserCollection.size(); i++) {
        if(userID == UserCollection.get(i).userID) {
            return UserCollection.get(i);
        }
    }

    return UserCollection.get(0); 
>>>>>>> 2f35afc741aabe6bddf2795e06a9cfbcdf2e9903
   }

}

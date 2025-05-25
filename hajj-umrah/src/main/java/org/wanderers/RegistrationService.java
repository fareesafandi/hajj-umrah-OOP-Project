package org.wanderers;

import org.wanderers.Account;

import javafx.scene.chart.PieChart.Data;

import java.util.ArrayList; 

public class RegistrationService implements GeneralService {
  
    private ArrayList<Account> AccountCollection; 

    DataStorage store = new DataStorage(); 
    
    public RegistrationService() {
        this.AccountCollection = new ArrayList<>(); 
    }

   public void createAccount(String name, String password, int noPhone, String email) {
   
    //Validation methods
    String userID = GenerateUserID();     

    Account newAccount = new Account(userID, name, password, noPhone, email); 
    AccountCollection.add(newAccount);
    
    System.out.println("Account " + "[" + userID + "] Successfully created!");

    store.saveToFile(AccountCollection);
   }

   public String GenerateUserID() {
    //This method will generate and validate available userid
    String userID = "TESTACCOUNT";

    return userID; 
   }


   public void findUserID() {
    //find user ID from the collection of account arraylist
   }

   
   public void save(ArrayList<Object> account) {
    //save data to DataStorge from Arraylist
   }


   public void load(ArrayList<Object> account) {
    //load data from DataStorage to Arraylist of Account Object
   }


}

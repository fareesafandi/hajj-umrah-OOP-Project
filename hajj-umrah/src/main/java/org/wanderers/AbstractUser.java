package org.wanderers;

public abstract class AbstractUser {
   
   protected String userID; 
   protected String name; 
   protected String password; 
   protected int noPhone; 
   protected String email; 
   protected String gender; 
   
   //Getter methods

   public String getUserID() {

    return userID;  
   }

   public String getGender() {
      return gender; 
   }

   public String getName() {

    return name; 
   }

   public String password() {

    return password; 
   }

   public int getNoPhone() {
    
    return noPhone; 
   }

   public String email() {

    return email; 
   } 

   //Setter methods

        
   public void setUserID(String userID) {
    this.userID = userID; 
   }

   public void setName(String name) {
    this.name = name;
   } 

   public void setPassword(String password) {
    this.password = password; 
   }

   public void setNoPhone(int noPhone) {
    this.noPhone = noPhone; 
   }

   public void setEmail(String email) {
    this.email = email; 
   }
    
}

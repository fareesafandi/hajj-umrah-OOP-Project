package org.wanderers.test;

import org.wanderers.*;
import java.util.Scanner;

public class TestRegistrationFeature {
    
    public static void main(String[] args) {
       
           DataStorage store = new DataStorage();  
           RegistrationService register = new RegistrationService(store); 
        
        while (true) {
           
           Scanner opt = new Scanner(System.in);
           Scanner choice = new Scanner(System.in); 
           Scanner num = new Scanner(System.in);
           
           System.out.println("Enter details to create an account: ");
           System.out.print("Name: ");
           String name = opt.nextLine();
           System.out.print("Password: ");
           String password = opt.nextLine();
           System.out.print("Email: ");
           String email = opt.nextLine(); 
           System.out.print("Gender: ");
           String gender = opt.nextLine(); 
           System.out.print("Phone Number(No '-'): ");
           int noPhone = opt.nextInt(); 
           
           String status = register.createAccount(name, password, noPhone, email, gender);

           if(status == null) {
            System.out.println("Account creation failed");
           } 
           System.out.println("Press 'q' to quit testing.");
           char c = choice.nextLine().charAt(0); 

           if(c == 'q') {
            System.exit(0);
           }
        
        }
    }
}

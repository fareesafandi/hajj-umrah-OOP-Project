package org.wanderers.test;

import org.wanderers.*;
import java.util.Scanner;

public class testMain {
    
    public static void main(String[] args) {
       
        while (true) {
           
           Scanner opt = new Scanner(System.in);
           Scanner choice = new Scanner(System.in); 
           Scanner num = new Scanner(System.in); 
           RegistrationService register = new RegistrationService(); 
           
           System.out.println("Enter details to create an account: ");
           System.out.print("Name: ");
           String name = opt.nextLine();
           System.out.print("Password: ");
           String password = opt.nextLine();
           System.out.print("Email: ");
           String email = opt.nextLine(); 
           System.out.print("Phone Number(No '-'): ");
           int noPhone = opt.nextInt(); 
           
           register.createAccount(name, password, noPhone, email);

           
           System.out.println("Press 'q' to quit testing.");
           char c = choice.nextLine().charAt(0); 

           if(c == 'q') {
            System.exit(0);
           }
        }
    }
}

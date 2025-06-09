package org.wanderers;

import java.util.ArrayList;

public class Authentication {

 private DataStorage store;

    public Authentication(DataStorage store) {
        this.store = store;
    }


    public User authenticateUser(String userID, String password) {
        ArrayList<User> users = store.getUsers();
        for (User user : users) {
            // Assuming password() method is the correct one to get the password from User object
            if (user.getUserID().equals(userID) && user.password().equals(password)) {
                return user; // Authentication successful
            }
        }
        return null; // Authentication failed
    }
}

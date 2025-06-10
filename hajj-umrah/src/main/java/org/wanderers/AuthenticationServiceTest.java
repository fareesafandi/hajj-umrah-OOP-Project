package org.wanderers;

import java.util.ArrayList;

public class AuthenticationServiceTest {

    public static void main(String[] args) {
        System.out.println("--- Starting AuthenticationService Manual Tests ---");

       
        DataStorage dataStorage = new DataStorage();
        Authentication authenticationService = new Authentication(dataStorage);

        // so these users will be written to your Data.txt file.
        // If you run this multiple times, these users might accumulate in Data.txt.
        User user1 = new User("U.101", "Alice Smith", "alicepass", 111222333, "alice@example.com", "Female");
        User user2 = new User("U.102", "Bob Johnson", "bobpass", 444555666, "bob@example.com", "Male");
        User user3 = new User("U.103", "Charlie Brown", "charliepass", 777888999, "charlie@example.com", "Male");

        System.out.println("Attempting to add test users to DataStorage...");
        dataStorage.addAccount(user1);
        dataStorage.addAccount(user2);
        dataStorage.addAccount(user3);
        System.out.println("Test users added. DataStorage now has " + dataStorage.getUsers().size() + " users.");
        // DataStorage's constructor already loads from file, and addAccount saves,
        // so users should be accessible.

        // 3. Perform Test Cases and print results

        System.out.println("\n--- Test Case 1: Successful Authentication ---");
        User authenticatedUser1 = authenticationService.authenticateUser("U.101", "alicepass");
        if (authenticatedUser1 != null && authenticatedUser1.getUserID().equals("U.101")) {
            System.out.println("  RESULT: SUCCESS. User U.101 authenticated.");
        } else {
            System.out.println("  RESULT: FAILURE. User U.101 authentication failed unexpectedly.");
        }

        System.out.println("\n--- Test Case 2: Incorrect Password ---");
        User authenticatedUser2 = authenticationService.authenticateUser("U.102", "wrongpass");
        if (authenticatedUser2 == null) {
            System.out.println("  RESULT: SUCCESS. User U.102 authentication failed as expected (incorrect password).");
        } else {
            System.out.println("  RESULT: FAILURE. User U.102 authentication succeeded unexpectedly with wrong password.");
        }

        System.out.println("\n--- Test Case 3: User Not Found ---");
        User authenticatedUser3 = authenticationService.authenticateUser("U.999", "anypass");
        if (authenticatedUser3 == null) {
            System.out.println("  RESULT: SUCCESS. User U.999 authentication failed as expected (user not found).");
        } else {
            System.out.println("  RESULT: FAILURE. User U.999 authentication succeeded unexpectedly (user not found).");
        }

        System.out.println("\n--- Test Case 4: Authenticate another valid user ---");
        User authenticatedUser4 = authenticationService.authenticateUser("U.103", "charliepass");
        if (authenticatedUser4 != null && authenticatedUser4.getUserID().equals("U.103")) {
            System.out.println("  RESULT: SUCCESS. User U.103 authenticated.");
        } else {
            System.out.println("  RESULT: FAILURE. User U.103 authentication failed unexpectedly.");
        }

        System.out.println("\n--- Test Case 5: Empty User ID ---");
        User authenticatedUser5 = authenticationService.authenticateUser("", "alicepass");
        if (authenticatedUser5 == null) {
            System.out.println("  RESULT: SUCCESS. Authentication failed as expected with empty User ID.");
        } else {
            System.out.println("  RESULT: FAILURE. Authentication succeeded unexpectedly with empty User ID.");
        }

        System.out.println("\n--- Finished AuthenticationService Manual Tests ---");
    }

}

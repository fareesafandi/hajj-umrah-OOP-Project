package org.wanderers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.util.ArrayList;

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
   private ArrayList<TrackDetail> TrackDetailCollection; // New collection for tracking details

   public static final String DELIMITER = "\\|";

   public DataStorage() {
    //making sure the ArrayList is not empty
    this.UserCollection = new ArrayList<>();
    this.TrackDetailCollection = new ArrayList<>(); // Initialize new collection
    loadFromFile(); // Load all data types
   }

   //Class Specific Methods
   /*
    - Instances will be added one-by-one using a Class Specific method
    - Add, Delete property of the account.
    */

    //Account Class (User)
    public void addAccount(User user) {
        this.UserCollection.add(user);
        saveToFile(); // Save immediately after adding
    }

    public void deleteAccount(User user) {
        this.UserCollection.remove(user);
        saveToFile(); // Save immediately after deleting
    }

    public ArrayList<User> getUsers() {
        return UserCollection;
    }

    // Methods for TrackingService 
    // save the changes of tracking details
    public void saveTrackingDetail(TrackDetail detail) {
        this.TrackDetailCollection.add(detail);
        saveToFile();
    }
    
    // Assuming you need to update an existing tracking detail
    // This method will update in the file
    public void updateTrackingDetail(TrackDetail updatedDetail) {
        for (int i = 0; i < TrackDetailCollection.size(); i++) {
            if (TrackDetailCollection.get(i).getTracID().equals(updatedDetail.getTracID())) {
                TrackDetailCollection.set(i, updatedDetail);
                saveToFile(); // Save after updating
                return;
            }
        }
        System.out.println("Tracking detail with ID " + updatedDetail.getTracID() + " not found for update.");
    }

    public ArrayList<TrackDetail> getAllTrackDetails() {
        return TrackDetailCollection;
    }

   public void saveToFile() {
    //save all the instance in txt file
    /*
    - This method will be involved in multiple Service Class
    - The parameter should be empty and ArrayList of instance should be pass to
      DataStorage.
     */

    File file = new File("Data.txt");

    try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(file))){ // FileWriter(file) overwrites
        // Write UserCollection
        for(User user : UserCollection) {
            dataWriter.write(user.toFileFormat()); // This will write all fields including passportID etc.
            dataWriter.newLine();
        }

        // Write TrackDetailCollection
        for(TrackDetail detail : TrackDetailCollection) {
            dataWriter.write(detail.toFileFormat());
            dataWriter.newLine();
        }

        System.out.println("All data successfully saved to Data.txt!");

    } catch(IOException e) {
       System.out.println("Error writing to file: " + e.getMessage());
    }

   }

   public void loadFromFile() {

    File file = new File("Data.txt");
    // Clear collections before loading to prevent duplicates on subsequent loads
    UserCollection.clear();
    TrackDetailCollection.clear();

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
                // Using the static fromFileFormat of User to create a new User object and do the same for tracking as well
                User newUser = User.fromFileFormat(line);
                if (newUser != null) {
                    this.UserCollection.add(newUser);
                    System.out.println("User: " +"["+newUser.getUserID()+"]"+" Successfully read!");
                    // The orginal add one by one but this version just use UserCollection
                }
                break;
            case "TRACKDETAIL": 
                TrackDetail newTrackDetail = TrackDetail.fromFileFormat(line);
                if (newTrackDetail != null) {
                    this.TrackDetailCollection.add(newTrackDetail);
                    System.out.println("TrackDetail: " +"["+newTrackDetail.getTracID()+"]"+" Successfully read!");
                }
                break;
            default:
                break;
           }
        }
        // dataReader.close(); 
        // i dont know what this do
        // fix?
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }

   }

}
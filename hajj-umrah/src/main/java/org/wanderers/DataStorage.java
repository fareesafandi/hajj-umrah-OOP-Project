package org.wanderers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.net.URL;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class DataStorage {

    private ArrayList<User> UserCollection;
    private ArrayList<TrackDetail> TrackDetailCollection;

    public static final String DELIMITER = "\\|";
    private static final String DATA_FILE_NAME = "Data.txt"; // Define filename as a constant
    private File dataFile; // Store the file reference

    public DataStorage() {
        this.UserCollection = new ArrayList<>();
        this.TrackDetailCollection = new ArrayList<>();
        
        try {
            // This logic reliably finds the file in the 'resources' folder.
            URL resourceUrl = getClass().getClassLoader().getResource(DATA_FILE_NAME);
            if (resourceUrl == null) {
                System.out.println("Error: Could not find the data file '" + DATA_FILE_NAME + "' in resources.");
                // You might want to create the file if it doesn't exist
                 File resourcesDir = new File("src/main/resources");
                 if (!resourcesDir.exists()) resourcesDir.mkdirs();
                 this.dataFile = new File(resourcesDir, DATA_FILE_NAME);
                 if (this.dataFile.createNewFile()) {
                     System.out.println("New data file created at: " + this.dataFile.getAbsolutePath());
                 }
            } else {
                 this.dataFile = new File(resourceUrl.toURI());
            }

            loadFromFile(); // Load all data types
        } catch (URISyntaxException | IOException e) {
            System.out.println("Error initializing DataStorage file path: " + e.getMessage());
        }
    }

    // ... (keep your addAccount, deleteAccount, saveTrackingDetail, etc. methods here, they don't need to change)
    public void addAccount(User user) {
        this.UserCollection.add(user);
        saveToFile();
    }

    public void deleteAccount(User user) {
        this.UserCollection.remove(user);
        saveToFile();
    }

    public ArrayList<User> getUsers() {
        return UserCollection;
    }

    public void saveTrackingDetail(TrackDetail detail) {
        this.TrackDetailCollection.add(detail);
        saveToFile();
    }
    
    public void updateTrackingDetail(TrackDetail updatedDetail) {
        for (int i = 0; i < TrackDetailCollection.size(); i++) {
            if (TrackDetailCollection.get(i).getTracID().equals(updatedDetail.getTracID())) {
                TrackDetailCollection.set(i, updatedDetail);
                saveToFile();
                return;
            }
        }
        System.out.println("Tracking detail with ID " + updatedDetail.getTracID() + " not found for update.");
    }

    public ArrayList<TrackDetail> getAllTrackDetails() {
        return TrackDetailCollection;
    }


    public void saveToFile() {
        // Now this method will write to the correct file path.
        if (this.dataFile == null) {
            System.out.println("Error: dataFile is not initialized, cannot save.");
            return;
        }
        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(this.dataFile))) {
            for (User user : UserCollection) {
                dataWriter.write(user.toFileFormat());
                dataWriter.newLine();
            }

            for (TrackDetail detail : TrackDetailCollection) {
                dataWriter.write(detail.toFileFormat());
                dataWriter.newLine();
            }

            System.out.println("All data successfully saved to " + this.dataFile.getName() + "!");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        if (this.dataFile == null || !this.dataFile.exists()) {
             System.out.println("Data file does not exist. Starting with empty collections.");
             return;
        }

        UserCollection.clear();
        TrackDetailCollection.clear();

        try (BufferedReader dataReader = new BufferedReader(new FileReader(this.dataFile))) {
            String line;
            while ((line = dataReader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(DELIMITER);
                if (data.length > 0) {
                    String type = data[0].trim();
                    switch (type) {
                        case "USER":
                            User newUser = User.fromFileFormat(line);
                            if (newUser != null) {
                                this.UserCollection.add(newUser);
                            }
                            break;
                        case "TRACKDETAIL":
                            System.out.println("DEBUG: Found a TRACKDETAIL line. Attempting to parse...");
                            TrackDetail newTrackDetail = TrackDetail.fromFileFormat(line);
                            if (newTrackDetail != null) {
                                this.TrackDetailCollection.add(newTrackDetail);
                                System.out.println("DEBUG: SUCCESS! Loaded TrackDetail with ID: " + newTrackDetail.getTracID());
                            } else {
                                System.out.println("DEBUG: FAILED to parse TRACKDETAIL line: " + line);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            System.out.println("Data loaded successfully. Users: " + UserCollection.size() + ", Tracking Details: " + TrackDetailCollection.size());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
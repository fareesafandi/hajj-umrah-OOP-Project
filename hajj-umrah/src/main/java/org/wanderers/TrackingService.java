package org.wanderers;

import java.util.ArrayList;
import java.util.Random;

// keyword = "fix"

/*
 * This calss contains method that allows the tracking of Hajj participants 
 VAR    
    - trackingCollection: ArrayList<TrackDetail>
    - hajjJourneyStatus: String
    - bookingProgress: String
 METHOD
    + TrackingService(DataStorage store)
    + cancelBooking(boolean): void
    + viewBookingProgress(): void
    + getHajjJourneyStatus(): void
    + generatedID(): String
    */
// 2414771

public class TrackingService implements GeneralService {

    private ArrayList<TrackDetail> trackingCollection; // Collection of individual tracking details
    private DataStorage dataStorage; // An instance of the DataStorage to store and use data into the txt file

    // Constructor 
    public TrackingService(DataStorage store) {
        this.dataStorage = store;
        this.trackingCollection = new ArrayList<>(); // Initialize the collection
        
        loadTrackingDetailsFromStorage(); // Load existing tracking details from dataStorage 
    }

    //fix
    // this simply a call method from DataStorage to get all previously saved TrackDetail objects
    private void loadTrackingDetailsFromStorage() {
        ArrayList<TrackDetail> loadedTracDetails = dataStorage.getAllTrackDetails();
        System.out.println("Loading tracking details from data storage...");
        if (loadedTracDetails != null) {
            this.trackingCollection.addAll(dataStorage.getAllTrackDetails()); // get ALL of the track details, at least it should
            System.out.println("Loaded " + loadedTracDetails.size() + " tracking details."); // Display how many tracking details already existing
        } else {
            System.out.println("No tracking details found in storage or an error occurred during loading.");
        }    
    }

    @Override
    public String GenerateID() {
        Random random = new Random(); 
        StringBuilder idBuilder = new StringBuilder(); 

        for (int i = 0; i < 6; i++) {  // loop for 6 times to create a 6 digit ID
            int digit = random.nextInt(10); // Generates a random integer between 0 and 9
            idBuilder.append(digit); // Accumulate the digits into the idbuilder
        }
        return idBuilder.toString();
    }

    // Adds a new tracking detail to the trackingCollection and saves it.

    public void addTrackingDetail(TrackDetail detail) {
        this.trackingCollection.add(detail); //add detail into the array list
        dataStorage.saveTrackingDetail(detail); // Save the new detail using DataStorage
        System.out.println("Tracking detail for User ID " + detail.getCurrUserID() + " added.");
    }

    // Cancels a booking associated with a specific tracking ID.
    // This method would update the bookingProgress within a TrackDetail.
    public void cancelBooking(String tracID) {
        // Find the TrackDetail and update its bookingProgress (Ongoing/Pending/Approved --> Terminated)
        for (TrackDetail detail : trackingCollection) {
            if (detail.getTracID().equals(tracID)) {
                detail.setBookingProgress("Cancelled");
                dataStorage.updateTrackingDetail(detail); // update in the savefile
                System.out.println("Booking with Tracking ID " + tracID + " has been terminated.");
                return; 
            }
        }
        System.out.println("Tracking ID " + tracID + " not found. Cannot cancel booking.");
    }

    // Views the booking progress for a specific tracking ID
    public void viewBookingProgress(String tracID) {
        for (TrackDetail detail : trackingCollection) {
            if (detail.getTracID().equals(tracID)) {
                System.out.println("Booking Progress for Tracking ID " + tracID + ": " + detail.getBookingProgress());
                return; 
            }
        }
        System.out.println("Tracking ID " + tracID + " not found.");
    }

    // Retrieves the Hajj journey status for a specific tracking ID.
    public void getHajjJourneyStatus(String tracID) {
        for (TrackDetail detail : trackingCollection) {
            if (detail.getTracID().equals(tracID)) {
                System.out.println("Hajj Journey Status for Tracking ID " + tracID + ": " + detail.getHajjJourneyStatus());
                return;
            }
        }
        System.out.println("Tracking ID " + tracID + " not found.");
    }

    // Get a specific TrackDetail object
    // optional
    /*
     * public TrackDetail getTrackDetail(String tracID) {
        for (TrackDetail detail : trackingCollection) {
            if (detail.getTracID().equals(tracID)) {
                return detail;
            }
        }
        return null;
    }
     */
    
}
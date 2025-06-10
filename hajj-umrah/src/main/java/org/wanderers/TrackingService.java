package org.wanderers;

import java.util.ArrayList;
import java.util.Random;

// keyword = "fix"

//This calss contains method that allows the tracking of Hajj participants 
//  VAR    
//     - trackingCollection: ArrayList<TrackDetail>
//     - hajjJourneyStatus: String
//     - bookingProgress: String
//  METHOD
//     + TrackingService(DataStorage store)
//     + cancelBooking(boolean): void
//     + viewBookingProgress(): void
//     + getHajjJourneyStatus(): void
//     + generatedID(): String
//     */
// // 2414771

public class TrackingService implements GeneralService {

    private ArrayList<TrackDetail> trackingCollection; // Collection of individual tracking details
    private DataStorage dataStorage; // An instance of the DataStorage to store and use data into the txt file

    // Constructor 
    public TrackingService(DataStorage store) {
        this.dataStorage = store;
        this.trackingCollection = new ArrayList<>(); // Initialize the collection
        
        loadTrackingDetailsFromStorage(); // Load existing tracking details from dataStorage 
        System.out.println("DEBUG: TrackingService initialized. Collection size is: " + this.trackingCollection.size());
    }

    //fix
    // this simply a call method from DataStorage to get all previously saved TrackDetail objects
    private void loadTrackingDetailsFromStorage() {
        ArrayList<TrackDetail> loadedTracDetails = dataStorage.getAllTrackDetails();
        System.out.println("[DEBUG] TrackingService: Loading tracking details from data storage... hmm"); // hmm
        if (loadedTracDetails != null) {
            this.trackingCollection.clear(); // hmm: Ensure fresh load into TrackingService's collection
            this.trackingCollection.addAll(loadedTracDetails); // hmm: Corrected to use loadedTracDetails
            System.out.println("[DEBUG] TrackingService: Loaded " + this.trackingCollection.size() + " tracking details into service's collection. hmm"); // hmm
        } else {
            System.out.println("[DEBUG] TrackingService: No tracking details found in storage or an error occurred during loading. hmm"); // hmm
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
    public TrackDetail getTrackDetail(String tracID) {
        System.out.println("[DEBUG] TrackingService: Searching for Tracking ID: '" + tracID); // hmm
        System.out.println("[DEBUG] TrackingService: Current trackingCollection size: " + trackingCollection.size() + " hmm"); // hmm
        for (TrackDetail detail : trackingCollection) {
            System.out.println("[DEBUG] TrackingService: Comparing with stored ID: '" + detail.getTracID()); // hmm
            if (detail.getTracID().equals(tracID)) {
                System.out.println("[DEBUG] TrackingService: Match found for ID: '" + tracID); // hmm
                return detail;
            }
        }
        System.out.println("[DEBUG] TrackingService: Tracking ID '" + tracID + "' not found in collection. hmm"); // hmm
        return null;
    }

    // redundancy........
    // fix 
    // Retrieves all TrackDetail objects associated with a specific User ID.
    public ArrayList<TrackDetail> getTrackDetailsByUserID(String userID) {
        System.out.println("[DEBUG] TrackingService: Searching for Tracking Details by User ID: '" + userID);
        ArrayList<TrackDetail> foundDetails = new ArrayList<>();
        System.out.println("[DEBUG] TrackingService: Current trackingCollection size: " + trackingCollection.size());
        for (TrackDetail detail : trackingCollection) {
            System.out.println("[DEBUG] TrackingService: Comparing with stored User ID: '" + detail.getCurrUserID());
            if (detail.getCurrUserID().equals(userID)) {
                foundDetails.add(detail);
                System.out.println("[DEBUG] TrackingService: Match found for User ID: '" + userID + "' with Tracking ID: '" + detail.getTracID());
            }
        }
        if (foundDetails.isEmpty()) {
            System.out.println("[DEBUG] TrackingService: No tracking details found for User ID: '" + userID);
        }
        return foundDetails;
    }
    
}
 
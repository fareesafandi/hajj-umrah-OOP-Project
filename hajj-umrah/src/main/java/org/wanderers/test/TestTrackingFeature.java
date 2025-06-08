package org.wanderers.test;

import org.wanderers.*;

import java.util.Scanner;

public class TestTrackingFeature {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 

        DataStorage dataStorage = new DataStorage();
        TrackingService trackingService = new TrackingService(dataStorage);

        System.out.println("Test for the Tracking Services");

        // Simulate getting a user ID
        System.out.print("Enter User ID to track (e.g., U.1): "); //use this bcs... this is an existing ID 
        String currUserID = input.nextLine();

        // Check if the user exists (optional, but can assist for the exception handling part)
        //fix
        //?
        User user = null;
        for (User u : dataStorage.getUsers()) {
            if (u.getUserID().equals(currUserID)) { // compare the IDs of the user from dataStorage to the input ID
                user = u;
                break;
            }
        }
        if (user == null) {
            System.out.println("User with ID " + currUserID + " not found. ");
            // for now even if the ID is not found, create a new tracking entry anyway for the sake of testting
            //fix
        }

        // Generate a new tracking ID
        String generatedTracID = trackingService.GenerateID();

        // Placeholder for actual data (these would typically come from user input or a database)
        // Initial states for a new tracking entry
        // fix
        String hajjJourneyStatus = "Pending";
        String bookingProgress = "Confirmed";
        String hajjProgress = "Not Started";

        // Create a new TrackDetail object for this user
        // (currUserID | generatedTracID | hajjJourneyStatus | bookingProgress | hajjProgress)
        TrackDetail newTrackDetail = new TrackDetail(currUserID, generatedTracID, hajjJourneyStatus, bookingProgress, hajjProgress);

        // Add the new TrackDetail to the TrackingService (which will save it via DataStorage)
        //fix
        //?
        trackingService.addTrackingDetail(newTrackDetail);

        // Display the tracking details
        System.out.println("\nTracking details added for User ID: " + newTrackDetail.getCurrUserID());
        System.out.println("Tracking ID: " + newTrackDetail.getTracID());
        System.out.println("Hajj Journey Status: " + newTrackDetail.getHajjJourneyStatus());
        System.out.println("Booking Progress: " + newTrackDetail.getBookingProgress());
        System.out.println("Hajj Progress: " + newTrackDetail.getHajjProgress());

        System.out.println("\nTesting the Methods ");

        // Test viewBookingProgress
        System.out.print("Enter Tracking ID to view booking progress (e.g., " + generatedTracID + "): ");
        String tracIDToView = input.nextLine();
        trackingService.viewBookingProgress(tracIDToView);

        // Test getHajjJourneyStatus
        System.out.print("Enter Tracking ID to view Hajj journey status (e.g., " + generatedTracID + "): ");
        String tracIDToGetStatus = input.nextLine();
        trackingService.getHajjJourneyStatus(tracIDToGetStatus);

        // Test cancelBooking (optional: ask for confirmation)
        System.out.print("Do you want to cancel a booking? Enter Tracking ID to cancel (or 'no'): ");
        String tracIDToCancel = input.nextLine();
        //fix
        //?
        if (!tracIDToCancel.equalsIgnoreCase("no")) {
            trackingService.cancelBooking(tracIDToCancel);
            // After cancellation, view progress again to confirm
            System.out.println("\n--- After Cancellation Attempt ---");
            trackingService.viewBookingProgress(tracIDToCancel);
        }

        //display all of the data inside dataStorage (this is for the tracking parts only)
        System.out.println("\nAll tracking details currently in DataStorage:");
        for (TrackDetail td : dataStorage.getAllTrackDetails()) {
            System.out.println("  User ID: " + td.getCurrUserID() + ", Tracking ID: " + td.getTracID() +
                               ", Status: " + td.getHajjJourneyStatus() + ", Booking: " + td.getBookingProgress() +
                               ", Hajj Progress: " + td.getHajjProgress());
        }

        input.close(); 
    }
}
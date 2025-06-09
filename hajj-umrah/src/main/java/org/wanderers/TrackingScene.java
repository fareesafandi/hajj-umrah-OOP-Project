package org.wanderers;

import java.util.ArrayList;

import org.wanderers.TrackingService;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane; // Import ScrollPane


public class TrackingScene {
    
    private TextField userIDField; 
    private Text resultText; 
    private Button trackingButton;

    private TextField tracIDToCancelField;
    private Button cancelButton;

    private DataStorage storage; 
    private TrackingService trackingService;

    TrackingScene (DataStorage storage) {
        this.storage = storage; 
        this.trackingService = new TrackingService(storage);
    }

    public Scene initializeScene() {

        HBox mainHbox = new HBox();         
        mainHbox.setPadding(new Insets(20));
        mainHbox.setSpacing(40); 
        mainHbox.setAlignment(Pos.CENTER);
        // Main HBox background (Root background color)
        mainHbox.setStyle("-fx-background-color: #C8FFBE;"); // Tea Green for the root

        VBox leftVbox = new VBox();
        leftVbox.setPadding(new Insets(20)); 
        leftVbox.setSpacing(25); 
        leftVbox.setAlignment(Pos.TOP_LEFT);
        leftVbox.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 5);"); 

        Label titleHeader = new Label("HAJJ AND UMRAH MANAGEMENT TRACKING SYSTEM");  
        titleHeader.setFont(new Font("Arial", 22)); 
        titleHeader.setStyle("-fx-font-weight: bold; -fx-text-fill: #295135; -fx-padding: 0 0 10px 0; -fx-border-color: #ecf0f1; -fx-border-width: 0 0 1px 0;"); // Car Poly Green for title

        // Section for Viewing Tracking Details
        VBox viewTrackingSection = new VBox();
        viewTrackingSection.setSpacing(10);
        viewTrackingSection.setPadding(new Insets(15)); 
        viewTrackingSection.setStyle("-fx-background-color: #ffffff; -fx-border-color: #C8FFBE; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 5, 0, 0, 2);"); // Tea Green border

        Label userIDLabel = new Label("User ID"); 
        userIDLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #295135;"); // Car Poly Green for labels
        userIDField = new TextField(); 
        userIDField.setPromptText("Enter your User ID (e.g., U.1)");
        userIDField.setPrefWidth(250); 
        userIDField.setStyle("-fx-pref-width: 250px; -fx-padding: 8px 10px; -fx-border-color: #9FCC2E; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-font-size: 14px;"); // Yellow Green border for text fields

        trackingButton = new Button("View My Tracking Details");
        trackingButton.setPrefWidth(250); 
        trackingButton.setOnAction(e -> trackingController());
        trackingButton.setStyle("-fx-background-color: #9FCC2E; -fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px; -fx-cursor: hand;"); // Yellow Green for buttons

        viewTrackingSection.getChildren().addAll(userIDLabel, userIDField, trackingButton);

        // Section for Cancelling Booking
        VBox cancelBookingSection = new VBox();
        cancelBookingSection.setSpacing(10);
        cancelBookingSection.setPadding(new Insets(15)); 
        cancelBookingSection.setStyle("-fx-background-color: #ffffff; -fx-border-color: #C8FFBE; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 5, 0, 0, 2);"); // Tea Green border

        Label tracIDToCancelLabel = new Label("Tracking ID to Cancel");
        tracIDToCancelLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #295135;"); // Car Poly Green for labels
        tracIDToCancelField = new TextField();
        tracIDToCancelField.setPromptText("Enter Tracking ID to cancel");
        tracIDToCancelField.setPrefWidth(250); 
        tracIDToCancelField.setStyle("-fx-pref-width: 250px; -fx-padding: 8px 10px; -fx-border-color: #9FCC2E; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-font-size: 14px;"); // Yellow Green border for text fields

        cancelButton = new Button("Cancel Booking");
        cancelButton.setPrefWidth(250); 
        cancelButton.setOnAction(e -> cancelBookingAction());
        cancelButton.setStyle("-fx-background-color: #9FCC2E; -fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px; -fx-cursor: hand;"); // Yellow Green for buttons
        
        cancelBookingSection.getChildren().addAll(tracIDToCancelLabel, tracIDToCancelField, cancelButton);
        
        resultText = new Text();
        resultText.setFont(new Font("Arial", 14));
        resultText.setStyle("-fx-fill: #295135; -fx-line-spacing: 5px; -fx-wrap-text: true;"); // Car Poly Green for result text
        
        ScrollPane scrollPane = new ScrollPane(resultText);
        scrollPane.setFitToWidth(true); 
        scrollPane.setPrefHeight(200); 
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); 
        scrollPane.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #C8FFBE; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5px;"); // Light background with Tea Green border

        leftVbox.getChildren().addAll(titleHeader, viewTrackingSection, cancelBookingSection, scrollPane); 

        mainHbox.getChildren().addAll(leftVbox);

        Scene scene = new Scene(mainHbox, 850, 600); 

        // Removed all getStyleClass().add() and scene.getStylesheets().add() as per user request

        return scene;
    }

    private void trackingController() {
        String userID = userIDField.getText().trim();

        if (userID.isEmpty()) {
            resultText.setText("Please enter your User ID.");
            return;
        }

        System.out.println("[DEBUG] Entered User ID: '" + userID + "' hmm");

        ArrayList<TrackDetail> userTrackDetails = trackingService.getTrackDetailsByUserID(userID);

        if (userTrackDetails != null && !userTrackDetails.isEmpty()) {
            StringBuilder sb = new StringBuilder("Tracking Details for User " + userID + ":\n\n");
            for (TrackDetail td : userTrackDetails) {
                sb.append("Tracking ID: ").append(td.getTracID()).append("\n");
                sb.append("  Booking Progress: ").append(td.getBookingProgress()).append("\n");
                sb.append("  Hajj Journey Status: ").append(td.getHajjJourneyStatus()).append("\n");
                sb.append("  Hajj Progress: ").append(td.getHajjProgress()).append("\n");
                sb.append("------------------------------------------\n");
            }
            resultText.setText(sb.toString());

        } else {
            resultText.setText("No tracking details found for User ID: " + userID + ".");
        }
    }

    private void cancelBookingAction() {
        String userID = userIDField.getText().trim();
        String tracIDToCancel = tracIDToCancelField.getText().trim();

        if (userID.isEmpty()) {
            resultText.setText("Please enter your User ID first.");
            return;
        }
        if (tracIDToCancel.isEmpty()) {
            resultText.setText("Please enter the Tracking ID you wish to cancel.");
            return;
        }

        TrackDetail detailToCancel = trackingService.getTrackDetail(tracIDToCancel);
        if (detailToCancel == null) {
            resultText.setText("Error: Tracking ID '" + tracIDToCancel + "' not found.");
            return;
        }
        
        if (!detailToCancel.getCurrUserID().equals(userID)) {
            resultText.setText("Error: Tracking ID '" + tracIDToCancel + "' does not belong to User ID '" + userID + "'.");
            return;
        }

        trackingService.cancelBooking(tracIDToCancel);
        resultText.setText("Booking for Tracking ID '" + tracIDToCancel + "' has been cancelled successfully!");
        
        trackingController();
    }

    public void display(Stage primaryStage) {
       
        primaryStage.setTitle("HAJJ AND UMRAH TRACKING SYSTEM");

        primaryStage.setScene(initializeScene());
        primaryStage.show();
    }
}
package org.wanderers;

import org.wanderers.RegistrationService;
import org.wanderers.BookingScene;
import org.wanderers.TrackingScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



public class UserDashboardScene {
    /*
     * UserDashboard Page 
     * - Page that will display overall of content for users for this system
     * - also include the page for user profile that include his detail
     * - Instance of this class are invoked by authentication or registration
     * - 
     */

    private Stage primaryStage;  

    private String userID; 
    private String name; 
    private String gender; 
    private String email; 
    private int noPhone;
    private int visaID; 
    private boolean medicalApprovalStatus;  
    private DataStorage store; 

    private Label visaLabel; 
    
    public UserDashboardScene() {

    }

    public UserDashboardScene(String userID, String name, String email, int noPhone, String gender, DataStorage storage) {
        this.userID = userID; 
        this.name = name; 
        this.email = email; 
        this.noPhone = noPhone; 
        this.gender = gender; 
        this.store = storage; 
    }

    public Scene initializeScene() {

        Label username = new Label(name); 
        Label emailLabel = new Label("E-MAIL ADDRESS: " + email); 
        Label noPhoneLabel = new Label("PHONE: " + Integer.toString(noPhone));
        visaLabel = new Label(); 
        Label medicalLabel = new Label(); 
        Label genderLabel = new Label("Gender: " + gender); 

        TextField visaField = new TextField(); 
        if(visaID == 0) {
            visaLabel.setText("VISA NUMBER NOT ENTERED");
            visaField.setPromptText("Enter your VISA ID");
        } else {
            visaLabel.setText("VISA NUMBER: " + visaID);
            visaField.setPromptText("Update your VISA ID");
        }

        Button updateVisa = new Button("Update VISA number");         
        updateVisa.setOnAction(e -> {
            updateVisaID(visaID);
        });

        HBox visaSection = new HBox();
        visaSection.setStyle("-fx-alignment: center;" +
                             "-fx-border-color: skyblue;" + 
                             "-fx-border-width: 5px;" + 
                             "-fx-padding: 5px;" + 
                             "-fx-border-radius: 5px;"); 

        visaSection.getChildren().addAll(visaLabel, visaField, updateVisa); 

        if(medicalApprovalStatus) {
            medicalLabel.setText("Medical Check-up Status: Approved");
            medicalLabel.setStyle("-fx-text-fill: blue;");
        } else {
            medicalLabel.setText("Medical Check-up Status: Not Approved");
            medicalLabel.setStyle("-fx-text-fill: red;");
        }

        VBox userProfile = new VBox(10); 
        userProfile.setStyle("-fx-alignment: top-center;" + 
                             "-fx-border-color: black;" + 
                             "-fx-border-width: 5px;" + 
                             "-fx-border-radius: 10px;" +
                             "-fx-border-insets: 10, 10, 10, 10;");      

        username.setStyle("-fx-font-size: 20px;" +
                          "-fx-font-weight: bold;");

        userProfile.getChildren().addAll(username, genderLabel, emailLabel, noPhoneLabel, visaSection, medicalLabel); 
        
        HBox mainHboxUser = new HBox(); 
        mainHboxUser.setStyle("-fx-alignment: top-center;");
        
        if(gender == "Male") {
            try {
                FileInputStream imageInput = new FileInputStream("hajj-umrah/src/main/resources/img/man.png"); 
                Image imgMan = new Image(imageInput); 
                ImageView displayImage = new ImageView(); 
                displayImage.setImage(imgMan);
                displayImage.setFitWidth(100);
                displayImage.setPreserveRatio(true);

                mainHboxUser.getChildren().addAll(userProfile, displayImage); 

            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } else {

            try {
                FileInputStream imageInput = new FileInputStream("hajj-umrah/src/main/resources/img/woman.png"); 
                Image imgWoman = new Image(imageInput); 
                ImageView displayImage = new ImageView(); 
                displayImage.setImage(imgWoman);
                displayImage.setFitWidth(100);
                displayImage.setPreserveRatio(true);

                mainHboxUser.getChildren().addAll(userProfile, displayImage); 
                
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }


        TabPane mainDashboard = new TabPane();
        String mainDashboardStyle = "-fx-tab-min-width: 50px;" + 
                                    "-fx-tab-min-height: 50px;" + 
                                    "-fx-background-color: #9FCC2E;" +
                                    "-fx-text-fill: white;" ;

        mainDashboard.setStyle(mainDashboardStyle);

        //Tab Creation
        Tab profileTab = new Tab(); 
        Tab bookingTab = new Tab(); 
        Tab trackingTab = new Tab(); 
        
        String tabStyle = "-fx-background-color: #CAFF8A;" + //Mindaro
                          "-fx-text-fill: white;" + 
                          "-fx-font-family: 'Courier';" + 
                          "-fx-font-weight: bold;" + 
                          "-fx-background-insets: 0, 0, 0, 50;";

        //BookingTab 
        VBox bookVbox = new VBox(10); 
        Button toBookingButton = new Button("To Booking Page."); 
        Label bookingLabel = new Label(); 

        bookingLabel.setText("Enter Booking Page:");

        toBookingButton.setOnAction(e -> {
            toBookingScene();
        });

        bookVbox.getChildren().addAll(bookingLabel, toBookingButton); 
        
        profileTab.setText("User Profile");
        profileTab.setContent(mainHboxUser);
        profileTab.setStyle(tabStyle);
        profileTab.setClosable(false);

        bookingTab.setText("Booking");
        bookingTab.setStyle(tabStyle);
        bookingTab.setClosable(false);
        bookingTab.setContent(bookVbox);

        Button trackingPageButton = new Button("TO TRACKING PAGE");
        trackingPageButton.setOnAction(e -> {
            toTrackingPage();
        });

        trackingTab.setText("Tracking");
        trackingTab.setStyle(tabStyle);
        trackingTab.setContent(trackingPageButton);
        trackingTab.setClosable(false);

        mainDashboard.getTabs().add(profileTab); 
        mainDashboard.getTabs().add(bookingTab);
        mainDashboard.getTabs().add(trackingTab);

        Scene dashboardScene = new Scene(mainDashboard, 500, 500);
        
        return dashboardScene; 
    }

    public void toTrackingPage() {
        TrackingScene track = new TrackingScene(store); 
        track.display(primaryStage);
    }
    
    public void updateVisaID(int VisaID) {
        //inject instance of user, from RegistrationService
        RegistrationService register = new RegistrationService(store); 

        register.findUserByID(userID).setVisaID(VisaID);
        visaLabel.setText("VISA NUMBER UPDATED SUCCESSFULLY: UserID: " + userID); 
        store.saveToFile();
        this.display(primaryStage);
    }

public void toBookingScene() {
    BookingScene booking = new BookingScene(store, primaryStage, userID, name, email, noPhone, gender);
    booking.start();
}

    public void display(Stage primaryStage) {
        this.primaryStage = primaryStage; 
        
        primaryStage.setTitle("USER DASHBOARD");

        //ICON are outsourced, subjected to attribution: <a href="https://www.flaticon.com/free-icons/kaaba" title="kaaba icons">Kaaba icons created by Muhamad Ulum - Flaticon</a>
        try {
            primaryStage.getIcons().add(new Image(new FileInputStream("hajj-umrah/src/main/resources/img/kaabaIcon.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        primaryStage.setMaximized(false);
        primaryStage.setScene(initializeScene());
        primaryStage.show();
    }

}

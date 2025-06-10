package org.wanderers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.wanderers.RegistrationService;
import org.wanderers.UserDashboardScene;
import org.wanderers.AuthenticationScene;

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


public class RegistrationScene {
/* 
* -  
*/   
    
    private Stage primaryStage; 
    private TextField nameField; 
    private TextField passwordField; 
    private TextField emailField; 
    private TextField phoneField;
    private RadioButton male; 
    private RadioButton female; 
    private Text resultText; 
    private DataStorage storage; 
    private RegistrationService registerUser;

    RegistrationScene(DataStorage storage) {
        this.storage = storage; 
        this.registerUser = new RegistrationService(this.storage); 
    }

    public Scene initializeScene() {

        //main, TODO: Set specification
        HBox mainHbox = new HBox();         

        Label titleHeader = new Label("HAJJ AND UMRAH MANAGEMENT SYSTEM REGISTRATION");  
        Label nameLabel = new Label("Name"); 
        Label passwordLabel = new Label("Password "); 
        Label emailLabel = new Label("Email "); 
        Label phoneLabel = new Label("Phone Number");

        //TODO: add label styles

        male = new RadioButton("Male"); 
        female = new RadioButton("Female"); 
        ToggleGroup genderGroup = new ToggleGroup(); 
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);
        HBox genderSelect = new HBox(10, male, female); 
        genderSelect.setStyle("-fx-background-color: #C8FFBE;" +
                              "-fx-border-radius: 10px;" + 
                              "-fx-border-width: 5px;");
        
        this.nameField = new TextField(); 
        this.passwordField = new TextField();
        this.emailField = new TextField(); 
        this.phoneField = new TextField(); 

        this.resultText = new Text();

        nameField.setPromptText("Enter your name");
        passwordField.setPromptText("Enter your password");
        emailField.setPromptText("Enter your email");
        phoneField.setPromptText("Enter your phone number (without '-')");

        //CSS Styling for labels
        
        titleHeader.setStyle("-fx-font-size: 30px;" + 
                             "-fx-text-fill: #C8FFBE;");
        titleHeader.setWrapText(true);

        String mainLabelStyle = "-fx-font-family: Courier;" + 
                                "-fx-text-fill: white;" + 
                                "-fx-background-color: black;";

        nameLabel.setStyle(mainLabelStyle);
        passwordLabel.setStyle(mainLabelStyle);
        emailLabel.setStyle(mainLabelStyle);
        phoneLabel.setStyle(mainLabelStyle);

        Button submitButton = new Button("REGISTER USER");
        
        //CSS styling submitButton 
        String submitButtonStyle = "-fx-background-color: green;" + 
                                   "-fx-transition-duration: 0.4s;"; 
        
        submitButton.setStyle(submitButtonStyle);
        
        //submitbutton dynamic properties
        submitButton.setOnAction(e -> registerController());
        submitButton.setOnMouseEntered(e -> {
            submitButton.setStyle("-fx-background-color: #9FCC2E;" + //Yellow Green
                                  "-fx-padding: 10px;" + 
                                  "-fx-background-insets: 10px;" + 
                                  "-fx-border-insets: 10px;" + 
                                  "-fx-border-color: #9FCC2E;" + 
                                  "-fx-border-radius: 5px;");
        });
        submitButton.setOnMouseExited(e -> {
            submitButton.setStyle(submitButtonStyle);
        });

        VBox mainVbox = new VBox(10);
        //the value of the vbox
        // mainVbox.setMargin(mainVbox, new Insets(10));

        String mainVboxStyle = "-fx-padding: 50px;" +
                               "-fx-background-color: #295135;" + //Car Poly Green
                               "-fx-font-family: \"Courier\";" + 
                               "-fx-text-align: center;";

        mainVbox.setStyle(mainVboxStyle);
       

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            goToLogin();
        });
        loginButton.setStyle(submitButtonStyle);

       //LOGIN REDIRECTION TODO:  
        mainVbox.getChildren().addAll(titleHeader, nameLabel, nameField, genderSelect, passwordLabel, passwordField, 
                                      emailLabel, emailField, phoneLabel, phoneField,
                                      submitButton,loginButton, resultText); 

        
        VBox altVbox = new VBox(10);
        
        try {
            FileInputStream imageInput = new FileInputStream("hajj-umrah/src/main/resources/img/kaaba.jpeg");
            Image img1 = new Image(imageInput);
            ImageView imgDisplay = new ImageView(); 
            //Using viewport for "overflow" effect
            Rectangle2D viewport = new Rectangle2D(0, 0, img1.getWidth()/2, img1.getHeight());
            imgDisplay.setImage(img1);;
            imgDisplay.setViewport(viewport);
            
            altVbox.getChildren().addAll(imgDisplay); 

        } catch (FileNotFoundException e) {
            System.out.println("Error Loading Image: " + e.getMessage());  
        }

        mainHbox.getChildren().addAll(altVbox, mainVbox);
        
        Scene registrationScene = new Scene(mainHbox, 500, 500);
       
        //Adding registration.css stylesheet to the scene. 
        // registrationScene.getStylesheets().add(this.getClass().getResource().toExternalForm());  
        
        return registrationScene; 
    }    

    public void registerController() {
        /*
        registerController() method
        - this method will handle all user input and verify the input to the according to business rule.
        - 
        */ 
        String name = nameField.getText();
        String gender = male.isSelected() ? "Male":"Female";
        String password = passwordField.getText(); 
        String email = emailField.getText();
        int noPhone = Integer.parseInt(phoneField.getText());
        //TODO: define error checking for noPhone field 
        
        String resultTextStyle = "-fx-text-fill: white;" + 
                                 "-fx-background-color: #EFF8E2";  //HoneyDew
        resultText.setStyle(resultTextStyle);
        //Debug
        resultText.setText("[DATA]: " + name + ", " + password + ", " + email + ", " + noPhone);

        if(name == null || password == null || email == null || noPhone == 0) {
            resultText.setText("Please fill in all the details."); 
        }


        String statusMessage = registerUser.createAccount(name, password, noPhone, email, gender); 
        resultText.setText(statusMessage);

        if(statusMessage == "Account Creation Successful, Please Login") {
            UserDashboardScene userDashboard = new UserDashboardScene(registerUser.getGeneratedUserID(), name, email, noPhone, gender, storage);
            userDashboard.display(primaryStage);
        }
    }

    public void goToLogin() {
        AuthenticationScene login = new AuthenticationScene(storage, registerUser); 
        login.display(primaryStage);
    }

    public void display(Stage primaryStage) {
        this.primaryStage = primaryStage;

        primaryStage.setTitle("USER REGISTRATION PAGE"); 

        //ICON are outsourced, subjected to attribution: <a href="https://www.flaticon.com/free-icons/kaaba" title="kaaba icons">Kaaba icons created by Muhamad Ulum - Flaticon</a>
        try {
            primaryStage.getIcons().add(new Image(new FileInputStream("hajj-umrah/src/main/resources/img/kaabaIcon.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        primaryStage.setScene(initializeScene());
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}

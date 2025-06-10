package org.wanderers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField; // Recommended for password input
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.wanderers.RegistrationService;

public class AuthenticationScene {
    
    private TextField userIDField;
    private PasswordField passwordField; // Use PasswordField for secure password input
    private Button loginButton;
    private Text resultText;

    private DataStorage storage;
    private Authentication authentication;
    private RegistrationService register; 
    private Stage primaryStage; 

    /**
     * Constructor for AuthenticationScene.
     * Initializes DataStorage and AuthenticationService.
     * @param storage The DataStorage instance to use for authentication.
     */
    public AuthenticationScene(DataStorage store, RegistrationService register) {
        this.storage = store; 
        this.register = register; 
        this.authentication = new Authentication(store); 
    }
    public AuthenticationScene(DataStorage storage) {
        this.storage = storage;
        this.authentication = new Authentication(storage);
    }

    /**
     * Initializes and builds the JavaFX Scene for the authentication interface.
     * @return The constructed Scene object.
     */
    public Scene initializeScene() {
        // Main HBox for overall layout
        HBox mainHbox = new HBox();
        mainHbox.setPadding(new Insets(20));
        mainHbox.setSpacing(40);
        mainHbox.setAlignment(Pos.CENTER);
        mainHbox.setStyle("-fx-background-color: #C8FFBE;"); // Tea Green background

        // VBox for login elements
        VBox loginVbox = new VBox();
        loginVbox.setPadding(new Insets(20));
        loginVbox.setSpacing(25);
        loginVbox.setAlignment(Pos.TOP_LEFT);
        loginVbox.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 5);");

        // Title Header
        Label titleHeader = new Label("USER AUTHENTICATION");
        titleHeader.setFont(new Font("Arial", 22));
        titleHeader.setStyle("-fx-font-weight: bold; -fx-text-fill: #295135; -fx-padding: 0 0 10px 0; -fx-border-color: #ecf0f1; -fx-border-width: 0 0 1px 0;");

        // User ID input section
        Label userIDLabel = new Label("User ID");
        userIDLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #295135;");
        userIDField = new TextField();
        userIDField.setPromptText("Enter your User ID (e.g., U.1)");
        userIDField.setPrefWidth(250);
        userIDField.setStyle("-fx-pref-width: 250px; -fx-padding: 8px 10px; -fx-border-color: #9FCC2E; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-font-size: 14px;");

        // Password input section
        Label passwordLabel = new Label("Password");
        passwordLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #295135;");
        passwordField = new PasswordField(); // Using PasswordField for security
        passwordField.setPromptText("Enter your Password");
        passwordField.setPrefWidth(250);
        passwordField.setStyle("-fx-pref-width: 250px; -fx-padding: 8px 10px; -fx-border-color: #9FCC2E; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-font-size: 14px;");

        // Login Button
        loginButton = new Button("Login");
        loginButton.setPrefWidth(250);
        loginButton.setOnAction(e -> handleLoginAction()); // Set action for login button
        loginButton.setStyle("-fx-background-color: #9FCC2E; -fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px; -fx-cursor: hand;");

        // Result Text area to display login messages
        resultText = new Text();
        resultText.setFont(new Font("Arial", 14));
        resultText.setStyle("-fx-fill: #295135; -fx-line-spacing: 5px; -fx-wrap-text: true; -fx-text-alignment: center;");
        
        // Add all components to the login VBox
        loginVbox.getChildren().addAll(titleHeader, userIDLabel, userIDField, passwordLabel, passwordField, loginButton, resultText);

        // Add the login VBox to the main HBox
        mainHbox.getChildren().addAll(loginVbox);

        // Create the scene with the main HBox
        Scene scene = new Scene(mainHbox, 400, 450); // Adjust scene size as needed

        return scene;
    }

    /**
     * Handles the login button action. Authenticates the user and updates the UI.
     */
    private void handleLoginAction() {
        String userID = userIDField.getText().trim();
        String password = passwordField.getText().trim();

        // Basic input validation
        if (userID.isEmpty() || password.isEmpty()) {
            resultText.setText("Please enter both User ID and Password.");
            resultText.setStyle("-fx-fill: red;"); // Indicate error
            return;
        }

        // Attempt to authenticate the user
        User authenticatedUser = authentication.authenticateUser(userID, password);

        if (authenticatedUser != null) {
            // Login successful
            resultText.setText("Login successful! Welcome, " + authenticatedUser.getName() + "!");
            resultText.setStyle("-fx-fill: green;"); // Indicate success
            // In a real application, you would now transition to the main application scene.
            System.out.println("User " + authenticatedUser.getUserID() + " logged in successfully.");

            String name = register.findUserByID(userID).name; 
            String email = register.findUserByID(userID).email; 
            int noPhone = register.findUserByID(userID).noPhone; 
            String gender = register.findUserByID(userID).gender; 
            
            UserDashboardScene userDashboard = new UserDashboardScene(register.getGeneratedUserID(), name, email, noPhone, gender, storage);
            userDashboard.display(primaryStage);
            // Example: Clear fields after successful login
            userIDField.clear();
            passwordField.clear();

            // You could uncomment the line below to immediately transition to the TrackingScene
            // if you have access to the primary stage here.
            // new TrackingScene(storage).display((Stage) loginButton.getScene().getWindow());
        } else {
            // Login failed
            resultText.setText("Login failed. Invalid User ID or Password.");
            resultText.setStyle("-fx-fill: red;"); // Indicate error
            System.out.println("Login attempt failed for User ID: " + userID);
        }
    }

    /**
     * Displays the authentication scene on the given primary stage.
     * @param primaryStage The primary Stage of the JavaFX application.
     */
    public void display(Stage primaryStage) {
        this.primaryStage = primaryStage; 
        primaryStage.setTitle("User Authentication"); // Set window title
        primaryStage.setScene(initializeScene()); // Set the scene
        primaryStage.show(); // Show the stage
    }

}

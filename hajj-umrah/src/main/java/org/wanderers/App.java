package org.wanderers;

import javafx.application.Application;
import javafx.stage.Stage;

<<<<<<< HEAD
import org.wanderers.AuthenticationScene;
=======
import org.wanderers.RegistrationScene;
>>>>>>> 2f35afc741aabe6bddf2795e06a9cfbcdf2e9903
import org.wanderers.DataStorage;
/**
 * JavaFX App
 */
public class App extends Application {

<<<<<<< HEAD
    @Override
    public void start(Stage stage) {
        
        DataStorage store = new DataStorage(); 
        
        AuthenticationScene register = new AuthenticationScene(store); 
        register.display(stage);
=======
    private Stage mainStage; 

    public Stage getMainStage() {
        return mainStage; 
    }

    @Override
    public void start(Stage primaryStage) {
        /*
         * The program will either start with user registration or user login
         */
        DataStorage store = new DataStorage();
        
        RegistrationScene register = new RegistrationScene(store); 
        register.display(primaryStage);

>>>>>>> 2f35afc741aabe6bddf2795e06a9cfbcdf2e9903
    }

    public static void main(String[] args) {
        launch();
    }
}

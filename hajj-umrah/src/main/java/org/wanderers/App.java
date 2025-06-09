package org.wanderers;

import javafx.application.Application;
import javafx.stage.Stage;

import org.wanderers.RegistrationScene;
import org.wanderers.DataStorage;
/**
 * JavaFX App
 */
public class App extends Application {

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

    }

    public static void main(String[] args) {
        launch();
    }
}

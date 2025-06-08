package org.wanderers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.wanderers.RegistrationScene;
import org.wanderers.DataStorage;
/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        
        DataStorage store = new DataStorage(); 
        
        RegistrationScene register = new RegistrationScene(store); 
        register.display(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}
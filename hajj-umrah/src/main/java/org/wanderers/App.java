package org.wanderers;

import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
    
    @Override
    public void start(Stage bookingStage) {
        
        BookingScene bookingScene = new BookingScene();
        bookingScene.start(bookingStage);
        
 
        
    }

    public static void main(String[] args) {
        launch();
    }

}
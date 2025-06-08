package org.wanderers;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        DataStorage store = new DataStorage();
        BookingScene booking = new BookingScene(store);
        booking.start(primaryStage);

    }

    public static void main(String[] args) {
        launch();
    }
}

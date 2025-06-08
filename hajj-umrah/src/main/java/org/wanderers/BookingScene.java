package org.wanderers;

import org.wanderers.RegistrationService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.FileInputStream;
import java.util.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.time.LocalDate;



public class BookingScene {

    private TextField bookIDField;
    private TextField userIDField;
    private ComboBox<String> packageName;
    private DatePicker bookingDateField;
    private Text resultText;

    public void start(Stage bookingStage) {

        // Create the Gridpane to Hold The elements
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setStyle("-fx-background-color: #C8FFBE  ;");
        grid.setVgap(15);
        grid.setHgap(15);

        // Creating the labels and the input field
        Label bookIDLabel = new Label("Book ID: ");
        bookIDField = new TextField();
        bookIDField.setPromptText("Enter A Booking ID");

        Label userIDLabel = new Label("User ID : ");
        userIDField = new TextField();
        userIDField.setPromptText("Enter Your User ID");

        // Combo Box For Booking Package
        Label packageLabel = new Label("Package Name : ");
        packageName = new ComboBox<>();
        packageName.getItems().addAll("Standard (RM 8900)", "Premium (RM 10800)", "Super Deluxe (RM 14500");
        packageName.setValue("Choose A Package:");

        // Date Picker
        Label bookingDateLabel = new Label("Pick A Date To Book :");
        bookingDateField = new DatePicker();

        // Button to finalize booking
        Button bookingButton = new Button(" Book A Spot! ");

        grid.add(bookIDLabel, 0, 0);
        grid.add(bookIDField, 1, 0);
        grid.add(userIDLabel, 0, 1);
        grid.add(userIDField, 1, 1);
        grid.add(packageLabel, 0, 2);
        grid.add(packageName, 1, 2);
        grid.add(bookingDateLabel, 0, 3);
        grid.add(bookingDateField, 1, 3);
        grid.add(bookingButton, 1, 4);

        // VBox to hold the grid
        VBox vbox = new VBox(grid);
        vbox.setStyle("-fx-background-color: #9fcc2e");
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 600, 400);
        // set the title
        bookingStage.setTitle("Booking Page For Hajj Umrah");
        // set the scene
        bookingStage.setScene(scene);
        bookingStage.show();
    }

}

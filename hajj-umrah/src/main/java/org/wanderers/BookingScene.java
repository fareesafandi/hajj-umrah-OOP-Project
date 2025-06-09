package org.wanderers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
import javafx.geometry.Rectangle2D;

public class BookingScene {

    private TextField bookIDField;
    private TextField userIDField;
    private ComboBox<String> packageName;
    private DatePicker bookingDateField;
    private Text resultText;
    private DataStorage storage;
    private Stage bookingStage;
    private String userID;
    private String name;
    private String email;
    private int noPhone;
    private String gender;

 public BookingScene(DataStorage storage, Stage bookingStage, String userID, String name, String email, int noPhone, String gender) {
    this.storage = storage;
    this.bookingStage = bookingStage;
    this.userID = userID;
    this.name = name;
    this.email = email;
    this.noPhone = noPhone;
    this.gender = gender;
}

    public void toUserDashboardScene() {
        UserDashboardScene dashboard = new UserDashboardScene(userID, name, email, noPhone, gender, storage);
        dashboard.display(bookingStage);  // Switch scene in the current stage
    }

    public void start() {

        HBox imageBox = new HBox(10);
        imageBox.setPadding(new Insets(20));
        imageBox.setAlignment(Pos.CENTER);

        try {
            String imageInput = "https://esislam.com/wp-content/uploads/2020/04/Kaaba.jpg";
            Image img1 = new Image(imageInput);

            ImageView imgDisplay = new ImageView(img1);
            imgDisplay.setFitHeight(200);
            imgDisplay.setFitWidth(600);

            imgDisplay.setPreserveRatio(true);
            imgDisplay.setSmooth(true);

            //Using viewport for "overflow" effect
            Rectangle2D viewport = new Rectangle2D(0, 0, img1.getWidth() / 2, img1.getHeight());
            imgDisplay.setImage(img1);
            imgDisplay.setViewport(viewport);
            imageBox.getChildren().addAll(imgDisplay);

        } catch (Exception e) {
            System.out.println("Error Loading Image: " + e.getMessage());
        }

        HBox hbox = new HBox();
        Label titleHeader = new Label("HAJJ AND UMRAH MANAGEMENT BOOKING SYSTEM");
        titleHeader.setStyle("-fx-font-size: 30px;"
                + "-fx-text-fill: #C8FFBE;"
                + "-fx-text-align: center;"
                + "-fx-background-color:#295135;"
                + "-fx-font-weight: bold;");
        titleHeader.setWrapText(true);
        hbox.getChildren().addAll(titleHeader);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20, 0, 20, 0));

        // Create the Gridpane to Hold The elements
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setStyle("-fx-background-color: #9fcc2e  ; -fx-text-align: center;");
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
        Button bookingButton = new Button(" Book A Spot ");

        //CSS styling submitButton 
        String bookingButtonStyle = "-fx-background-color: white;"
                + "-fx-transition-duration: 0.4s;"
                + "fx-font-weight:bold;";

        bookingButton.setStyle(bookingButtonStyle);
        //booking tbutton dynamic properties
        bookingButton.setOnAction(e -> bookingRegisterController());
        bookingButton.setOnMouseEntered(e -> {
            bookingButton.setStyle("-fx-background-color: #ffce09;"
                    + //Yellow Green
                    "-fx-padding: 10px;"
                    + "-fx-background-insets: 10px;"
                    + "-fx-border-insets: 10px;"
                    + "-fx-border-color: #ffce09;"
                    + "-fx-border-radius: 5px;"
                    + "-fx-text-fill: #000000;"
                    + "fx-font-weight:bold;");
        });
        bookingButton.setOnMouseExited(e -> {
            bookingButton.setStyle(bookingButtonStyle);
        });

        Button returnButton = new Button("Return");
        returnButton.setStyle(bookingButtonStyle);
        returnButton.setOnAction(e -> toUserDashboardScene());

        String mainLabelStyle = "-fx-font-family: Courier;"
                + "-fx-text-fill: white;"
                + "-fx-background-color: black;"
                + " -fx-font-size: 15px;"
                + "-fx-font-weight: bold;"
                + "-fx-padding: 5px;"
                + "-fx-background-radius: 5px;";

        bookIDLabel.setStyle(mainLabelStyle);
        userIDLabel.setStyle(mainLabelStyle);
        packageLabel.setStyle(mainLabelStyle);
        bookingDateLabel.setStyle(mainLabelStyle);

        String submitButtonStyle = "-fx-background-color: #ffffff;"
                + "-fx-transition-duration: 0.4s;"
                + "-fx-font-weight: bold;";

        bookingButton.setStyle(submitButtonStyle);
        returnButton.setStyle(submitButtonStyle);

        grid.add(bookIDLabel, 0, 0);
        grid.add(bookIDField, 1, 0);
        grid.add(userIDLabel, 0, 1);
        grid.add(userIDField, 1, 1);
        grid.add(packageLabel, 0, 2);
        grid.add(packageName, 1, 2);
        grid.add(bookingDateLabel, 0, 3);
        grid.add(bookingDateField, 1, 3);
        grid.add(bookingButton, 1, 4);
        grid.add(returnButton, 1, 5);
        grid.setAlignment(Pos.CENTER);

        // VBox to hold the grid
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: #295135; -fx-text-align: center;");
        vbox.setAlignment(Pos.CENTER);

        // Add title and grid to VBox
        vbox.getChildren().addAll(imageBox, hbox, grid);

        Scene scene = new Scene(vbox, 800, 600);
        // set the title
        bookingStage.setTitle("BOOKING PAGE");

        //ICON are outsourced, subjected to attribution: <a href="https://www.flaticon.com/free-icons/kaaba" title="kaaba icons">Kaaba icons created by Muhamad Ulum - Flaticon</a>
        try {
            bookingStage.getIcons().add(new Image(new FileInputStream("C:\\Users\\User\\Desktop\\IIUM\\OOP Project Hajj And Umrah\\hajj-umrah-OOP-Project\\hajj-umrah\\src\\main\\java\\images\\landmark.PNG")));
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // set the scene
        bookingStage.setScene(scene);
        bookingStage.show();

        resultText = new Text();
        resultText.setStyle("-fx-font-size: 14px; -fx-fill: white; -fx-padding: 10px;");
        vbox.getChildren().add(resultText);
    }

    public void bookingRegisterController() {

        String bookID = bookIDField.getText();
        String userID = userIDField.getText();
        String selectedPackage = packageName.getValue();
        LocalDate bookingDate = bookingDateField.getValue();

        // input validation
        if (bookID.isEmpty() || userID.isEmpty() || selectedPackage.equals("Choose A Package:") || bookingDate == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields!");
            alert.showAndWait();
            return;
        }

        BookingService registerBooking = new BookingService(this.storage);
        String statusMessage = registerBooking.createBookingDetail(bookID, userID, selectedPackage, bookingDate);
        resultText.setText(statusMessage);

        // Confirmation message
        Alert success = new Alert(AlertType.INFORMATION);
        success.setTitle("Booking Successful");
        success.setHeaderText(null);
        success.setContentText("Booking for " + userID + " has been made successfully!");
        success.showAndWait();

        // Optionally clear fields after successful booking
        bookIDField.clear();
        userIDField.clear();
        packageName.setValue("Choose A Package:");
        bookingDateField.setValue(null);
    }


}

package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class Main extends Application {

    private BorderPane bp = new BorderPane();
    private GridPane gp = new GridPane();

    private Label usernameLabel = new Label();
    private Label passLabel = new Label();
    private Label fullNameLabel = new Label();
    private Label emailLabel = new Label();
    private Label phoneLabel = new Label();
    private Label dateLabel = new Label();

    private TextField usernameField = new TextField();
    private TextField passField = new TextField();
    private TextField fullNameField = new TextField();
    private TextField emailField = new TextField();
    private TextField phoneField = new TextField();

    private DatePicker dateField = new DatePicker();

    private Button registerButton = new Button();

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Setting grid settings and window size
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setPrefSize(500, 300);

        // Setting text for labels and fields
        usernameLabel.setText("Username:");
        usernameField.setPromptText("Username");

        passLabel.setText("Password:");
        passField.setPromptText("Password");

        fullNameLabel.setText("Full Name:");
        fullNameField.setPromptText("Full Name");

        emailLabel.setText("E-Mail:");
        emailField.setPromptText("E-Mail");

        phoneLabel.setText("Phone #:");
        phoneField.setPromptText("Phone #");

        dateLabel.setText("Date of Birth:");
        dateField.setPromptText("MM/DD/YYYY");

        registerButton.setText("Register");

        // Set gaps between items
        gp.setHgap(15);
        gp.setVgap(10);

        // Adding items to the grid
        gp.add(usernameLabel, 0, 0);
        gp.add(usernameField, 2, 0);

        gp.add(passLabel, 0, 1);
        gp.add(passField, 2, 1);

        gp.add(fullNameLabel, 0, 2);
        gp.add(fullNameField, 2, 2);

        gp.add(emailLabel, 0, 3);
        gp.add(emailField, 2, 3);

        gp.add(phoneLabel, 0, 4);
        gp.add(phoneField, 2, 4);

        gp.add(dateLabel, 0, 5);
        gp.add(dateField, 2, 5);

        gp.add(registerButton, 2, 6);

        // Showing window
        bp.setCenter(gp);
        Scene sceneRegister = new Scene(bp);
        primaryStage.setTitle("lab 04");
        primaryStage.setScene(sceneRegister);
        primaryStage.show();

        // Simple button action
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                register(e);
            }
        });
    }

    // Function to print name, email, phone #, and date of birth to console
    private void register(ActionEvent e) {
        // if phone number not in the form ###-###-#### print null for its value
        if (!phoneField.getText().matches("\\d{3}-\\d{3}-\\d{4}")) {
            emailField.clear();
            System.out.println("Hello " + fullNameField.getText() + "\nEmail: " + emailField.getText() + "\nPhone #: "
                    + null + "\nDate of Birth: " + dateField.getValue());
            fullNameField.clear();
            emailField.clear();
            phoneField.clear();
        }

        // else phone number is in valid format
        else {
            System.out.println("Hello " + fullNameField.getText() + "\nEmail: " + emailField.getText() + "\nPhone #: "
                    + phoneField.getText() + "\nDate of Birth: " + dateField.getValue());
            fullNameField.clear();
            emailField.clear();
            phoneField.clear();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
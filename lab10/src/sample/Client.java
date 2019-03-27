package sample;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Client extends Application {
    // IO stream
    DataOutputStream toServer;

    @Override
    public void start(Stage primaryStage) {
        // Text field for username and the message
        TextField tfUsername= new TextField();
        TextField tfMessage = new TextField();

        tfUsername.setPrefColumnCount(12);
        tfMessage.setPrefColumnCount(12);

        // Button to send message to server & button to exit
        Button btSend= new Button("Send");
        Button btExit = new Button("Exit");

        // Grid pane to hold the label and text field
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(30, 30, 30, 30));
        gp.setVgap(10);
        gp.setHgap(10);

        Label usernameLabel = new Label("Username:");
        Label messageLabel = new Label("Message:");

        gp.add(usernameLabel, 0, 0);
        gp.add(messageLabel, 0, 1);
        gp.add(tfUsername, 1, 0);
        gp.add(tfMessage, 1, 1);
        gp.add(btSend, 0, 2);
        gp.add(btExit, 0, 3);

        // Create a scene and place it in the stage
        Scene scene = new Scene(gp, 300, 200);
        primaryStage.setTitle("SimpleBBS Client v1.0"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        // Handler event for submit button
        btSend.setOnAction(e -> {
            try {
                // Get the username from the text field
                String username = tfUsername.getText().trim();

                // Get the message from the text field
                String message = tfMessage.getText().trim();

                // Send the username to the server
                toServer.writeUTF(username + ": " + message);

                toServer.flush();
            }
            catch (IOException ex) {
                System.err.println(ex);
            }
        });

        // handler event for exit button
        btExit.setOnAction(e -> {
            System.exit(0);
        });

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);

            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());


        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

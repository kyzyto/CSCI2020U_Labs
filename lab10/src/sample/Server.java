package sample;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Server extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Text area for displaying contents
        TextArea ta = new TextArea();

        // Button to exit
        Button btExit= new Button("Exit");

        // Gridpane to hold text area and exit button
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(30, 30, 30, 30));
        gp.setVgap(10);

        gp.add(ta,0,0);
        gp.add(btExit,0,1);

        // Create a scene and place it in the stage
        Scene scene = new Scene(gp, 350, 300);
        primaryStage.setTitle("SimpleBBS Server v1.0"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        new Thread( () -> {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(8000);

                // Listen for a connection request
                Socket socket = serverSocket.accept();

                // Create data input stream
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());

                while (true) {
                    // Receive text from the client
                    String text = inputFromClient.readUTF();

                    Platform.runLater(() -> {
                        ta.appendText(text + "\n");
                    });
                }
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }).start();

        // handler event for exit button
        btExit.setOnAction(e -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
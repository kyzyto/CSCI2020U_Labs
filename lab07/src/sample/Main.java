package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;


public class Main extends Application {
    // array for the colours of the pie chart
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    // array for the weather warnings
    private static String[] warnings = {
            "FLASH FLOOD", "SEVERE THUNDERSTORM",
            "SPECIAL MARINE", "TORNADO"
    };

    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root);

        canvas = new Canvas(800, 500);

        root.getChildren().add(canvas);

        primaryStage.setTitle("Lab07 Solution");
        primaryStage.setScene(scene);
        primaryStage.show();
        // calling to display the charts
        drawPieChart();
    }

    public void drawPieChart() {
        GraphicsContext g = canvas.getGraphicsContext2D();

        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        WeatherWarningData w = new WeatherWarningData();
        w.readData("weatherwarnings-2015.csv", ",");

        Integer[] warningValues = w.getData();

        double startAng = 0;
        double heightOffset = 100;
        double currLen;
        double sum = 0;

        for (int i = 0; i < 4; i++) {
            sum += warningValues[i];
        }

        for (int i = 0; i < 4; i++) {
            currLen = (warningValues[i] / sum) * 360;

            // creating legend
            g.setFill(pieColours[i]);
            g.strokeRect(50, heightOffset, 50, 25);
            g.fillRect(50, heightOffset, 50, 25);

            // creating pie chart
            g.fillArc(350, 50, 400, 400, startAng, currLen, ArcType.ROUND);
            g.strokeArc(350,50,400, 400, startAng, currLen, ArcType.ROUND);

            // setting warning texts to black and positioning them
            g.setFill(Color.BLACK);
            g.fillText(warnings[i], 105, heightOffset + 15);

            startAng += currLen;
            heightOffset += 50;
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}

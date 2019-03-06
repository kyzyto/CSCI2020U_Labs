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
    // array for the average housing prices per year
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    // array for the average commercial prices per year
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };
    // array for the age groups
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    // array for the purchases by age group
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    // array for the colours of the pie chart
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    private Canvas canvas;

    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root);

        canvas = new Canvas(800, 500);

        root.getChildren().add(canvas);

        primaryStage.setTitle("Lab06 Solution");
        primaryStage.setScene(scene);
        primaryStage.show();
        // calling to display the charts
        drawCharts();
    }

    private void drawCharts() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        double numValue = 0;
        int x = 0;

        for(int i = 0; i < avgHousingPricesByYear.length; i++){
            //RED BAR - housing prices by year
            numValue = avgHousingPricesByYear[i];
            numValue = (50 + (numValue - avgHousingPricesByYear[0])*((canvas.getHeight() - 50) - 50))
                    /(avgCommercialPricesByYear[avgCommercialPricesByYear.length - 1] - avgHousingPricesByYear[0]);

            g.setFill(Color.RED);
            g.fillRect(x, canvas.getHeight() - numValue - 50, 20, numValue);
            x += 20; // spacing to place red and blue bars beside one another

            //BLUE BAR - commercial prices by year
            numValue = avgCommercialPricesByYear[i];
            numValue = (50 + (numValue - avgCommercialPricesByYear[0])*((canvas.getHeight() - 50) - 50))
                    /(avgCommercialPricesByYear[avgCommercialPricesByYear.length - 1] - avgCommercialPricesByYear[0]);

            g.setFill(Color.BLUE);
            g.fillRect(x, canvas.getHeight() - numValue - 50, 20, numValue);
            x += 30; // blank spacing for the next set of red and blue bars
        }

        x += 50; // after the last blue, bar add spacing of 50 for the pie chart


        // pie chart
        double total = 0;
        for(int i = 0; i < purchasesByAgeGroup.length; i++){
            // getting total for of purchases by age group
            total += purchasesByAgeGroup[i];
        }

        double startAngle = 0;
        for(int i = 0; i < purchasesByAgeGroup.length; i++){
            // converting purchases by age group to proportion of a pie
            numValue = (purchasesByAgeGroup[i] / total) * 360;
            g.setFill(pieColours[i]);
            g.fillArc(x, canvas.getHeight()/2 - (300/2), 300, 300, startAngle, numValue, ArcType.ROUND);
            startAngle += numValue;
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

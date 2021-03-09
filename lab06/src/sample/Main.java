package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Main extends Application {
    private Canvas canvas = new Canvas();

    private static double[] avgHousingPricesByYear = { 247381.0,264171.4,287715.3,294736.1, 308431.4,322635.9,340253.0,363153.7};
    private static double[] avgCommercialPricesByYear = { 1121585.3,1219479.5,1246354.2,1295364.8, 1335932.6,1472362.0,1583521.9,1613246.3};

    private static String[] ageGroups = { "18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private static int[] purchasesByAgeGroup = {648, 1021, 2453, 3173, 1868, 2247};
    private int arraySum = 0;
    private double angle = 0;
    private double angleSum = 0;
    private static Color[] pieColours = { Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 900, 600);

        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        root.getChildren().add(canvas);

        primaryStage.setTitle("Graphics - Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();

        draw(root);
    }

    private void draw(Group root) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (int i = 0; i < avgCommercialPricesByYear.length; i++){
            gc.setFill(Color.RED);
            gc.fillRect(50 + (45*i) , 550 - avgHousingPricesByYear[i]/5000, 20, avgHousingPricesByYear[i]/5000);
            gc.setFill(Color.BLUE);
            gc.fillRect(70 + (45*i) , 550 - avgCommercialPricesByYear[i]/5000, 20, avgCommercialPricesByYear[i]/5000);
        }

        for (int i = 0; i < purchasesByAgeGroup.length; i++){
            arraySum += purchasesByAgeGroup[i];
        }

        for (int i = 0; i < purchasesByAgeGroup.length; i++){
            gc.setFill(pieColours[i]);
            angle = purchasesByAgeGroup[i] * 360 / arraySum;
            gc.fillArc(500, 200, 350, 350, angleSum, angle, ArcType.ROUND);
            angleSum += angle;
        }

        /*gc.fillArc(500, 500, 100, 75, 45, 115, ArcType.ROUND);
        gc.fillArc(500, 200, 100, 75, 0, 115, ArcType.ROUND);
        gc.setFill(Color.GREEN);
        gc.fillArc(500, 200, 100, 75, 115.6565, 115, ArcType.ROUND);*/
    }


    public static void main(String[] args) {
        launch(args);
    }
}

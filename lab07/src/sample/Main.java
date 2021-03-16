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
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main extends Application {
    private Map<String, Integer> wordCounts;
    private Canvas canvas = new Canvas();
    private static Color[] pieColours = { Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    public Main(){
        wordCounts = new TreeMap<>();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 900, 600);

        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        root.getChildren().add(canvas);

        primaryStage.setTitle("Lab 07");
        primaryStage.setScene(scene);
        primaryStage.show();

        draw(root);
    }

    public void draw(Group root) {
        //file manipultion
        try{
            FileReader fileInput = new FileReader("weatherwarnings-2015.csv");
            BufferedReader input = new BufferedReader(fileInput);

            //Count number of warnings
            String line;
            while((line = input.readLine()) != null){
                String[] data = line.split(",");
                countWord(data[5]);
            }

            Set<String> keys = wordCounts.keySet();
            Iterator<String> keyIterator = keys.iterator();

            int totalCount = 0;
            String[] keyArray = new String[wordCounts.keySet().size()];
            int[] countArray = new int[wordCounts.keySet().size()];
            double angle = 0;
            double angleSum = 0;
            int index = 0;

            //Iterate through warnings and store in array
            while(keyIterator.hasNext()){
                String key = keyIterator.next();
                int count = wordCounts.get(key);

                totalCount += count;
                keyArray[index] = key;
                countArray[index] = count;
                index++;
            }
            input.close();

            GraphicsContext gc = canvas.getGraphicsContext2D();

            //Draw Pie Chart
            for (int i = 0; i < countArray.length; i++){
                gc.setFill(pieColours[i]);
                angle = countArray[i] * 360 / totalCount;
                gc.fillArc(500, 100, 350, 350, angleSum, angle, ArcType.ROUND);
                gc.setFill(Color.BLACK);
                gc.strokeArc(500, 100, 350, 350, angleSum, angle, ArcType.ROUND);
                angleSum += angle;
            }

            //Draw legend
            gc.setFont(new Font("Arial", 18));
            for (int i = 0; i < keyArray.length; i++) {
                gc.setFill(pieColours[i]);
                gc.fillRect(100, 100*(i+1), 100, 75);
                gc.setFill(Color.BLACK);
                gc.strokeRect(100, 100*(i+1), 100, 75);
                gc.fillText(keyArray[i], 230, 100*i+140);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Sort words in respective categories
    private void countWord(String word){
        if(wordCounts.containsKey(word)){
            int previous = wordCounts.get(word);
            wordCounts.put(word, previous+1);
        }
        else{
            wordCounts.put(word, 1);
        }
    }

    public static void main(String[] args) { launch(args); }
}

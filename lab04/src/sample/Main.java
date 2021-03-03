package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 04 Solution");

        //Creating layout gridpane
        GridPane myGrid = new GridPane();
        myGrid.setAlignment(Pos.TOP_LEFT);
        myGrid.setHgap(10);
        myGrid.setVgap(10);
        myGrid.setPadding(new Insets(25, 25, 25 ,25));

        // -- labels
        Label lbUserName = new Label("Username:");
        Label lbPassword = new Label("Password:");
        Label lbFullName = new Label("Full Name:");
        Label lbEMail = new Label("E-Mail:");
        Label lbPhone = new Label("Phone #:");
        Label lbDOB = new Label("Date of Birth:");

        // -- inputs
        final TextField txUserName = new TextField();
        txUserName.setPromptText("Username");
        PasswordField psPassword = new PasswordField();
        psPassword.setPromptText("Password");
        final TextField txFullName = new TextField();
        txFullName.setPromptText("Full Name");
        final TextField txEMail = new TextField();
        txEMail.setPromptText("E-Mail");
        final TextField txPhone = new TextField();
        txPhone.setPromptText("Phone #");
        final DatePicker dpDOB = new DatePicker();
        dpDOB.setPromptText("Date of Birth");

        //--Button
        Button btn = new Button("Register");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(btn);

        //--Link
        final Text actiontarget = new Text();

        //Add the components onto the myGrid pane
        myGrid.add(lbUserName, 0, 1);
        myGrid.add(lbPassword, 0, 2);
        myGrid.add(lbFullName, 0, 3);
        myGrid.add(lbEMail, 0, 4);
        myGrid.add(lbPhone, 0, 5);
        myGrid.add(lbDOB, 0, 6);
        myGrid.add(txUserName, 1, 1);
        myGrid.add(psPassword, 1, 2);
        myGrid.add(txFullName, 1, 3);
        myGrid.add(txEMail, 1, 4);
        myGrid.add(txPhone, 1, 5);
        myGrid.add(dpDOB, 1, 6);
        myGrid.add(hbBtn, 1, 8);
        myGrid.add(actiontarget, 1, 6);

        //Setting the btn event
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(txFullName.getText());
                System.out.println(txEMail.getText());
                System.out.println(txPhone.getText());
                LocalDate date = dpDOB.getValue();
                System.out.println(date);
            }
        });

        //Creating myScene with custom layout
        Scene myScene = new Scene(myGrid, 500, 450);
        primaryStage.setScene(myScene);


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

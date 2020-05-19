package hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class HelloFXML extends Application {

    private Stage primaryStage;
    private AnchorPane root;


    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("CalculatorFX");
        showView();
    }

    public void showView(){
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(HelloFXML.class.getResource("calc.fxml"),
                    ResourceBundle.getBundle("hellofx.calc"));

        Scene scene = new Scene(root, 360, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
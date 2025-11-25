package de.gfn.dbdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TodoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TodoApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        /*
        Label text = new Label("Hallo JavaFX");
        Button btn = new Button("Click!");
        VBox box = new VBox(text, btn);
        Scene scene = new Scene(box);
        */

        stage.setTitle("Todo-App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
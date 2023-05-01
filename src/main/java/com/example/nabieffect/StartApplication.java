package com.example.nabieffect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {
    private static Scene scene;

    public StartApplication(){
    }

    public static Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("calendar-view.fxml"));
        scene = new Scene((Parent)fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource(fxml + ".fxml"));
        scene.setRoot((Parent)fxmlLoader.load());
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.rentalps.rentalps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(
                    App.class.getResource("/fxml/LoginView.fxml")
            );

            Scene scene = new Scene(root);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/logos.png")));
            stage.setTitle("Rental PS");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Gagal memuat LoginView.fxml");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
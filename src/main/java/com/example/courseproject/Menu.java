package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Menu {
    @FXML
    private Pane sushi;
    public void goToTheSushi(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("sushi.fxml");
    }

    @FXML
    void goToTheSoup(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("soup.fxml");
    }
    @FXML
    void goToTheSalad(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("salad.fxml");
    }

    @FXML
    void goToTheNoodles(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("noodles.fxml");
    }

    @FXML
    void goToTheDessert(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("desserts.fxml");
    }

    @FXML
    void goToTheDrink(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("drinks.fxml");
    }
    @FXML
    void backToTheProfile(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("client.fxml");
    }
}

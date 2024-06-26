package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Menu {
    @FXML
    private Button back;

    @FXML
    private ImageView d;

    @FXML
    private Pane drink;

    @FXML
    private Pane noodles;

    @FXML
    private ImageView noodles1;

    @FXML
    private ImageView s;

    @FXML
    private Pane salad;

    @FXML
    private ImageView salad1;

    @FXML
    private Pane sushi;
    private static String category;

    public void goToTheSushi(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        setCategory("Суши и роллы");
        HelloApplication app = new HelloApplication();
        app.changeScene("sushi.fxml");
    }
    @FXML
    void goToTheSalad(MouseEvent event) throws IOException {
        setCategory("Салаты");
        HelloApplication app = new HelloApplication();
        app.changeScene("salad.fxml");
    }
    @FXML
    void goToTheNoodles(MouseEvent event) throws IOException {
        setCategory("Рис и лапша");
        HelloApplication app = new HelloApplication();
        app.changeScene("noodles.fxml");
    }
    @FXML
    void goToTheDrink(MouseEvent event) throws IOException {
        setCategory("Напитки");
        HelloApplication app = new HelloApplication();
        app.changeScene("drinks.fxml");
    }
    @FXML
    void backToTheProfile(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene(HelloController.getCurrentRole());
    }

    public static String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Cook {
    @FXML
    private Button logOut;

    @FXML
    private Button menu;

    @FXML
    private Button productsInStock;

    @FXML
    private Button profileCook;

    @FXML
    void goToTheMenu(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("menu.fxml");
    }

    @FXML
    void goToTheProfileCook(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("profileEmployee.fxml");
    }

    @FXML
    void goToTheStock(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("stock.fxml");
    }

    @FXML
    void userLogOut(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("hello-view.fxml");
    }
}

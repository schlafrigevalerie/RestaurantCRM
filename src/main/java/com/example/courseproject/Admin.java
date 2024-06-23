package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Admin {

    @FXML
    private Button freeTables;

    @FXML
    private Button logOut;

    @FXML
    private Button menu;

    @FXML
    private Button productsInStock;

    @FXML
    private Button profileAdmin;

    @FXML
    void goToTheMenu(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("menu.fxml");
    }

    @FXML
    void goToTheProfileAdmin(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("profileEmployee.fxml");
    }

    @FXML
    void goToTheStock(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("stockAdmin.fxml");
    }

    @FXML
    void goToTheTables(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("tables.fxml");
    }

    @FXML
    void userLogOut(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("hello-view.fxml");
    }
}

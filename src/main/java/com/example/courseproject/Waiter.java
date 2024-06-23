package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Waiter {
    @FXML
    private Button freeTables;

    @FXML
    private Button logOut;
    @FXML
    private Button menu;

    @FXML
    private Button profileWaiter;

    @FXML
    void goToTheMenu(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("menu.fxml");
    }

    @FXML
    void goToTheTables(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("tables.fxml");
    }


    @FXML
    void goToTheProfileWaiter(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("profileEmployee.fxml");
    }


    @FXML
    void userLogOut(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("hello-view.fxml");
    }
}

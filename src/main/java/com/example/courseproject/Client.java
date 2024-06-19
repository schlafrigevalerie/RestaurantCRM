package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Client {
    @FXML
    private Button logOut;



    public void userLogOut(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("hello-view.fxml");
    }
}

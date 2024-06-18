package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class SuccessfulRegistration {
    @FXML
    private Button back;

    public void backToTheMainPage(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("hello-view.fxml");
    }
}

package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label welcomeText2;


//    private Button onHelloButtonClick;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Вы администратор!");
//        onHelloButtonClick.setText("Вы кликнули!");
    }
    @FXML
    protected void onHelloButtonClick2() {
        welcomeText2.setText("Вы официант!");
//        onHelloButtonClick.setText("Вы кликнули!");
    }
}
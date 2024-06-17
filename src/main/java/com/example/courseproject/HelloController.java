package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController {

    private ChoiceBox<String> role;


    @FXML
    private Label welcomeText;

    @FXML
    private Label welcomeText2;


    //    private Button onHelloButtonClick;
//    @FXML
////    void getRole(MouseEvent event) {
////        role.getItems().addAll("Администратор", "Повар", "Официант");
////    }

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
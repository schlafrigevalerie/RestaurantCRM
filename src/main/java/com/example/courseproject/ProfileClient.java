package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class ProfileClient {
    @FXML
    private TextField INN;

    @FXML
    private TextField address;

    @FXML
    private Rectangle back;

    @FXML
    private Polygon back2;

    @FXML
    private TextField name;

    @FXML
    private Label noChanges;

    @FXML
    private PasswordField password;

    @FXML
    private Button save;

    @FXML
    private TextField surname;

    @FXML
    void saveChanges(MouseEvent event) {
        //
    }
    @FXML
    void backToTheProfile(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("client.fxml");
    }

}

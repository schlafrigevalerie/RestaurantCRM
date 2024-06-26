package com.example.courseproject;

import Models.AuthorizationModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    public HelloController(){

    }
    @FXML
    private ChoiceBox<String> roles;

    @FXML
    private Button registration;

    @FXML
    private Button button;

    @FXML
    private PasswordField password;

    @FXML
    private TextField login;

    @FXML
    private Label wrongLogin;
//    private static String profileLogin;
//    private static String currentRole;

    //private HelloApplication app;

    public void userLogin(javafx.scene.input.MouseEvent mouseEvent) throws IOException, ClassNotFoundException, SQLException {
        AuthorizationModel.authorization(roles,  login, wrongLogin, password);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roles.getItems().addAll("Администратор", "Повар", "Официант", "Клиент");
    }

    public void registration(MouseEvent mouseEvent) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("registration.fxml");
    }

}
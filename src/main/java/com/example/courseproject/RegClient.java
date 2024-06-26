package com.example.courseproject;

import Models.ClientModel;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.sql.*;

import java.io.IOException;

public class RegClient {
    @FXML
    private Label wrongReg;
    @FXML
    private TextField INN;
    @FXML
    private Button back;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password2;
    @FXML
    private CheckBox processingOfPersonalData;
    @FXML
    private Button reg;

    @FXML
    private TextField address;
    @FXML
    private TextField login;
    @FXML
    void backToTheMainPage(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("hello-view.fxml");
    }

    @FXML
    void regClient(MouseEvent event) throws IOException, ClassNotFoundException, SQLException {
        ClientModel.regClient(wrongReg, name, login, address, INN, password, password2, processingOfPersonalData);
    }
}

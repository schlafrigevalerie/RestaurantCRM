package com.example.courseproject;

import Models.ClientModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ProfileClient implements Initializable {
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private TextField INN;

    @FXML
    private TextField address;
    @FXML
    private Button back;
    @FXML
    private TextField name;

    @FXML
    private Label noChanges;
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label innLabel;
    @FXML
    private Label loginLabel;
    @FXML
    private Label passwordLabel;



    @FXML
    private Button save;

    @FXML
    private TextField surname;


    @FXML
    void saveChanges(MouseEvent event) {
        //обращаемся к модели клиента
       ClientModel.updateInfo(name, nameLabel, INN, innLabel, address, addressLabel, login, loginLabel, password, passwordLabel);
    }
    @FXML
    void backToTheProfile(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("client.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //обращаемся к модели клиента
        ClientModel.findClient(nameLabel,addressLabel,innLabel,loginLabel,passwordLabel);
    }

}

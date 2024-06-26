package com.example.courseproject;

import Models.AuthorizationModel;
import Models.EmployeeModel;
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

public class ProfileEmployee implements Initializable {
    @FXML
    private TextField address;

    @FXML
    private Label addressLabel;

    @FXML
    private Button back;

    @FXML
    private TextField login;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField name;

    @FXML
    private Label nameLabel;

    @FXML
    private Label noChanges;

    @FXML
    private PasswordField password;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Button save;

    @FXML
    void backToTheProfile(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene(AuthorizationModel.getCurrentRole());
    }

    @FXML
    void saveChanges(MouseEvent event) throws SQLException {
        EmployeeModel.updateInfo(name, nameLabel, phoneNumber, phoneNumberLabel, address, addressLabel, login, loginLabel, password, passwordLabel, noChanges);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            EmployeeModel.findEmployee(nameLabel,addressLabel,phoneNumberLabel,loginLabel,passwordLabel);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

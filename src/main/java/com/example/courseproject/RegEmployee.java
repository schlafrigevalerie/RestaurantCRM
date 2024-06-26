package com.example.courseproject;

import Models.EmployeeModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RegEmployee implements Initializable {
    @FXML
    private TextField address;
    @FXML
    private Button back;
    @FXML
    private TextField name;
    @FXML
    private CheckBox processingOfPersonalData;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password2;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField login;
    @FXML
    private ChoiceBox<String> roles;
    @FXML
    private Button reg;
    @FXML
    private Label wrongReg;

    public void backToTheMainPage(MouseEvent mouseEvent) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("hello-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            EmployeeModel.findRoles(roles);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void regEmployee(MouseEvent mouseEvent) throws IOException, SQLException, ClassNotFoundException {
        EmployeeModel.regEmployee(wrongReg, roles, name, login, address, phoneNumber, password, password2, processingOfPersonalData);
    }
}

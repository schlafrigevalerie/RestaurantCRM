package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

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
    private TextField surname;

    @FXML
    private TextField telephoneNumber;

    @FXML
    void backToTheMainPage(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("hello-view.fxml");
    }

    @FXML
    void regClient(MouseEvent event) throws IOException {
        wrongReg.setText("Проверьте заполненные поля");
        if (!name.getText().isEmpty() && !surname.getText().isEmpty() && telephoneNumber.getText().matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$") && INN.getText().matches("^\\d{10}$|^\\d{12}$") && !password.getText().isEmpty() && !password2.getText().isEmpty() && processingOfPersonalData.isSelected()){
            HelloApplication app = new HelloApplication();
            app.changeScene("successfulRegistration.fxml");
        }
    }
}

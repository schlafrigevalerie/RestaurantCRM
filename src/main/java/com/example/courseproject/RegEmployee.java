package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegEmployee implements Initializable {
    @FXML
    private TextField address;

    @FXML
    private Button back;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private CheckBox processingOfPersonalData;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password2;

    @FXML
    private TextField phoneNumber;

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
        roles.getItems().addAll("Администратор", "Повар", "Официант", "Клиент");
    }

    public void regEmployee(MouseEvent mouseEvent) throws IOException {
        wrongReg.setText("Проверьте заполненные поля");
        if (!name.getText().isEmpty() && !surname.getText().isEmpty() && phoneNumber.getText().matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$") && !address.getText().isEmpty() && !password.getText().isEmpty() && !password2.getText().isEmpty() && processingOfPersonalData.isSelected()){
            HelloApplication app = new HelloApplication();
            app.changeScene("successfulRegistration.fxml");
        }
    }
}

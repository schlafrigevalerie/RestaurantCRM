package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Registration implements Initializable {
    @FXML
    private Button reg;

    @FXML
    private ChoiceBox<String> roles;
    @FXML
    private Button back;
    @FXML
    private Label wrongRole;



    public void getRole(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        HelloApplication app = new HelloApplication();
        wrongRole.setText("Пожалуйста, выберите роль");
        String role = roles.getSelectionModel().getSelectedItem();
        System.out.println(role);
        if (role.equals("Клиент")){
            app.changeScene("regClient.fxml");
        }
        else if (role.equals("Сотрудник")){
            app.changeScene("regEmployee.fxml");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roles.getItems().addAll("Сотрудник", "Клиент");
    }

    public void backToTheMainPage(MouseEvent mouseEvent) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("hello-view.fxml");
    }
}

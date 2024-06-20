package com.example.courseproject;

import javafx.fxml.FXML;
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
    private TextField surname;

    @FXML
    private TextField address;
    @FXML
    private TextField login;

    Connection connection = null;



    @FXML
    void backToTheMainPage(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("hello-view.fxml");
    }

    @FXML
    void regClient(MouseEvent event) throws IOException, ClassNotFoundException, SQLException {
        wrongReg.setText("Проверьте заполненные поля");

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/restaurant",
                "valerie", "kvenn72!");

        // Создаем объект Statement для выполнения запросов к базе данных
        Statement statement = connection.createStatement();


        if (!name.getText().isEmpty() && !login.getText().isEmpty() && !surname.getText().isEmpty() && !address.getText().isEmpty() && INN.getText().matches("^\\d{10}$|^\\d{12}$") && !password.getText().isEmpty() && !password2.getText().isEmpty() && processingOfPersonalData.isSelected()){

            String query = "INSERT INTO customers(name, address, individual_tax_number, pass, login) values ( '" + name.getText() + "' , '" + address.getText() + "' , '" + INN.getText() + "' , '" + password.getText() + "' , '" + login.getText() + "')";
            statement.executeUpdate(query);
            HelloApplication app = new HelloApplication();
            app.changeScene("successfulRegistration.fxml");
        }

        statement.close();
        connection.close();
    }
}

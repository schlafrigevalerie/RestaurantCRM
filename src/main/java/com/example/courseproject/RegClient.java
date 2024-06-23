package com.example.courseproject;

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
    private Singleton s;
    @FXML
    void backToTheMainPage(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("hello-view.fxml");
    }

    @FXML
    void regClient(MouseEvent event) throws IOException, ClassNotFoundException, SQLException {
        wrongReg.setText("Проверьте заполненные поля");

        //Class.forName("com.mysql.cj.jdbc.Driver");

        // Создаем объект Statement для выполнения запросов к базе данных
        Statement statement = Singleton.getInstance().getConnection().createStatement();

        String queryCheck = String.format("SELECT COUNT(*) FROM clients WHERE login = '%s';",login.getText());
        ResultSet result = statement.executeQuery(queryCheck);
        if (!name.getText().isEmpty() && !login.getText().isEmpty() && !address.getText().isEmpty() && INN.getText().matches("^\\d{10}$|^\\d{12}$") && !password.getText().isEmpty() && !password2.getText().isEmpty() && processingOfPersonalData.isSelected()){
            if (result.next()) {
                int count = result.getInt(1);
                if (count > 0) {
                    wrongReg.setText("Такой логин существует");
                } else {
                    String query = String.format("INSERT INTO clients(name, address, individual_tax_number, passw, login) values ( '%s' , '%s' , '%s' , '%s' , '%s')", name.getText(), address.getText(), INN.getText(), password.getText(), login.getText());
                    statement.executeUpdate(query);
                    HelloApplication app = new HelloApplication();
                    app.changeScene("successfulRegistration.fxml");
                }
            }
        }
        statement.close();
        Singleton.getInstance().getConnection().close();
    }
}

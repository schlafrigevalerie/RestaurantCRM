package com.example.courseproject;

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
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = "SELECT * FROM roles";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                roles.getItems().add(result.getString("name"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void regEmployee(MouseEvent mouseEvent) throws IOException, SQLException, ClassNotFoundException {
        wrongReg.setText("Проверьте заполненные поля");
        String role = roles.getSelectionModel().getSelectedItem();
        boolean flag = false;

        Class.forName("com.mysql.cj.jdbc.Driver");

        Statement statement = Singleton.getInstance().getConnection().createStatement();
        String queryCheckStaff = String.format("SELECT COUNT(*) FROM staff WHERE login = '%s';", login.getText());
        String queryCheckIdEmp = String.format("SELECT id FROM staff WHERE login = '%s';",login.getText());
        String queryCheckIdRole = String.format("SELECT id FROM roles WHERE name = '%s';",role);

        ResultSet resultLog = statement.executeQuery(queryCheckStaff);

        if (!name.getText().isEmpty() && phoneNumber.getText().matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$") && !address.getText().isEmpty() && !password.getText().isEmpty() && !password2.getText().isEmpty() && processingOfPersonalData.isSelected()){
            if (resultLog.next()) {
                int count1 = resultLog.getInt(1);
                if (count1 > 0) {
                    wrongReg.setText("Такой логин существует");
                } else {
                    String query = String.format("INSERT INTO staff(name, address, phone_number, login, passw) values ( '%s' , '%s' , '%s' , '%s' , '%s')",name.getText(),address.getText(),phoneNumber.getText(),login.getText(),password.getText());
                    statement.executeUpdate(query);
                    flag = true;
                    HelloApplication app = new HelloApplication();
                    app.changeScene("successfulRegistration.fxml");
                }
            }
            ResultSet resultIdStuff = statement.executeQuery(queryCheckIdEmp);
            if (resultIdStuff.next()) {
                ResultSet resultIdRole = statement.executeQuery(queryCheckIdRole);
                if (resultIdRole.next()) {
                    if (flag) {
                        String queryInsertStaffRole = String.format("INSERT INTO staff_roles(stf_id, role_id) VALUES ((select id from staff where login = '%s'),(select id from roles where name = '%s'))", login.getText(), role);
                        statement.executeUpdate(queryInsertStaffRole);
                    }
                }
            }
        }
    }
}

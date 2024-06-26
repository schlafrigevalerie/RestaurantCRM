package Models;

import com.example.courseproject.HelloApplication;
import com.example.courseproject.HelloController;
import com.example.courseproject.Singleton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeModel {
    public static void regEmployee(Label wrongReg, ChoiceBox<String> roles, TextField name, TextField login, TextField address, TextField phoneNumber, TextField password,
                                   TextField password2, CheckBox processingOfPersonalData) throws SQLException, IOException {
        wrongReg.setText("Проверьте заполненные поля");
        String role = roles.getSelectionModel().getSelectedItem();
        boolean flag = false;

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
    public static void findRoles(ChoiceBox<String> roles) throws SQLException {
        Statement statement = Singleton.getInstance().getConnection().createStatement();
        String query = "SELECT * FROM roles";
        ResultSet result = statement.executeQuery(query);
        while (result.next()) {
            roles.getItems().add(result.getString("name"));
        }
    }

    public static void updateInfo(TextField name, Label nameLabel, TextField phoneNumber, Label phoneNumberLabel, TextField
            address, Label addressLabel, TextField login, Label loginLabel, TextField password, Label passwordLabel, Label noChanges) throws SQLException {
        Statement statement = Singleton.getInstance().getConnection().createStatement();
        String query = "SELECT * FROM staff WHERE login = '" + AuthorizationModel.getLogin() + "';";
        ResultSet result = statement.executeQuery(query);
        if (result.next()){
            if (!name.getText().isEmpty()){
                nameLabel.setText(name.getText());
                statement.executeUpdate("UPDATE staff SET name = '" + name.getText() + "' WHERE login = '" + AuthorizationModel.getLogin() + "';");
            }
            if  (!phoneNumber.getText().isEmpty()){
                statement.executeUpdate("UPDATE staff SET individual_tax_number = '" + phoneNumber.getText() + "' WHERE login = '" + AuthorizationModel.getLogin() + "';");
                phoneNumberLabel.setText(phoneNumber.getText());
            }
            if (!address.getText().isEmpty()){
                statement.executeUpdate("UPDATE staff SET address = '" + address.getText() + "' WHERE login = '" + AuthorizationModel.getLogin() + "';");
                addressLabel.setText(address.getText());
            }
            if (!login.getText().isEmpty()){
                statement.executeUpdate("UPDATE staff SET login = '" + login.getText() + "' WHERE login = '" + AuthorizationModel.getLogin() + "';");
                loginLabel.setText(login.getText());
            }
            if (!password.getText().isEmpty()){
                statement.executeUpdate("UPDATE staff SET passw = '" + password.getText() + "' WHERE login = '" + AuthorizationModel.getLogin() + "';");
                passwordLabel.setText(password.getText());
            }
            else {
                noChanges.setText("Изменений нет");
            }
        }
    }

    public static void findEmployee(Label nameLabel, Label addressLabel, Label phoneNumberLabel, Label loginLabel, Label passwordLabel) throws SQLException {
        Statement statement = Singleton.getInstance().getConnection().createStatement();
        String query = "SELECT * FROM staff WHERE login = '" + AuthorizationModel.getLogin() + "';";
        ResultSet result = statement.executeQuery(query);
        if (result.next()){
            nameLabel.setText(result.getString("name"));
            addressLabel.setText(result.getString("address"));
            phoneNumberLabel.setText(result.getString("phone_number"));
            loginLabel.setText(result.getString("login"));
            passwordLabel.setText(result.getString("passw"));
        }
        result.close();
        statement.close();
    }
}

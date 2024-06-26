package Models;

import com.example.courseproject.HelloApplication;
import com.example.courseproject.HelloController;
import com.example.courseproject.Singleton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientModel {
    public static void updateInfo(TextField name, Label nameLabel, TextField INN, Label innLabel, TextField
            address, Label addressLabel, TextField login, Label loginLabel, TextField password, Label passwordLabel) {
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = "SELECT * FROM clients WHERE login = '" + AuthorizationModel.getLogin() + "';";
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                if (!name.getText().isEmpty()) {
                    nameLabel.setText(name.getText());
                    statement.executeUpdate("UPDATE clients SET name = '" + name.getText() + "' WHERE login = '" + AuthorizationModel.getLogin() + "';");
                }
                if (!INN.getText().isEmpty()) {
                    statement.executeUpdate("UPDATE clients SET individual_tax_number = '" + INN.getText() + "' WHERE login = '" + AuthorizationModel.getLogin() + "';");
                    innLabel.setText(INN.getText());
                }
                if (!address.getText().isEmpty()) {
                    statement.executeUpdate("UPDATE clients SET address = '" + address.getText() + "' WHERE login = '" + AuthorizationModel.getLogin() + "';");
                    addressLabel.setText(address.getText());
                }
                if (!login.getText().isEmpty()) {
                    statement.executeUpdate("UPDATE clients SET login = '" + login.getText() + "' WHERE login = '" + AuthorizationModel.getLogin() + "';");
                    loginLabel.setText(login.getText());
                }
                if (!password.getText().isEmpty()) {
                    statement.executeUpdate("UPDATE clients SET passw = '" + password.getText() + "' WHERE login = '" + AuthorizationModel.getLogin() + "';");
                    passwordLabel.setText(password.getText());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void findClient(Label nameLabel, Label addressLabel, Label innLabel, Label loginLabel, Label passwordLabel){
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = "SELECT * FROM clients WHERE login = '" + AuthorizationModel.getLogin() + "';";
            ResultSet result = statement.executeQuery(query);
            if (result.next()){
                nameLabel.setText(result.getString("name"));
                addressLabel.setText(result.getString("address"));
                innLabel.setText(result.getString("individual_tax_number"));
                loginLabel.setText(result.getString("login"));
                passwordLabel.setText(result.getString("passw"));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void regClient(Label wrongReg, TextField name, TextField login, TextField address, TextField INN, TextField password,
                                 TextField password2, CheckBox processingOfPersonalData){
        try {
            wrongReg.setText("Проверьте заполненные поля");
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
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

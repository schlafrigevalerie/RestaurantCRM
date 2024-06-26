package com.example.courseproject;

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

public class ProfileClient implements Initializable {
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private TextField INN;

    @FXML
    private TextField address;
    @FXML
    private Button back;
    @FXML
    private TextField name;

    @FXML
    private Label noChanges;
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label innLabel;
    @FXML
    private Label loginLabel;
    @FXML
    private Label passwordLabel;



    @FXML
    private Button save;

    @FXML
    private TextField surname;


    @FXML
    void saveChanges(MouseEvent event) {
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = "SELECT * FROM clients WHERE login = '" + HelloController.getLogin() + "';";
            ResultSet result = statement.executeQuery(query);
            if (result.next()){
                if (!name.getText().isEmpty()){
                    nameLabel.setText(name.getText());
                    statement.executeUpdate("UPDATE clients SET name = '" + name.getText() + "' WHERE login = '" + HelloController.getLogin() + "';");
                }
                if  (!INN.getText().isEmpty()){
                    statement.executeUpdate("UPDATE clients SET individual_tax_number = '" + INN.getText() + "' WHERE login = '" + HelloController.getLogin() + "';");
                    innLabel.setText(INN.getText());
                }
                if (!address.getText().isEmpty()){
                    statement.executeUpdate("UPDATE clients SET address = '" + address.getText() + "' WHERE login = '" + HelloController.getLogin() + "';");
                    addressLabel.setText(address.getText());
                }
                if (!login.getText().isEmpty()){
                    statement.executeUpdate("UPDATE clients SET login = '" + login.getText() + "' WHERE login = '" + HelloController.getLogin() + "';");
                    loginLabel.setText(login.getText());
                }
                if (!password.getText().isEmpty()){
                    statement.executeUpdate("UPDATE clients SET passw = '" + password.getText() + "' WHERE login = '" + HelloController.getLogin() + "';");
                    passwordLabel.setText(password.getText());
                }
                else {
                    noChanges.setText("Изменений нет");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void backToTheProfile(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("client.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = "SELECT * FROM clients WHERE login = '" + HelloController.getLogin() + "';";
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

}

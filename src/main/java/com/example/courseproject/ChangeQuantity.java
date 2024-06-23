package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ChangeQuantity implements Initializable {

    @FXML
    private Button back;

    @FXML
    private TextField newQuantity;

    @FXML
    private ChoiceBox<String> products;

    @FXML
    private Button save;
    @FXML
    private Label change;

    @FXML
    void backToTheStock(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        System.out.println(HelloController.getCurrentRole());
        if (HelloController.getCurrentRole().equals("admin.fxml")) {
            app.changeScene("stockAdmin.fxml");
        }
        if (HelloController.getCurrentRole().equals("cook.fxml")) {
            app.changeScene("stock.fxml");
        }
    }

    @FXML
    void saveChanges(MouseEvent event) {
        String product = products.getSelectionModel().getSelectedItem();
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = "SELECT * FROM ingredients WHERE name = '" + product  + "';";
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                int currentQuantity = result.getInt("quantity_in_stock");
                if (Integer.parseInt(newQuantity.getText()) < currentQuantity){
                    int newProductQuantity = result.getInt("quantity_in_stock") - Integer.parseInt(newQuantity.getText());
                    String changeQuantity = "UPDATE ingredients SET quantity_in_stock = '" + newProductQuantity + "' WHERE name = '" + product + "';";
                    statement.executeUpdate(changeQuantity);
                    change.setText("Изменения сохранены");
                }
                if (Integer.parseInt(newQuantity.getText()) > currentQuantity){
                    change.setText("Количество продуктов на складе меньше введённого числа");
                }
                if (newQuantity.getText().isEmpty()){
                    change.setText("Введите число");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = "SELECT * FROM ingredients";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                products.getItems().add(result.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

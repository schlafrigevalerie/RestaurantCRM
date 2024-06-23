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

public class AddProduct implements Initializable {
    @FXML
    private Label changeProduct;

    @FXML
    private Button back;

    @FXML
    private TextField name;

    @FXML
    private TextField quantity;

    @FXML
    private ChoiceBox<String> units;

    @FXML
    void backToTheStock(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("stockAdmin.fxml");
    }

    @FXML
    void saveChanges(MouseEvent event) {
        String unit_of_measurement = units.getSelectionModel().getSelectedItem();
        String nameProduct = name.getText();
        int quantity_in_stock = Integer.parseInt(quantity.getText());
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = "INSERT INTO ingredients (quantity_in_stock, unit_of_measurement,name) VALUES('" + quantity_in_stock + "','"+ unit_of_measurement + "','" + nameProduct + "');";
            statement.executeUpdate(query);
            changeProduct.setText("Продукт добавлен");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        units.getItems().addAll("л", "кг", "шт", "ст. ложка", "чайная ложка");
    }
}

package com.example.courseproject;

import Models.ProductModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DeleteProduct implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private ChoiceBox<String> names;
    @FXML
    private Label changeProduct;

    @FXML
    void backToTheStock(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("stockAdmin.fxml");
    }

    @FXML
    void deleteProduct(MouseEvent event) {
        ProductModel.deleteProduct(names,changeProduct);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProductModel.findProduct(names);
    }
}

package com.example.courseproject;

import Models.AuthorizationModel;
import Models.ProductModel;
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
        System.out.println(AuthorizationModel.getCurrentRole());
        if (AuthorizationModel.getCurrentRole().equals("admin.fxml")) {
            app.changeScene("stockAdmin.fxml");
        }
        if (AuthorizationModel.getCurrentRole().equals("cook.fxml")) {
            app.changeScene("stock.fxml");
        }
    }

    @FXML
    void saveChanges(MouseEvent event) {
        ProductModel.changeQuantity(products,newQuantity,change);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ProductModel.findIngredients(products);
    }
}

package com.example.courseproject;

import Models.DishesModel;
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

public class DeleteDishes implements Initializable {

    @FXML
    private Button back;

    @FXML
    private ChoiceBox<String> dishes;

    @FXML
    private Button save;
    @FXML
    private Label deleteD;


    @FXML
    void backToTheCat(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        if (Menu.getCategory().equals("Напитки")) {
            app.changeScene("drinks.fxml");
        }
        if (Menu.getCategory().equals("Салаты")) {
            app.changeScene("salad.fxml");
        }
        if (Menu.getCategory().equals("Рис и лапша")) {
            app.changeScene("noodles.fxml");
        }
        if (Menu.getCategory().equals("Суши и роллы")) {
            app.changeScene("sushi.fxml");
        }
    }

    @FXML
    void saveDishes(MouseEvent event) {
        DishesModel.deleteDishes(dishes,deleteD);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DishesModel.findOnCategory(dishes);
    }
}

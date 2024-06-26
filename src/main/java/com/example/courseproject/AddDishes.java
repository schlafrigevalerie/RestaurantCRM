package com.example.courseproject;

import Models.DishesModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddDishes implements Initializable {
    @FXML
    private Label saveCh;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private Button addN;

    @FXML
    private ChoiceBox<String> ingredients;

    @FXML
    private ListView<String> ingredientsList;

    @FXML
    private TextField name;

    @FXML
    private TextField quantity;

    @FXML
    private Button save;


    @FXML
    void addInList(MouseEvent event) {
        DishesModel.addDishes(ingredients,quantity,ingredientsList,name);
    }

    @FXML
    void addInName(MouseEvent event) {
        DishesModel.addNameInTable(name);
    }

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
        if (!name.getText().isEmpty()) {
            saveCh.setText("Блюдо добавлено");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DishesModel.findIngredient(ingredients);
    }
}

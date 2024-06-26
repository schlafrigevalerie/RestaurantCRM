package com.example.courseproject;

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
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String nameIng = ingredients.getSelectionModel().getSelectedItem();
            if (!quantity.getText().isEmpty()) {
                ingredientsList.getItems().add(nameIng);
                String query = "SELECT id FROM dishes WHERE name = '" + name.getText() + "';";
                ResultSet res = statement.executeQuery(query);
                if (res.next()){
                    int idDish = res.getInt("id");
                    String query2 = "SELECT id FROM ingredients WHERE name = '" + nameIng + "';";
                    ResultSet res2 = statement.executeQuery(query2);
                    if (res2.next()){
                        int idIng = res2.getInt("id");
                        String updateIngDish = "INSERT INTO ingredients_dishes(dishes_id,ing_id,quantity_in_the_dish)" +
                                "VALUES('" + idDish + "','" + idIng +"','" + quantity.getText() + "');";
                        statement.executeUpdate(updateIngDish);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addInName(MouseEvent event) {
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String updateDishes = "INSERT INTO dishes(category,name)" +
                    "VALUES('" + Menu.getCategory() + "','" + name.getText() + "');";
            statement.executeUpdate(updateDishes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String numberOrderQuery = "SELECT name FROM ingredients";
            ResultSet result = statement.executeQuery(numberOrderQuery);
            while (result.next()) {
                ingredients.getItems().add(result.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

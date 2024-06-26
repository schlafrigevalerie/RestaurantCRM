package com.example.courseproject;

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
        String nameDishes = dishes.getSelectionModel().getSelectedItem();
        if (!nameDishes.isEmpty()){
            try {
                Statement statement = Singleton.getInstance().getConnection().createStatement();
                String query = "DELETE FROM dishes WHERE name = '" + nameDishes + "';";
                statement.executeUpdate(query);
                deleteD.setText("Блюдо удалено");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String numberOrderQuery = "SELECT name FROM dishes WHERE category = '" + Menu.getCategory() + "';";
            ResultSet result = statement.executeQuery(numberOrderQuery);
            while (result.next()) {
                dishes.getItems().add(result.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

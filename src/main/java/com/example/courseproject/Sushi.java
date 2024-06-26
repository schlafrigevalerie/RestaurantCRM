package com.example.courseproject;

import Models.AuthorizationModel;
import Models.DishesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class Sushi implements Initializable {

    @FXML
    private Button add;

    @FXML
    private Label admin;

    @FXML
    private Button back;

    @FXML
    private Button change;

    @FXML
    private Button delete;

    @FXML
    private TableColumn<Dish, String> ingredients;

    @FXML
    private TableColumn<Dish, String> name;

    @FXML
    private TableColumn<Dish, Float> quantityInDishes;

    @FXML
    private TableView<Dish> sushi;

    ObservableList<Dish> list = FXCollections.observableArrayList();

    @FXML
    void backToTheMenu(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("menu.fxml");
    }
    @FXML
    void addDishes(MouseEvent event) throws IOException {
        if (AuthorizationModel.getCurrentRole().equals("admin.fxml")){
            HelloApplication app = new HelloApplication();
            app.changeScene("addDishes.fxml");
        }
        admin.setText("Необходимо обладать правами администратора");
    }

    @FXML
    void deleteDishes(MouseEvent event) throws IOException {
        if (AuthorizationModel.getCurrentRole().equals("admin.fxml")){
            HelloApplication app = new HelloApplication();
            app.changeScene("deleteDishes.fxml");
        }
        admin.setText("Необходимо обладать правами администратора");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = DishesModel.findDishes("суши и роллы");
            ResultSet result = statement.executeQuery(query);
            String currentNameDish = "";
            String beforeNameDish = "";
            while (result.next()) {
                String dName = "";
                currentNameDish = result.getString("dishes.name");
                if (!Objects.equals(currentNameDish, beforeNameDish)){
                    dName = result.getString("dishes.name");
                }
                String iName = result.getString("ingredients.name");
                float quantity = result.getFloat("ingredients_dishes.quantity_in_the_dish");
                list.add(new Dish(dName, iName, quantity));
                beforeNameDish = currentNameDish;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        name.setCellValueFactory(new PropertyValueFactory<Dish,String>("nameDish"));
        ingredients.setCellValueFactory(new PropertyValueFactory<Dish,String>("ingredient"));
        quantityInDishes.setCellValueFactory(new PropertyValueFactory<Dish, Float>("quantityInDishes"));

        sushi.setItems(list);
    }
}

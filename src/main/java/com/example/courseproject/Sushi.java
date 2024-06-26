package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Sushi implements Initializable {

    @FXML
    private Button back;

    @FXML
    private TableColumn<Dish, String> ingredients;

    @FXML
    private TableColumn<Dish, String> name;

    @FXML
    private TableColumn<Dish, Float> quantityInDishes;

    @FXML
    private TableView<Dish> sushi;

    @FXML
    void backToTheMenu(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("menu.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try{
//            Statement statement = Singleton.getInstance().getConnection().createStatement();
//            //String query = "SELECT * FROM dishes WHERE category = 'суши и роллы'";
//            //ResultSet result = statement.executeQuery(query);
//            while (result.next()) {
//                String name = result.getString("name");
//
//                //String t = result.getString("name");
//                //String uOM = result.getString("unit_of_measurement");
//                list.add(new Products(t, uOM, q));
//
//            }
//        }catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        title.setCellValueFactory(new PropertyValueFactory<Products,String>("title"));
//        unitOfMeasurement.setCellValueFactory(new PropertyValueFactory<Products,String>("unitOfMeasurement"));
//        quantity.setCellValueFactory(new PropertyValueFactory<Products,Integer>("quantity"));
//
//        products.setItems(list);
    }
}

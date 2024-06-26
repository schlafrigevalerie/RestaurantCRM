package com.example.courseproject;

import Models.AuthorizationModel;
import Models.StockModels;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class Stock implements Initializable {
    @FXML
    private Button back;
    @FXML
    private Button changeQuantity;
    @FXML
    private TableView<Products> products;
    @FXML
    private TableColumn<Products, String> title;
    @FXML
    private TableColumn<Products, String> unitOfMeasurement;
    @FXML
    private TableColumn<Products, Integer> quantity;

//    ObservableList<Products> list = FXCollections.observableArrayList(
//
//    );
    ObservableList<Products> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = StockModels.findStock();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                int q = result.getInt("quantity_in_stock");
                String t = result.getString("name");
                String uOM = result.getString("unit_of_measurement");
                list.add(new Products(t, uOM, q));

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        title.setCellValueFactory(new PropertyValueFactory<Products,String>("title"));
        unitOfMeasurement.setCellValueFactory(new PropertyValueFactory<Products,String>("unitOfMeasurement"));
        quantity.setCellValueFactory(new PropertyValueFactory<Products,Integer>("quantity"));

        products.setItems(list);
    }
    @FXML
    void backToTheProfile(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene(AuthorizationModel.getCurrentRole());
    }

    @FXML
    void changeTheQuantity(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("changeQuantity.fxml");
    }
}

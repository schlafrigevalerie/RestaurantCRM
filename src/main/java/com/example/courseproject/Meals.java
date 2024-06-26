package com.example.courseproject;

import Models.AuthorizationModel;
import Models.MealsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class Meals implements Initializable {
    @FXML
    private Button back;

    @FXML
    private Button addMeals;

    @FXML
    private TableColumn<Meal, LocalTime> beginTime;

    @FXML
    private TableColumn<Meal, LocalDate> date;

    @FXML
    private TableColumn<Meal, LocalTime> endTime;

    @FXML
    private TableView<Meal> meals;

    @FXML
    private TableColumn<Meal, Integer> numberOrder;

    @FXML
    private TableColumn<Meal, Integer> numberTable;
    //LocalDate today = LocalDate.now();
    ObservableList<Meal> list = FXCollections.observableArrayList();

    @FXML
    void backToTheProfile(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene(AuthorizationModel.getCurrentRole());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = MealsModel.findMeals();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                LocalDate d = result.getDate("date_of_meals").toLocalDate();
                LocalTime start = result.getTime("start_time").toLocalTime();
                LocalTime end = result.getTime("end_time").toLocalTime();
                int orderId = result.getInt("orders_id");
                int tableId = result.getInt("tables_id");
                list.add(new Meal(orderId, d, start, end, tableId));
                numberOrder.setCellValueFactory(new PropertyValueFactory<Meal,Integer>("numberOrder"));
                date.setCellValueFactory(new PropertyValueFactory<Meal,LocalDate>("dateMeals"));
                beginTime.setCellValueFactory(new PropertyValueFactory<Meal,LocalTime>("beginTime"));
                endTime.setCellValueFactory(new PropertyValueFactory<Meal,LocalTime>("endTime"));
                numberTable.setCellValueFactory(new PropertyValueFactory<Meal,Integer>("numberTable"));

                meals.setItems(list);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void addAMeal(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("addMeal.fxml");
    }
}

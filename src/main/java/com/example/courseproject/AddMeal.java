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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddMeal implements Initializable {

    @FXML
    private Button back;

    @FXML
    private DatePicker date;

    @FXML
    private TextField endTime;

    @FXML
    private ChoiceBox<Integer> numberOrder;

    @FXML
    private ChoiceBox<Integer> numberTable;

    @FXML
    private Label change;

    @FXML
    private Button save;

    @FXML
    private TextField startTime;

    @FXML
    void backToTheMeals(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("meals.fxml");
    }

    @FXML
    void saveChanges(MouseEvent event) {
        int numberOrd = numberOrder.getSelectionModel().getSelectedItem();
        int numberTab = numberTable.getSelectionModel().getSelectedItem();
        LocalTime start = LocalTime.parse(startTime.getText());
        LocalTime end = LocalTime.parse(endTime.getText());
        LocalDate dateMeal = date.getValue();
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = "INSERT INTO meals (date_of_meals, start_time, end_time, stf_rl_id,ord_tbl_id) VALUES('" + dateMeal + "','"+ start + "','" + end + "','" + numberOrd + "','" + numberTab + "');";
            statement.executeUpdate(query);
            change.setText("Продукт добавлен");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String numberOrderQuery = "SELECT id FROM orders";
            ResultSet result = statement.executeQuery(numberOrderQuery);
            while (result.next()) {
                numberOrder.getItems().add(result.getInt("id"));
            }
            String numberTableQuery = "SELECT id FROM restaurant_tables";
            ResultSet result2 = statement.executeQuery(numberTableQuery);
            while (result2.next()) {
                numberTable.getItems().add(result2.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

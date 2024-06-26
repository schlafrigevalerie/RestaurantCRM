package Models;

import com.example.courseproject.Singleton;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

public class MealsModel {
    public static void addMeal(ChoiceBox<Integer> numberOrder, ChoiceBox<Integer> numberTable, TextField startTime, TextField endTime, DatePicker date, Label change) {
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

    public static void findOrder(ChoiceBox<Integer> numberOrder, ChoiceBox<Integer> numberTable) {
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
    public static String findMeals(){
        return "SELECT date_of_meals, start_time, end_time, orders_id," +
                " tables_id FROM meals INNER JOIN orders_tables ON meals.ord_tbl_id = orders_tables.id";
    }
}

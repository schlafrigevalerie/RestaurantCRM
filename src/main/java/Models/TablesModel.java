package Models;

import com.example.courseproject.Singleton;
import com.example.courseproject.Table;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

public class TablesModel {
    public static void findId(ChoiceBox<Integer> numTables) {
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String numberOrderQuery = "SELECT id FROM restaurant_tables";
            ResultSet result = statement.executeQuery(numberOrderQuery);
            while (result.next()) {
                numTables.getItems().add(result.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTable(TextField capacityT, Label change) {
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String updateDishes = "INSERT INTO restaurant_tables(capacity)" +
                    "VALUES('" + capacityT.getText() + "');";
            statement.executeUpdate(updateDishes);
            change.setText("Стол добавлен");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteTable(ChoiceBox<Integer> numTables, Label change) {
        int idTable = numTables.getSelectionModel().getSelectedItem();
        if (idTable != 0){
            try {
                Statement statement = Singleton.getInstance().getConnection().createStatement();
                String query = "DELETE FROM restaurant_tables WHERE id = '" + idTable + "';";
                statement.executeUpdate(query);
                change.setText("Столик удалён");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static String findTables(){
        return "SELECT restaurant_tables.id, restaurant_tables.capacity, meals.start_time, meals.end_time, meals.date_of_meals" +
                " FROM restaurant_tables" +
                " INNER JOIN orders_tables INNER JOIN meals ON orders_tables.id = meals.ord_tbl_id";
    }
}

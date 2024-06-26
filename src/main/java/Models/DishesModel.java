package Models;

import com.example.courseproject.Menu;
import com.example.courseproject.Singleton;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DishesModel {
    public static void addDishes(ChoiceBox<String> ingredients, TextField quantity, ListView<String> ingredientsList, TextField name) {
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
    public static void addNameInTable(TextField name){
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String updateDishes = "INSERT INTO dishes(category,name)" +
                    "VALUES('" + Menu.getCategory() + "','" + name.getText() + "');";
            statement.executeUpdate(updateDishes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void findIngredient(ChoiceBox<String> ingredients){
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
    public static String findDishes(String category){
        return "SELECT dishes.name, ingredients_dishes.quantity_in_the_dish, ingredients.name " +
                "FROM dishes INNER JOIN ingredients_dishes  ON dishes.id = ingredients_dishes.dishes_id " +
                "INNER JOIN ingredients ON ingredients_dishes.ing_id = ingredients.id WHERE dishes.category = '" + category + "'";
    }
    public static void deleteDishes(ChoiceBox<String> dishes, Label deleteD){
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

    public static void findOnCategory(ChoiceBox<String> dishes) {
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

package Models;

import com.example.courseproject.Singleton;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductModel {
    public static void deleteProduct(ChoiceBox<String> names, Label changeProduct) {
        String name = names.getSelectionModel().getSelectedItem();
        changeProduct.setText("Выберите продукт для удаления");
        try {
            if (!name.isEmpty()){
                Statement statement = Singleton.getInstance().getConnection().createStatement();
                String query = "DELETE FROM ingredients WHERE name = '" + name + "';";
                statement.executeUpdate(query);
                changeProduct.setText("Продукт удалён");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findProduct(ChoiceBox<String> names) {
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = "SELECT * FROM ingredients";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                names.getItems().add(result.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeQuantity(ChoiceBox<String>products, TextField newQuantity, Label change) {
        String product = products.getSelectionModel().getSelectedItem();
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = "SELECT * FROM ingredients WHERE name = '" + product  + "';";
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                int currentQuantity = result.getInt("quantity_in_stock");
                if (Integer.parseInt(newQuantity.getText()) < currentQuantity){
                    int newProductQuantity = result.getInt("quantity_in_stock") - Integer.parseInt(newQuantity.getText());
                    String changeQuantity = "UPDATE ingredients SET quantity_in_stock = '" + newProductQuantity + "' WHERE name = '" + product + "';";
                    statement.executeUpdate(changeQuantity);
                    change.setText("Изменения сохранены");
                }
                if (Integer.parseInt(newQuantity.getText()) > currentQuantity){
                    change.setText("Количество продуктов на складе меньше введённого числа");
                }
                if (newQuantity.getText().isEmpty()){
                    change.setText("Введите число");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findIngredients(ChoiceBox<String>products) {
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = "SELECT * FROM ingredients";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                products.getItems().add(result.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addProduct(ChoiceBox<String>units, TextField name, TextField quantity, Label changeProduct) {
        String unit_of_measurement = units.getSelectionModel().getSelectedItem();
        String nameProduct = name.getText();
        int quantity_in_stock = Integer.parseInt(quantity.getText());
        try {
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = "INSERT INTO ingredients (quantity_in_stock, unit_of_measurement,name) VALUES('" + quantity_in_stock + "','"+ unit_of_measurement + "','" + nameProduct + "');";
            statement.executeUpdate(query);
            changeProduct.setText("Продукт добавлен");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

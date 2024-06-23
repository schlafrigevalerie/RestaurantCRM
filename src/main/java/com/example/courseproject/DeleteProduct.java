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

public class DeleteProduct implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private ChoiceBox<String> names;
    @FXML
    private Label changeProduct;

    @FXML
    void backToTheStock(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("stockAdmin.fxml");
    }

    @FXML
    void deleteProduct(MouseEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
}

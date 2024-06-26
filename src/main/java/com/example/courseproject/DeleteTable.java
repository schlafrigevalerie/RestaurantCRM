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

public class DeleteTable implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Label change;

    @FXML
    private Button delete;

    @FXML
    private ChoiceBox<Integer> numTables;

    @FXML
    void backToTheTable(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("tables.fxml");
    }

    @FXML
    void deleteT(MouseEvent event) {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

}

package com.example.courseproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class Tables implements Initializable {

    @FXML
    private Button back;

    @FXML
    private TableColumn<Table, Integer> capacity;

    @FXML
    private TableColumn<Table, Integer> number;

    @FXML
    private TableColumn<Table, String> status;

    @FXML
    private TableView<Table> tables;
    private String setStatus;
    ObservableList<Table> list = FXCollections.observableArrayList();

    @FXML
    void backToTheProfile(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene(HelloController.getCurrentRole());
    }

    @FXML
    void saveStatus(MouseEvent event) {
        //прописать здесь внос данных в таблицу
        //переделать базу данных, добавив в сущность
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Statement statement = Singleton.getInstance().getConnection().createStatement();
            String query = "SELECT restaurant_tables.id, restaurant_tables.capacity, meals.start_time, meals.end_time, meals.date_of_meals FROM restaurant_tables INNER JOIN orders_tables INNER JOIN meals ON restaurant_tables.id = orders_tables.tables_id";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                int num = result.getInt("id");
                int cap = result.getInt("capacity");
                if (LocalDate.now().compareTo(result.getDate("date_of_meals").toLocalDate()) == 0 && LocalTime.now().compareTo(result.getTime("start_time").toLocalTime()) < 0 && LocalTime.now().compareTo(result.getTime("end_time").toLocalTime()) > 0 ) {
                    setStatus = "Занят";
                }else{
                    setStatus = "Свободен";
                }
                list.add(new Table(num,cap,setStatus));
                number.setCellValueFactory(new PropertyValueFactory<Table,Integer>("number"));
                capacity.setCellValueFactory(new PropertyValueFactory<Table, Integer>("capacity"));
                status.setCellValueFactory(new PropertyValueFactory<Table,String>("status"));

                tables.setItems(list);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        number.setCellValueFactory(new PropertyValueFactory<Table,Integer>("number"));
        capacity.setCellValueFactory(new PropertyValueFactory<Table,Integer>("capacity"));
        status.setCellValueFactory(new PropertyValueFactory<Table, String>("status"));

        tables.setItems(list);
    }
}

package com.example.courseproject;

import Models.MealsModel;
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
        MealsModel.addMeal(numberOrder, numberTable, startTime, endTime, date, change);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MealsModel.findOrder(numberOrder, numberTable);
    }
}

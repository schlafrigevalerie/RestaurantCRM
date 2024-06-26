package com.example.courseproject;

import Models.TablesModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class AddTable {

    @FXML
    private Button back;

    @FXML
    private TextField capacityT;

    @FXML
    private Label change;

    @FXML
    private Button save;

    @FXML
    void backToTheTable(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("tables.fxml");
    }

    @FXML
    void saveTable(MouseEvent event) {
        TablesModel.addTable(capacityT,change);
    }

}

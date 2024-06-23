package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Sushi implements Initializable {

    @FXML
    private Button back;

    @FXML
    private TableColumn<?, ?> ingredients;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> quantityInDishes;

    @FXML
    private TableView<?> sushi;

    @FXML
    void backToTheMenu(MouseEvent event) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("menu.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ;
    }
}

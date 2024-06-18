package com.example.courseproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    public HelloController(){

    }
    @FXML
    private ChoiceBox<String> roles;

    @FXML
    private Button button;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Label wrongLogin;

    public void userLogin(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        HelloApplication app = new HelloApplication();
        if (phoneNumber.getText().toString().equals("89514190941") && password.getText().toString().equals("123")){
            wrongLogin.setText("Success!");

            app.changeScene("afterLogin.fxml");
        }
        else if(phoneNumber.getText().isEmpty() && password.getText().isEmpty()){
            wrongLogin.setText("Please enter your data");
        }
        else{
            wrongLogin.setText("Wrong number or password");
        }
    }


    //    private Button onHelloButtonClick;
//    @FXML
//    void getRole(MouseEvent event) {
//        role.getItems().addAll("Администратор", "Повар", "Официант");
//    }

//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Вы администратор!");
////        onHelloButtonClick.setText("Вы кликнули!");
//    }
//
//    @FXML
//    protected void onHelloButtonClick2() {
//        welcomeText2.setText("Вы официант!");
////        onHelloButtonClick.setText("Вы кликнули!");
//    }

//    public void userLogin(ActionEvent event) throws IOException {
//        checkLogin();
//    }
//    private void checkLogin() throws IOException{
//        //HelloApplication app = new HelloApplication();
//        if (phoneNumber.getText().toString().equals("89514190941") && password.getText().toString().equals("123")){
//            wrongLogin.setText("Success!");
//
//            //app.changeScene("afterLogin.fxml");
//        }
//        else if(phoneNumber.getText().isEmpty() && password.getText().isEmpty()){
//            wrongLogin.setText("Please enter your data");
//        }
//        else{
//            wrongLogin.setText("Wrong number or password");
//        }
//    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roles.getItems().addAll("Администратор", "Повар", "Официант", "Клиент");
    }
}
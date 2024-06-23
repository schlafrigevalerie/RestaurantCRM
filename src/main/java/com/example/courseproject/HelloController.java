package com.example.courseproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    public HelloController(){

    }
    @FXML
    private ChoiceBox<String> roles;

    @FXML
    private Button registration;

    @FXML
    private Button button;

    @FXML
    private PasswordField password;

    @FXML
    private TextField login;

    @FXML
    private Label wrongLogin;
    private static String profileLogin;

    //private HelloApplication app;

    public void userLogin(javafx.scene.input.MouseEvent mouseEvent) throws IOException, ClassNotFoundException, SQLException {
        HelloApplication app = new HelloApplication();
        String role = roles.getSelectionModel().getSelectedItem();
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Устанавливаем соединение с базой данных
//        connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/restaurant",
//                "valerie", "kvenn72!");
        profileLogin = login.getText();
        Statement statement = Singleton.getInstance().getConnection().createStatement();
        String query = String.format("SELECT * FROM roles WHERE name = '%s';", role);
        String clientPass = String.format("SELECT COUNT(*) FROM clients WHERE login = '%s' and passw = '%s';", login.getText(), password.getText());
        ResultSet result = statement.executeQuery(query);
        if (role.equals("Администратор") || role.equals("Официант") || role.equals("Повар")){
            if (result.next()){
                int r_id = result.getInt("id");
                String query2 = String.format("SELECT * FROM staff_roles WHERE role_id = '%d'", r_id);
                ResultSet result2 = statement.executeQuery(query2);
                if (result2.next()) {
                    int s_id = result2.getInt("stf_id");
                    String queryCheckLog = String.format("SELECT COUNT(*) FROM staff WHERE id = '%d' and passw = '%s';", s_id, password.getText());
                    ResultSet resultLog = statement.executeQuery(queryCheckLog);
                    if (resultLog.next()) {
                        int count1 = resultLog.getInt(1);
                        if (count1 == 0) {
                            wrongLogin.setText("Такого аккаунта не существует");
                        } else if (role.equals("Официант")) {
                            wrongLogin.setText("Success!");
                            app.changeScene("waiter.fxml");
                        } else if (role.equals("Повар")) {
                            wrongLogin.setText("Success!");
                            app.changeScene("cook.fxml");
                        } else {
                            wrongLogin.setText("Success!");
                            app.changeScene("admin.fxml");
                        }
                    } else {
                        wrongLogin.setText("Данные введены неверно");
                    }
                }
            }
        }else{
            ResultSet resultClient = statement.executeQuery(clientPass);
            if (resultClient.next()){
                int count = resultClient.getInt(1);
                if (count > 0) {
                    wrongLogin.setText("Success!");
                    app.changeScene("client.fxml");
                }else{
                    wrongLogin.setText("Данные введены неверно");
                }
            }
        }

//            wrongLogin.setText("Success!");
//            app.changeScene("client.fxml");
//        }
//        else if (phoneNumber.getText().toString().equals("waiter") && password.getText().toString().equals("123") && role.equals("Официант")){
//            wrongLogin.setText("Success!");
//            app.changeScene("waiter.fxml");
//        }
//        else if (phoneNumber.getText().toString().equals("cook") && password.getText().toString().equals("123") && role.equals("Повар")){
//            wrongLogin.setText("Success!");
//            app.changeScene("cook.fxml");
//        }
//        else if (phoneNumber.getText().toString().equals("admin") && password.getText().toString().equals("123") && role.equals("Администратор")){
//            wrongLogin.setText("Success!");
//            app.changeScene("admin.fxml");
//        }
//        else if(phoneNumber.getText().isEmpty() && password.getText().isEmpty()){
//            wrongLogin.setText("Пожалуйста, введите данные");
//        }
//        else{
//            wrongLogin.setText("Данные введены неверно");
//        }
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
//            //app.changeScene("client.fxml");
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

    public void registration(MouseEvent mouseEvent) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("registration.fxml");
    }
    public static String getLogin(){
        return profileLogin;
    }
}
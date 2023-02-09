package lk.ijse.petClinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginAnchonePane;
    public PasswordField txtPassWord;
    public TextField txtUserName;
    public JFXButton btnLogin;

    String username="user";
    String password="12345";

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String textUserName=txtUserName.getText();
        String textPassword= txtPassWord.getText();
        if (textUserName == null){
            txtUserName.requestFocus();
            return;
        }
        if (textPassword == null){
            txtPassWord.requestFocus();
            return;
        }
        if (textUserName.equals(username) && textPassword.equals(password)){
            try {
                loginAnchonePane.getChildren().clear();
                loginAnchonePane.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/ijse/petClinic/view/MainForm.fxml")));
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Forms error !");
            }

        }else{
            new Alert(Alert.AlertType.ERROR, "Username or password wrong !");
        }
    }
}

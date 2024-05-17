package org.example.demo8;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.example.demo8.controller.Controller;
import org.example.demo8.model.users.AccountUserModel;

import java.net.URL;
import java.util.ResourceBundle;

public class TopBarController implements Initializable,GeneralOperation {

    @FXML
    private Label account_lbl;

    @FXML
    private StackPane btn_back;

    @FXML
    private HBox buttons;

    @FXML
    private StackPane loginSignup_btn;

    @FXML
    private AnchorPane root;

    @FXML
    private Rectangle space;
    private static AccountUserModel currentAccount= Controller.getController().getAccModel();
    public static AccountUserModel getCurrentAccount() {
        return currentAccount;
    }
    public static void setCurrentAccount(AccountUserModel currentAccount) {
        TopBarController.currentAccount = currentAccount;
    }

    @FXML
    void showLoginSignupPage(MouseEvent event) {

    }
    public void showPreviousPage(MouseEvent mouseEvent) {
        backTo();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(currentAccount==null)
            account_lbl.setText("Login / Signup");
        else
            account_lbl.setText(currentAccount.getUserName());
    }

    @Override
    public void backTo() {

    }

    @Override
    public void logout() {

    }

    @Override
    public void login() {

    }

    @Override
    public void signup() {

    }

    @Override
    public void search() {}

}
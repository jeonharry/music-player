package org.example.demo8;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import controller.ArtistController;
import controller.Controller;
import controller.ListenerController;
import model.users.AccountUserModel;

public class LoginSignupPage implements GeneralOperation{

    @FXML
    private Tab LoginTab;

    @FXML
    private StackPane close_btn;

    @FXML
    private Label error_lbl;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUserName;

    @FXML
    private StackPane login_btn;

    @FXML
    private AnchorPane root;

    @FXML
    private Tab signupTab;

    @FXML
    private TabPane tabPane;
    @FXML
    private StackPane signup_btn;
    @FXML
    private TextField inputUserNameSignup;

    @FXML
    private TextField inputPasswordSignup;

    @FXML
    private TextField inputPhoneNumber;

    @FXML
    private DatePicker inputBirthDate;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputFullName;

    @FXML
    private Label errorSignup_lbl;

    @FXML
    private TextField inputBio;

    @FXML
    private RadioButton ListenerIsChosen;

    @FXML
    private ToggleGroup choiseBoxes;

    @FXML
    private RadioButton podcasterIsChosen;

    @FXML
    private RadioButton singerIsChosen;
    private static AccountUserModel signedupAcc;

    public static AccountUserModel getSignedupAcc() {
        return signedupAcc;
    }

    public static void setSignedupAcc(AccountUserModel signedupAcc) {
        LoginSignupPage.signedupAcc = signedupAcc;
    }

    @FXML
    void closePage(MouseEvent event) {
        backTo();
    }

    @FXML
    void signup(MouseEvent event) {
        signup();
    }

    @FXML
    void login(MouseEvent event) {
        login();
    }

    @Override
    public void backTo() {
        Controller.getController().getStackPane().getChildren().removeLast();
    }

    @Override
    public void logout() {}

    @Override
    public void login() {
        error_lbl.setVisible(false);
        try{
            Controller.getController().logIn(inputUserName.getText(),inputPassword.getText());
            backTo();
            FXMLLoader topBarLoader=new FXMLLoader(Main.class.getResource("TopBar.fxml"));
            Controller.getController().getBasePage().topProperty().setValue(topBarLoader.load());
        }catch (Exception exception)
        {
            error_lbl.setVisible(true);
            error_lbl.setText(exception.getMessage());
        }
    }

    @Override
    public void signup() {
        String answer = null;
        errorSignup_lbl.setVisible(false);
        if(!singerIsChosen.isSelected() && !podcasterIsChosen.isSelected() && !ListenerIsChosen.isSelected())
        {
            errorSignup_lbl.setVisible(true);
            errorSignup_lbl.setText("you need to select one of the boxes above");
        }
        try{
            String date=inputBirthDate.getValue().getYear()+"/"+inputBirthDate.getValue().getMonthValue()+"/"+inputBirthDate.getValue().getDayOfMonth();
            if(podcasterIsChosen.isSelected())
            {
                answer=ArtistController.getArtistController().makeNewArtist(inputUserNameSignup.getText(),inputPasswordSignup.getText(),inputFullName.getText(),inputEmail.getText(),inputPhoneNumber.getText(),date,inputBio.getText(),"P");
            }
            else if(singerIsChosen.isSelected())
            {
                answer=ArtistController.getArtistController().makeNewArtist(inputUserNameSignup.getText(),inputPasswordSignup.getText(),inputFullName.getText(),inputEmail.getText(),inputPhoneNumber.getText(),date,inputBio.getText(),"S");
            }
            else
            {
                answer=ListenerController.getListenerController().makeNewListener(inputUserNameSignup.getText(),inputPasswordSignup.getText(),inputFullName.getText(),inputEmail.getText(),inputPhoneNumber.getText(),date);
            }
            if(answer.compareTo("Signed up successfully")==0)
            {
                if(ListenerIsChosen.isSelected())
                {
                    Controller.getController().setNode(Controller.getController().getStackPane().getChildren().getLast());
                    FXMLLoader favGenres=new FXMLLoader(Main.class.getResource("GenresPage.fxml"));
                    backTo();
                    Controller.getController().getStackPane().getChildren().addLast(favGenres.load());
                }
                else
                    backTo();
            }
            else
            {
                errorSignup_lbl.setVisible(true);
                errorSignup_lbl.setText(answer);
            }

        }catch (Exception exception)
        {
            errorSignup_lbl.setVisible(true);
            errorSignup_lbl.setText(exception.getMessage());
        }
    }

    @Override
    public void search() {}

    @FXML
    void singerChosen(MouseEvent event) {
        inputBio.setVisible(true);
    }

    @FXML
    void podcasterChosen(MouseEvent event) {
        inputBio.setVisible(true);
    }

    @FXML
    void listenerChosen(MouseEvent event) {
        inputBio.setVisible(false);
    }
}
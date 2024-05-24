package org.example.demo8;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import controller.ArtistController;
import controller.Controller;
import controller.ListenerController;
import model.users.AccountUserModel;

import java.io.IOException;
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

    @FXML
    private ImageView logOutIcon;

    @FXML
    private StackPane buyPremium_btn;

    @FXML
    void showLoginSignupPage(MouseEvent event) throws IOException {
        if(Controller.getController().getAccModel()==null)
        {
            logOutIcon.setVisible(false);
            FXMLLoader loader=new FXMLLoader(Main.class.getResource("LoginSignupPage.fxml"));
            Controller.getController().getStackPane().getChildren().add(loader.load());
        }
        else
        {
            logOutIcon.setVisible(true);

        }
    }
    @FXML
    void logOut(MouseEvent event) {
        logOutIcon.setVisible(false);
        account_lbl.setText("Login / Signup");
        logout();
        event.consume();
    }
    public void showPreviousPage(MouseEvent mouseEvent) {
        backTo();
    }

    @FXML
    void showBuyPage(MouseEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("BuyPremiumPage.fxml"));
        Controller.getController().getStackPane().getChildren().add(loader.load());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logOutIcon.setVisible(false);
        if(Controller.getController().getAccModel()==null)
            account_lbl.setText("Login / Signup");
        else
        {
            account_lbl.setText(Controller.getController().getAccModel().getUserName());
            logOutIcon.setVisible(true);
        }
    }

    @Override
    public void backTo() {
        try
        {
            if(Controller.getController().getPreviousPage().compareTo("home")==0)
                Controller.getController().getBasePage().centerProperty().setValue(new FXMLLoader(Main.class.getResource("HomePage.fxml")).load());
            else if(Controller.getController().getPreviousPage().compareTo("search")==0)
                Controller.getController().getBasePage().centerProperty().setValue(new FXMLLoader(Main.class.getResource("SearchPage.fxml")).load());
            else if(Controller.getController().getPreviousPage().compareTo("library")==0)
                Controller.getController().getBasePage().centerProperty().setValue(new FXMLLoader(Main.class.getResource("LibraryPage.fxml")).load());
            else if(Controller.getController().getPreviousPage().compareTo("allaudios")==0)
                Controller.getController().getBasePage().centerProperty().setValue(new FXMLLoader(Main.class.getResource("AllAudiosPage.fxml")).load());
            else if(Controller.getController().getPreviousPage().compareTo("allartists")==0)
                Controller.getController().getBasePage().centerProperty().setValue(new FXMLLoader(Main.class.getResource("AllArtistsPage.fxml")).load());
        }catch (Exception exception) {}
    }

    @Override
    public void logout() {
        if(Controller.getController().getAccType().compareTo("L")==0)
        {
            ListenerController.getListenerController().setListener(null);
            Controller.getController().setAccModel(null);
        }
        else if(Controller.getController().getAccType().compareTo("A")==0)
            Controller.getController().setAccModel(null);
        else
        {
            ArtistController.getArtistController().setArtist(null);
            Controller.getController().setAccModel(null);
        }
    }

    @Override
    public void login() {}

    @Override
    public void signup() {}

    @Override
    public void search() {}

}
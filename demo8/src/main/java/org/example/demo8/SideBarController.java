package org.example.demo8;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.example.demo8.controller.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SideBarController implements Initializable,GeneralOperation {

    @FXML
    private StackPane btn_home;

    @FXML
    private StackPane btn_library;

    @FXML
    private StackPane btn_search;

    @FXML
    private VBox buttons;

    @FXML
    private HBox homeBackground;

    @FXML
    private Rectangle homeSelectedOrNot;

    @FXML
    private HBox libraryBackground;

    @FXML
    private Rectangle librarySelectedOrNot;

    @FXML
    private AnchorPane root;

    @FXML
    private HBox searchBackground;

    @FXML
    private Rectangle searchSelectedOrNot;

    @FXML
    void goToHomePage(MouseEvent event) throws IOException {
        homeSelectedOrNot.setVisible(false);
        librarySelectedOrNot.setVisible(true);
        searchSelectedOrNot.setVisible(true);
        homeBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
        searchBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        libraryBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        Controller.getController().getBasePage().centerProperty().setValue(fxmlLoader.load());
    }

    @FXML
    void goToLibrary(MouseEvent event) {
        librarySelectedOrNot.setVisible(false);
        searchSelectedOrNot.setVisible(true);
        homeSelectedOrNot.setVisible(true);
        libraryBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
        homeBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        searchBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        //khkfjydjyln;km;lsdcmas;
    }

    @FXML
    void search(MouseEvent event) {
        searchSelectedOrNot.setVisible(false);
        librarySelectedOrNot.setVisible(true);
        homeSelectedOrNot.setVisible(true);
        searchBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
        homeBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        libraryBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        //jjbkjblkaslcsvndlkvnsdclks
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Controller.getController().getPageType().compareTo("home")==0)
        {
            homeSelectedOrNot.setVisible(false);
            librarySelectedOrNot.setVisible(true);
            searchSelectedOrNot.setVisible(true);
            homeBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
            searchBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
            libraryBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
            try {
                Controller.getController().getBasePage().centerProperty().setValue(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//        else if()
        //knalflclscldvnsdlkcsncjlsbcksjdcbskjdcbskdjcbsdcsd
    }

    @Override
    public void backTo() {}

    @Override
    public void logout() {}

    @Override
    public void login() {}

    @Override
    public void signup() {}

    @Override
    public void search() {

    }
}
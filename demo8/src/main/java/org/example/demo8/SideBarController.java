package org.example.demo8;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import controller.Controller;
import model.users.listeners.ListenerModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SideBarController implements Initializable,GeneralOperation {


    @FXML
    private HBox allAudiosBackground;

    @FXML
    private Rectangle allAudiosSelectedOrNot;

    @FXML
    private StackPane btn_allAudios;

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
        if(librarySelectedOrNot.isVisible() && homeSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("search");
        if(searchSelectedOrNot.isVisible() && homeSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("library");
        homeSelectedOrNot.setVisible(false);
        librarySelectedOrNot.setVisible(true);
        searchSelectedOrNot.setVisible(true);
        allAudiosSelectedOrNot.setVisible(true);
        homeBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
        searchBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        libraryBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        allAudiosBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        Controller.getController().getBasePage().centerProperty().setValue(fxmlLoader.load());
    }

    @FXML
    void goToLibrary(MouseEvent event) {
        if(librarySelectedOrNot.isVisible() && homeSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("search");
        if(librarySelectedOrNot.isVisible() && searchSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("home");
        librarySelectedOrNot.setVisible(false);
        searchSelectedOrNot.setVisible(true);
        homeSelectedOrNot.setVisible(true);
        allAudiosSelectedOrNot.setVisible(true);
        libraryBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
        homeBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        searchBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        allAudiosBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        if(!(Controller.getController().getAccModel() instanceof ListenerModel))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("its for listeners only");
            alert.showAndWait();
        }
//        else
            //jkgkgfyfuyfyuy
    }

    @FXML
    void search(MouseEvent event) {
        if(librarySelectedOrNot.isVisible() && searchSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("home");
        if(searchSelectedOrNot.isVisible() && homeSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("library");
        searchSelectedOrNot.setVisible(false);
        librarySelectedOrNot.setVisible(true);
        homeSelectedOrNot.setVisible(true);
        allAudiosSelectedOrNot.setVisible(true);
        searchBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
        homeBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        libraryBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        allAudiosBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        search();
    }

    @FXML
    void goToAllAudios(MouseEvent event) throws IOException {
        searchSelectedOrNot.setVisible(true);
        librarySelectedOrNot.setVisible(true);
        homeSelectedOrNot.setVisible(true);
        allAudiosSelectedOrNot.setVisible(false);
        searchBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        homeBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        libraryBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        allAudiosBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AllAudiosPage.fxml"));
        Controller.getController().getBasePage().centerProperty().setValue(fxmlLoader.load());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Controller.getController().getPageType().compareTo("home")==0)
        {
            homeSelectedOrNot.setVisible(false);
            librarySelectedOrNot.setVisible(true);
            searchSelectedOrNot.setVisible(true);
            allAudiosSelectedOrNot.setVisible(true);
            homeBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
            searchBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
            libraryBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
            allAudiosBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
            try {
                Controller.getController().getBasePage().centerProperty().setValue(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if(Controller.getController().getPageType().compareTo("search")==0)
        {
            searchSelectedOrNot.setVisible(false);
            librarySelectedOrNot.setVisible(true);
            homeSelectedOrNot.setVisible(true);
            allAudiosSelectedOrNot.setVisible(true);
            searchBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
            homeBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
            libraryBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
            allAudiosBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        }
        else if(Controller.getController().getPageType().compareTo("library")==0)
        {
            librarySelectedOrNot.setVisible(false);
            searchSelectedOrNot.setVisible(true);
            homeSelectedOrNot.setVisible(true);
            allAudiosSelectedOrNot.setVisible(true);
            libraryBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
            homeBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
            searchBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
            allAudiosBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        }
        else if(Controller.getController().getPageType().compareTo("allaudios")==0)
        {
            librarySelectedOrNot.setVisible(true);
            searchSelectedOrNot.setVisible(true);
            homeSelectedOrNot.setVisible(true);
            allAudiosSelectedOrNot.setVisible(false);
            libraryBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
            homeBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
            searchBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
            allAudiosBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
        }
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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SearchPage.fxml"));
        try{
            Controller.getController().getBasePage().centerProperty().setValue(fxmlLoader.load());
        }catch (Exception e){}
    }
}
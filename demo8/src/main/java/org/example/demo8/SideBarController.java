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
    private HBox allArtistsBackground;

    @FXML
    private Rectangle allArtistsSelectedOrNot;

    @FXML
    private StackPane btn_allArtists;

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
        if(!searchSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("search");
        if(!librarySelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("library");
        if(!allAudiosSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("allaudios");
        if(!allArtistsSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("allartists");
        homeSelectedOrNot.setVisible(false);
        librarySelectedOrNot.setVisible(true);
        searchSelectedOrNot.setVisible(true);
        allAudiosSelectedOrNot.setVisible(true);
        allArtistsSelectedOrNot.setVisible(true);
        homeBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
        searchBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        libraryBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        allAudiosBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        allArtistsBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        Controller.getController().getBasePage().centerProperty().setValue(fxmlLoader.load());
    }

    @FXML
    void goToLibrary(MouseEvent event) throws IOException {
        if(!searchSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("search");
        if(!homeSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("home");
        if(!allAudiosSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("allaudios");
        if(!allArtistsSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("allartists");
        librarySelectedOrNot.setVisible(false);
        searchSelectedOrNot.setVisible(true);
        homeSelectedOrNot.setVisible(true);
        allAudiosSelectedOrNot.setVisible(true);
        allArtistsSelectedOrNot.setVisible(true);
        libraryBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
        homeBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        searchBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        allAudiosBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        allArtistsBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        if(!(Controller.getController().getAccModel() instanceof ListenerModel))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("its for listeners only");
            alert.showAndWait();
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LibraryPage.fxml"));
            Controller.getController().getBasePage().centerProperty().setValue(fxmlLoader.load());
        }
    }

    @FXML
    void search(MouseEvent event) {
        if(!homeSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("home");
        if(!librarySelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("library");
        if(!allAudiosSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("allaudios");
        if(!allArtistsSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("allartists");
        searchSelectedOrNot.setVisible(false);
        librarySelectedOrNot.setVisible(true);
        homeSelectedOrNot.setVisible(true);
        allAudiosSelectedOrNot.setVisible(true);
        allArtistsSelectedOrNot.setVisible(true);
        searchBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
        homeBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        libraryBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        allAudiosBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        allArtistsBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        search();
    }

    @FXML
    void goToAllAudios(MouseEvent event) throws IOException {
        if(!searchSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("search");
        if(!librarySelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("library");
        if(!homeSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("home");
        if(!allArtistsSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("allartists");
        searchSelectedOrNot.setVisible(true);
        librarySelectedOrNot.setVisible(true);
        homeSelectedOrNot.setVisible(true);
        allAudiosSelectedOrNot.setVisible(false);
        allArtistsSelectedOrNot.setVisible(true);
        searchBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        homeBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        libraryBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        allAudiosBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
        allArtistsBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AllAudiosPage.fxml"));
        Controller.getController().getBasePage().centerProperty().setValue(fxmlLoader.load());
    }

    @FXML
    void goToAllArtists(MouseEvent event) throws IOException {
        if(!searchSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("search");
        if(!librarySelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("library");
        if(!homeSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("home");
        if(!allAudiosSelectedOrNot.isVisible())
            Controller.getController().setPreviousPage("allaudios");
        searchSelectedOrNot.setVisible(true);
        librarySelectedOrNot.setVisible(true);
        homeSelectedOrNot.setVisible(true);
        allAudiosSelectedOrNot.setVisible(true);
        allArtistsSelectedOrNot.setVisible(false);
        searchBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        homeBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        libraryBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        allAudiosBackground.setStyle("-fx-background-color : black;-fx-background-radius :10;-fx-border-radius :10");
        allArtistsBackground.setStyle("-fx-background-color : #2e2e2e;-fx-background-radius :10;-fx-border-radius :10");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AllArtistsPage.fxml"));
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
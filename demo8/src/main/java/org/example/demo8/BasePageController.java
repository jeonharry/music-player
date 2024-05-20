package org.example.demo8;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import controller.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BasePageController implements Initializable {

    private static BasePageController basePage;
    @FXML
    private BorderPane borderPane;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private AnchorPane root;
    public BasePageController() {}

    public static BasePageController getBasePage() {
        if(basePage==null)
            basePage=new BasePageController();
        return basePage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.getController().setBasePage(borderPane);
        Controller.getController().setStackPane(mainStackPane);
        FXMLLoader fxmlLoaderSideBar = new FXMLLoader(Main.class.getResource("SideBar.fxml"));
        FXMLLoader fxmlLoaderTopBar = new FXMLLoader(Main.class.getResource("TopBar.fxml"));
        FXMLLoader fxmlLoaderMainSection = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        try {
            borderPane.topProperty().setValue(fxmlLoaderTopBar.load());
            borderPane.leftProperty().setValue(fxmlLoaderSideBar.load());
            borderPane.centerProperty().setValue(fxmlLoaderMainSection.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setPlayBar() throws IOException {
        borderPane=Controller.getController().getBasePage();
        FXMLLoader fxmlLoaderPlayBar = new FXMLLoader(Main.class.getResource("PlayBar.fxml"));
        borderPane.bottomProperty().setValue(fxmlLoaderPlayBar.load());
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
}

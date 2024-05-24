package org.example.demo8;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import model.audioRelated.PlayListModel;
import model.users.AccountUserModel;
import model.users.listeners.ListenerModel;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {


    @FXML
    private MenuButton ArtistsFollowed;

    @FXML
    private Label birthDate_lbl;

    @FXML
    private Label email_lbl;

    @FXML
    private Label fullName_lbl;

    @FXML
    private VBox moreInfo;

    @FXML
    private Rectangle newPlayList_btn;

    @FXML
    private GridPane playLists;

    @FXML
    private Label subscriptionDate_lbl;

    @FXML
    private Label userName_lbl;

    @FXML
    void newPlayList(MouseEvent event) throws IOException {
        Controller.getController().getStackPane().getChildren().addLast(new FXMLLoader(Main.class.getResource("MakePlayListPage.fxml")).load());
    }

    @FXML
    void showFollowings(MouseEvent event) {

    }

    @FXML
    void showMoreInfo(MouseEvent event) {
        if(moreInfo.isVisible())
        {
            moreInfo.setVisible(false);
        }
        else
        {
            moreInfo.setVisible(true);
            fullName_lbl.setText("Full name: "+Controller.getController().getAccModel().getFullName());
            email_lbl.setText(Controller.getController().getAccModel().getEmail());
            birthDate_lbl.setText("Birth date: "+Controller.getController().getAccModel().getBirthDate().get(Calendar.YEAR)+"/"+(Controller.getController().getAccModel().getBirthDate().get(Calendar.MONTH)+1)+"/"+Controller.getController().getAccModel().getBirthDate().get(Calendar.DATE));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userName_lbl.setText(Controller.getController().getAccModel().getUserName());
        for(AccountUserModel temp:((ListenerModel)Controller.getController().getAccModel()).getFollowings())
        {
            MenuItem artist=new MenuItem(temp.getUserName());
            ArtistsFollowed.getItems().add(artist);
        }
        if(((ListenerModel)Controller.getController().getAccModel()).getPlayLists().size()>playLists.getRowCount()*playLists.getColumnCount())
        {
            while (((ListenerModel)Controller.getController().getAccModel()).getPlayLists().size()>playLists.getRowCount()*playLists.getColumnCount())
            {
                playLists.addRow(playLists.getRowCount());
            }
        }
        int counter=0;
        for(PlayListModel play:((ListenerModel) Controller.getController().getAccModel()).getPlayLists())
        {
            PlayListGeneratorController.setPlayListModel(play);
            FXMLLoader loaderAlbum=new FXMLLoader(Main.class.getResource("PlayListGenerator.fxml"));
            try {
                playLists.add(loaderAlbum.load(),0,counter);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
        for(Node node:playLists.getChildren())
            node.setOnMouseClicked(event ->{
                for(PlayListModel t:((ListenerModel) Controller.getController().getAccModel()).getPlayLists())
                    if(t!=null && t.getPlayListID()==Long.parseLong(((Label)((HBox)node).getChildren().getLast()).getText()))
                    {
                        ShowPlayListController.setPlayList(t);
                        FXMLLoader loaderAlbum=new FXMLLoader(Main.class.getResource("ShowPlayListPage.fxml"));
                        try {
                            Controller.getController().getBasePage().centerProperty().setValue(loaderAlbum.load());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            });
    }
}
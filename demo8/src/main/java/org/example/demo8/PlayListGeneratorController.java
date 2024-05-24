package org.example.demo8;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.audioRelated.PlayListModel;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayListGeneratorController implements Initializable {

    @FXML
    private Label lbl_name;

    @FXML
    private HBox playList;

    private static PlayListModel playListModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_name.setText(playListModel.getPlayListName());
        Label ID=new Label(String.valueOf(playListModel.getPlayListID()));
        playList.getChildren().addLast(ID);
        ID.setVisible(false);
    }

    public static PlayListModel getPlayListModel() {
        return playListModel;
    }

    public static void setPlayListModel(PlayListModel playListModel) {
        PlayListGeneratorController.playListModel = playListModel;
    }
}
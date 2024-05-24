package org.example.demo8;

import controller.Controller;
import controller.ListenerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Database;
import model.audioRelated.AudioModel;
import model.audioRelated.PlayListModel;
import model.users.listeners.ListenerModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowPlayListController implements Initializable {

    @FXML
    private Label ID_lbl;

    @FXML
    private GridPane audioList;

    @FXML
    private VBox moreInfo;

    @FXML
    private Label name_lbl;

    private static PlayListModel playList;

    @FXML
    void showMoreInfo(MouseEvent event) {
        if(moreInfo.isVisible())
            moreInfo.setVisible(false);
        else
            moreInfo.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.getController().setPreviousPage("library");
        name_lbl.setText(playList.getPlayListName());
        ID_lbl.setText(String.valueOf("ID: "+playList.getPlayListID()));
        int counter=0;
        for(AudioModel temp :playList.getAudios())
            if(temp!=null)
            {
                SearchResultGeneratorController.setAudioOrArtist(true);
                audioList.setVgap(5);
                SearchResultGeneratorController.setAudioModel(temp);
                FXMLLoader result=new FXMLLoader(Main.class.getResource("SearchResultGenerator.fxml"));
                try {
                    audioList.add(result.load(),0,counter++);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                audioList.setOpaqueInsets(new Insets(0,30,0,30));
            }
        Controller.setMusics(playList.getAudios());
        for(Node node:audioList.getChildren())
            node.setOnMouseClicked(event -> {
                if (((Label) ((HBox) node).getChildren().getLast()).getText().compareTo("") != 0) {
                    AudioModel previousAudio = null;
                    for (AudioModel temp : Database.getDatabase().getAllAudios())
                        if (temp != null && temp.getAudioID() == Long.parseLong(((Label) ((HBox) node).getChildren().getLast()).getText())) {
                            previousAudio = PlayBarController.getMusic();
                            if (Controller.getController().isSideBarIsVisible()) {
                                PlayBarController.getCurrentMusic().pause();
                                if (previousAudio != null && temp.getAudioID() == previousAudio.getAudioID()) {
                                    PlayMusicController.setCurrentMusic(PlayBarController.getCurrentMusic());
                                } else {
                                    PlayBarController.getCurrentMusic().stop();
                                    PlayBarController.setCurrentMusic(new MediaPlayer(new Media(temp.getLink())));
                                    PlayBarController.setMusic(temp);
                                    PlayMusicController.setCurrentMusic(new MediaPlayer(new Media(temp.getLink())));
                                    if (Controller.getController().getAccModel() instanceof ListenerModel)
                                        ListenerController.getListenerController().playAudio(String.valueOf(temp.getAudioID()));
                                }
                            } else {
                                PlayMusicController.setCurrentMusic(new MediaPlayer(new Media(temp.getLink())));
                                if (Controller.getController().getAccModel() instanceof ListenerModel)
                                    ListenerController.getListenerController().playAudio(String.valueOf(temp.getAudioID()));
                            }
                            Controller.getController().setCurrentAudio(temp);
                        }
                    try {
                        Controller.getController().setPreviousScene(Main.getStage().getScene());
                        FXMLLoader loader = new FXMLLoader(Main.class.getResource("PlayMusicPage.fxml"));
                        Scene newScene = new Scene(loader.load(), 800, 600);
                        Main.getStage().setScene(newScene);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
    }

    public static PlayListModel getPlayList() {
        return playList;
    }

    public static void setPlayList(PlayListModel playList) {
        ShowPlayListController.playList = playList;
    }
}
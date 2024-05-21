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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Database;
import model.audioRelated.AlbumModel;
import model.audioRelated.AudioModel;
import model.audioRelated.MusicModel;
import model.users.listeners.ListenerModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MusicOrPodcastGeneratorController implements Initializable {

    @FXML
    private Label albumName_lbl;

    @FXML
    private GridPane musics;

    private static AlbumModel album;

    @FXML
    private VBox vbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Label ID=new Label(String.valueOf(album.getAlbumID()));
        vbox.getChildren().addLast(ID);
        ID.setVisible(false);
        int counter=0;
        albumName_lbl.setText(album.getAlbumName());
        if(album.getMusics().size()>musics.getRowCount()*musics.getColumnCount())
        {
            while (album.getMusics().size()>musics.getRowCount()*musics.getColumnCount())
            {
                musics.addColumn(musics.getColumnCount());
            }
        }
        for(MusicModel temp:album.getMusics())
            if(temp!=null)
            {
                MusicPlayerGeneratorController.setAudio(temp);
                FXMLLoader loader=new FXMLLoader(Main.class.getResource("MusicBoxGenerator.fxml"));
                try {
                    musics.add(loader.load(),counter++,0);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                musics.setOpaqueInsets(new Insets(10,40,0,40));
            }
        for(Node node :musics.getChildren())
            node.setOnMouseClicked(event -> {
                AudioModel previousAudio = null;
                for(AudioModel temp: Database.getDatabase().getAllAudios())
                    if(temp!=null && temp.getAudioID()==Long.parseLong(((Label)((VBox)node).getChildren().getLast()).getText()))
                    {
                        previousAudio=PlayBarController.getMusic();
                        if(Controller.getController().isSideBarIsVisible())
                        {
                            PlayBarController.getCurrentMusic().pause();
                            if(previousAudio!=null && temp.getAudioID()==previousAudio.getAudioID())
                            {
                                PlayMusicController.setCurrentMusic(PlayBarController.getCurrentMusic());
                            }
                            else
                            {
                                PlayBarController.getCurrentMusic().stop();
                                PlayBarController.setCurrentMusic(new MediaPlayer(new Media(temp.getLink())));
                                PlayBarController.setMusic(temp);
                                PlayMusicController.setCurrentMusic(new MediaPlayer(new Media(temp.getLink())));
                                if(Controller.getController().getAccModel() instanceof ListenerModel)
                                    ListenerController.getListenerController().playAudio(String.valueOf(temp.getAudioID()));
                            }
                        }
                        else
                        {
                            PlayMusicController.setCurrentMusic(new MediaPlayer(new Media(temp.getLink())));
                            if(Controller.getController().getAccModel() instanceof ListenerModel)
                                ListenerController.getListenerController().playAudio(String.valueOf(temp.getAudioID()));
                        }
                        Controller.getController().setCurrentAudio(temp);
                    }
                try {
                    Controller.getController().setPreviousScene(Main.getStage().getScene());
                    FXMLLoader loader=new FXMLLoader(Main.class.getResource("PlayMusicPage.fxml"));
                    Scene newScene=new Scene(loader.load(),800,600);
                    Main.getStage().setScene(newScene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    public static AlbumModel getAlbum() {
        return album;
    }

    public static void setAlbumName(AlbumModel albumName) {
        MusicOrPodcastGeneratorController.album = albumName;
    }
}
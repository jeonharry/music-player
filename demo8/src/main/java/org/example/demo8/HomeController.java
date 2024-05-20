package org.example.demo8;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import controller.Controller;
import controller.ListenerController;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Database;
import model.audioRelated.AudioModel;
import model.users.listeners.ListenerModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private GridPane musicList;
    private static long audioID;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.getController().setPageType("home");
        if(Controller.getController().getAccModel() instanceof ListenerModel)
            Controller.setMusics(ListenerController.getListenerController().getSuggestions());
        else
            Controller.setMusics(Controller.getController().getMostLikedAudios());
        if(Controller.getMusics().size()>musicList.getRowCount()*musicList.getColumnCount())
        {
            while (Controller.getMusics().size()>musicList.getRowCount()*musicList.getColumnCount())
            {
                musicList.addRow(musicList.getRowCount());
            }
        }
        int counter=0;
        for(int i=0;i<musicList.getRowCount();++i)
            for(int j=0;j<musicList.getColumnCount();++j)
            {
                if(counter==Controller.getMusics().size())
                    break;
                MusicPlayerGeneratorController.setAudio(Controller.getMusics().get(counter));
                FXMLLoader temp=new FXMLLoader(Main.class.getResource("MusicBoxGenerator.fxml"));
                try {
                    musicList.add(temp.load(),j,i);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                musicList.setOpaqueInsets(new Insets(10,40,0,40));
                counter++;
            }
        for(Node node :musicList.getChildren())
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

    public static long getAudioID() {
        return audioID;
    }

    public static void setAudioID(long audioID) {
        HomeController.audioID = audioID;
    }
}
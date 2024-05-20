package org.example.demo8;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import controller.Controller;
import model.audioRelated.AudioModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayBarController implements Initializable {

    @FXML
    private HBox allOfPlayBar;

    @FXML
    private HBox btn_back;

    @FXML
    private HBox btn_next;

    @FXML
    private StackPane btn_playOrPause;

    @FXML
    private HBox buttons;

    @FXML
    private Circle circlePlayOrPause;

    @FXML
    private ImageView cover;

    @FXML
    private Label nameOfSong;

    @FXML
    private HBox pauseShape;

    @FXML
    private ImageView pauseTriangle;

    @FXML
    private Rectangle playRect1;

    @FXML
    private Rectangle playRect2;

    @FXML
    private HBox playShape;

    @FXML
    private Rectangle rectBack;

    @FXML
    private Rectangle rectNext;

    @FXML
    private AnchorPane root;

    @FXML
    private VBox song;

    @FXML
    private Label songArtist;

    @FXML
    private VBox songInfo;

    @FXML
    private ImageView triangleBack;

    @FXML
    private ImageView triangleNext;
    private ArrayList <AudioModel>musics= Controller.getMusics();
    private int indexOfMusics=0;
    private static MediaPlayer currentMusic;
    private static AudioModel music;

    @FXML
    void playNext(MouseEvent event) {
        int counter=0;
        indexOfMusics++;
        if(playShape.isVisible())
            currentMusic.stop();
        if(indexOfMusics>=musics.size())
        {
            while (indexOfMusics>=musics.size())
                indexOfMusics-=musics.size();
        }
        for(AudioModel temp:musics)
        {
            if(temp!=null && counter==indexOfMusics)
            {
                currentMusic=new MediaPlayer(new Media(temp.getLink()));
                if(playShape.isVisible())
                    currentMusic.play();
                nameOfSong.setText(temp.getAudioName());
                songArtist.setText(temp.getNameOfArtist());
                cover.setImage(new Image(temp.getCover()));
                break;
            }
            counter++;
        }
    }

    @FXML
    void playOrPause(MouseEvent event) {
        if(pauseShape.isVisible())
        {
            pauseShape.setVisible(false);
            playShape.setVisible(true);
            currentMusic.play();
        }
        else
        {
            pauseShape.setVisible(true);
            playShape.setVisible(false);
            currentMusic.pause();
        }
    }

    @FXML
    void playPrevious(MouseEvent event) {
        int counter=0;
        indexOfMusics--;
        if(playShape.isVisible())
            currentMusic.stop();
        if(indexOfMusics<0)
        {
            while (indexOfMusics<0)
                indexOfMusics+=musics.size();
        }
        for(AudioModel temp:musics)
        {
            if(temp!=null && counter==indexOfMusics)
            {
                currentMusic=new MediaPlayer(new Media(temp.getLink()));
                if(playShape.isVisible())
                    currentMusic.play();
                nameOfSong.setText(temp.getAudioName());
                songArtist.setText(temp.getNameOfArtist());
                cover.setImage(new Image(temp.getCover()));
                break;
            }
            counter++;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        currentMusic=new MediaPlayer(new Media(music.getLink()));
        songArtist.setText(music.getNameOfArtist());
        nameOfSong.setText(music.getAudioName());
        cover.setImage(new Image(music.getCover()));
        currentMusic.play();
        playShape.setVisible(true);
        pauseShape.setVisible(false);
    }
    public static AudioModel getMusic() {
        return music;
    }
    public static void setMusic(AudioModel music) {
        PlayBarController.music = music;
    }
    public static MediaPlayer getCurrentMusic() {
        return currentMusic;
    }
    public static void setCurrentMusic(MediaPlayer currentMusic) {
        PlayBarController.currentMusic = currentMusic;
    }
}
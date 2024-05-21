package org.example.demo8;

import controller.Controller;
import controller.ListenerController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.audioRelated.AudioModel;
import model.audioRelated.MusicModel;
import model.audioRelated.PlayListModel;
import model.audioRelated.PodcastModel;
import model.users.listeners.ListenerModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class PlayMusicController implements Initializable {


    @FXML
    private Slider slider;

    @FXML
    private Label lyricCaption1;

    @FXML
    private VBox moreInfo;

    @FXML
    private Label ID;

    @FXML
    private Label likeAmount;

    @FXML
    private Label releaseDate;

    @FXML
    private Label playAmount;

    @FXML
    private Label artistNameOfSong;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private HBox btn_back;

    @FXML
    private HBox btn_next;

    @FXML
    private StackPane btn_playOrPause;

    @FXML
    private HBox buttons;

    @FXML
    private MenuButton choosePlayListMenu;

    @FXML
    private Circle circlePlayOrPause;

    @FXML
    private StackPane close_btn;

    @FXML
    private ImageView cover;

    @FXML
    private ImageView liked;

    @FXML
    private Label lyricCaption;

    @FXML
    private ImageView notLiked;

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
    private ImageView showLyricOrCaption;

    @FXML
    private Label songName;

    @FXML
    private ImageView triangleBack;

    @FXML
    private ScrollPane lyricOrCaption;

    @FXML
    private ImageView triangleNext;

    @FXML
    private Label time_lbl;

    @FXML
    private Label totaltime_lbl;

    private ArrayList<AudioModel> musics= Controller.getMusics();
    private int indexOfMusics=0;
    private static MediaPlayer currentMusic;

    @FXML
    void closePage(MouseEvent event) throws IOException {
        currentMusic.pause();
        Main.getStage().setScene(Controller.getController().getPreviousScene());
        PlayBarController.setCurrentMusic(currentMusic);
        PlayBarController.setMusic(Controller.getController().getCurrentAudio());
        BasePageController.getBasePage().setPlayBar();
        Controller.getController().setSideBarIsVisible(true);
    }

    @FXML
    void like(MouseEvent event) {
        if(liked.isVisible())
        {
            liked.setVisible(false);
            notLiked.setVisible(true);
            if(Controller.getController().getAccModel() instanceof ListenerModel)
            {
                ListenerController.getListenerController().disLikeAudio(String.valueOf(Controller.getController().getCurrentAudio().getAudioID()));
            }
        }
        else
        {
            liked.setVisible(true);
            notLiked.setVisible(false);
            if(Controller.getController().getAccModel() instanceof ListenerModel)
            {
                ListenerController.getListenerController().likeAudio(String.valueOf(Controller.getController().getCurrentAudio().getAudioID()));
            }
        }
    }

    @FXML
    void playNext(MouseEvent event) {
        moreInfo.setVisible(false);
        lyricOrCaption.setVisible(false);
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
                Controller.getController().setCurrentAudio(temp);
                currentMusic=new MediaPlayer(new Media(temp.getLink()));
                if(playShape.isVisible())
                {
                    currentMusic.currentTimeProperty().addListener((obs,oldTime,newTime)->{
                        updateMediaProgress();
                    });
                    currentMusic.setOnReady(()->{
                        slider.setMax(currentMusic.getMedia().getDuration().toSeconds());
                        updateMediaProgress();
                    });
                    slider.valueProperty().addListener((obs,oldValue,newValue)->{
                        if(slider.isValueChanging())
                        {
                            currentMusic.seek(Duration.seconds(newValue.doubleValue()));
                        }
                    });
                    currentMusic.play();
                }
                songName.setText(temp.getAudioName());
                artistNameOfSong.setText(temp.getNameOfArtist());
                cover.setImage(new Image(temp.getCover()));
                backgroundImage.setImage(new Image(temp.getCover()));
                if(Controller.getController().getAccModel() instanceof ListenerModel)
                {
                    boolean likedOrNot=false;
                    for(AudioModel tempAudio:((ListenerModel) Controller.getController().getAccModel()).getLikedAudios())
                        if(tempAudio!=null && tempAudio.getAudioID()==temp.getAudioID())
                        {
                            liked.setVisible(true);
                            notLiked.setVisible(false);
                            likedOrNot=true;
                        }
                    if(!likedOrNot)
                    {
                        liked.setVisible(false);
                        notLiked.setVisible(true);
                    }
                }
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
        moreInfo.setVisible(false);
        lyricOrCaption.setVisible(false);
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
                Controller.getController().setCurrentAudio(temp);
                currentMusic=new MediaPlayer(new Media(temp.getLink()));
                if(playShape.isVisible())
                {
                    currentMusic.currentTimeProperty().addListener((obs,oldTime,newTime)->{
                        updateMediaProgress();
                    });
                    currentMusic.setOnReady(()->{
                        slider.setMax(currentMusic.getMedia().getDuration().toSeconds());
                        updateMediaProgress();
                    });
                    slider.valueProperty().addListener((obs,oldValue,newValue)->{
                        if(slider.isValueChanging())
                        {
                            currentMusic.seek(Duration.seconds(newValue.doubleValue()));
                        }
                    });
                    currentMusic.play();
                }
                songName.setText(temp.getAudioName());
                artistNameOfSong.setText(temp.getNameOfArtist());
                cover.setImage(new Image(temp.getCover()));
                backgroundImage.setImage(new Image(temp.getCover()));
                if(Controller.getController().getAccModel() instanceof ListenerModel)
                {
                    boolean likedOrNot=false;
                    for(AudioModel tempAudio:((ListenerModel) Controller.getController().getAccModel()).getLikedAudios())
                        if(tempAudio!=null && tempAudio.getAudioID()==temp.getAudioID())
                        {
                            liked.setVisible(true);
                            notLiked.setVisible(false);
                            likedOrNot=true;
                        }
                    if(!likedOrNot)
                    {
                        liked.setVisible(false);
                        notLiked.setVisible(true);
                    }
                }
                break;
            }
            counter++;
        }
    }

    @FXML
    void showMoreInfo(MouseEvent event) {
        if(moreInfo.isVisible())
        {
            moreInfo.setVisible(false);
        }
        else if(lyricOrCaption.isVisible())
        {
            lyricOrCaption.setVisible(false);
            moreInfo.setVisible(true);
            if(Controller.getController().getCurrentAudio() instanceof MusicModel)
                lyricCaption.setText("Lyric");
            else
                lyricCaption.setText("Caption");
        }
        else
            moreInfo.setVisible(true);
    }
    @FXML
    void showLyricOrCaption(MouseEvent event) {
        moreInfo.setVisible(false);
        lyricOrCaption.setVisible(true);
        if(Controller.getController().getCurrentAudio() instanceof MusicModel)
            lyricCaption1.setText(((MusicModel) Controller.getController().getCurrentAudio()).getLyric());
        else if(Controller.getController().getCurrentAudio() instanceof PodcastModel)
            lyricCaption1.setText(((PodcastModel) Controller.getController().getCurrentAudio()).getCaption());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moreInfo.setVisible(false);
        lyricOrCaption.setVisible(false);
        artistNameOfSong.setText(Controller.getController().getCurrentAudio().getNameOfArtist());
        songName.setText(Controller.getController().getCurrentAudio().getAudioName());
        cover.setImage(new Image(Controller.getController().getCurrentAudio().getCover()));
        backgroundImage.setImage(new Image(Controller.getController().getCurrentAudio().getCover()));
        ID.setText("ID-"+String.valueOf(Controller.getController().getCurrentAudio().getAudioID()));
        releaseDate.setText("Release date: "+Controller.getController().getCurrentAudio().getReleaseDate().get(Calendar.YEAR)+"/"+(Controller.getController().getCurrentAudio().getReleaseDate().get(Calendar.MONTH)+1)+"/"+Controller.getController().getCurrentAudio().getReleaseDate().get(Calendar.DATE));
        playAmount.setText("plays: "+Controller.getController().getCurrentAudio().getPlayAmount());
        likeAmount.setText("likes: "+Controller.getController().getCurrentAudio().getLikeAmount());
        if(Controller.getController().getCurrentAudio() instanceof MusicModel)
            lyricCaption.setText("Lyric");
        else
            lyricCaption.setText("Caption");
        currentMusic.currentTimeProperty().addListener((obs,oldTime,newTime)->{
            updateMediaProgress();
        });
        currentMusic.setOnReady(()->{
            slider.setMax(currentMusic.getMedia().getDuration().toSeconds());
            updateMediaProgress();
        });
        slider.valueProperty().addListener((obs,oldValue,newValue)->{
            if(slider.isValueChanging())
            {
                currentMusic.seek(Duration.seconds(newValue.doubleValue()));
            }
        });
        currentMusic.play();
        playShape.setVisible(true);
        pauseShape.setVisible(false);
        if(Controller.getController().getAccModel() instanceof ListenerModel)
        {
            boolean likedOrNot=false;
            for(AudioModel temp:((ListenerModel) Controller.getController().getAccModel()).getLikedAudios())
                if(temp!=null && temp.getAudioID()==Controller.getController().getCurrentAudio().getAudioID())
                {
                    liked.setVisible(true);
                    notLiked.setVisible(false);
                    likedOrNot=true;
                }
            if(!likedOrNot)
            {
                liked.setVisible(false);
                notLiked.setVisible(true);
            }
        }
        if (Controller.getController().getAccModel() instanceof ListenerModel) {
            for (PlayListModel temp : ((ListenerModel) Controller.getController().getAccModel()).getPlayLists())
                if (temp != null) {
                    MenuItem item = new MenuItem(temp.getPlayListName());
                    choosePlayListMenu.getItems().add(item);
                    item.setOnAction(event -> {
                        try {
                            ListenerController.getListenerController().addToPlayList(item.getText(), String.valueOf(Controller.getController().getCurrentAudio().getAudioID()));
                        } catch (Exception e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText(e.getMessage());
                            alert.showAndWait();
                        }
                    });
                }
        }
    }

    public static MediaPlayer getCurrentMusic() {
        return currentMusic;
    }

    public static void setCurrentMusic(MediaPlayer currentMusic) {
        PlayMusicController.currentMusic = currentMusic;
    }
    public void updateMediaProgress()
    {
        Duration currentTime=currentMusic.getCurrentTime();
        Duration totalTime=currentMusic.getMedia().getDuration();
        long totalSeconds= (long) (totalTime.toSeconds()%60);
        long totalMins= (long) (totalTime.toSeconds()/60);
        long currentSeconds= (long) (currentTime.toSeconds()%60);
        long currentMins= (long) (currentTime.toSeconds()/60);
        time_lbl.setText(currentMins+":"+currentSeconds);
        totaltime_lbl.setText(totalMins+":"+totalSeconds);
        slider.setValue(currentTime.toSeconds());
    }
}

package org.example.demo8;

import controller.Controller;
import controller.ListenerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import model.Database;
import model.audioRelated.AlbumModel;
import model.audioRelated.AudioModel;
import model.audioRelated.MusicModel;
import model.audioRelated.PodcastModel;
import model.users.artists.PodcasterModel;
import model.users.artists.SingerModel;
import model.users.listeners.ListenerModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ArtistInfoController implements Initializable {

    @FXML
    private VBox Podcasts;

    @FXML
    private GridPane albumList;

    @FXML
    private VBox albums;

    @FXML
    private GridPane podcastsList;

    @FXML
    private Label birthDate_lbl;

    @FXML
    private Label email_lbl;

    @FXML
    private Rectangle followBackground;

    @FXML
    private Label follow_lbl;

    @FXML
    private Rectangle followedBackground;

    @FXML
    private Label followed_lbl;

    @FXML
    private Label fullName_lbl;

    @FXML
    private VBox moreInfo;

    @FXML
    private Rectangle report_btn;

    @FXML
    private Label userName_lbl;

    @FXML
    void followOrUnfollow(MouseEvent event) {
        if(!(Controller.getController().getAccModel() instanceof ListenerModel))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You need to login first");
            alert.showAndWait();
        }
        else
        {
            if(followBackground.isVisible())
            {
                followBackground.setVisible(false);
                followedBackground.setVisible(true);
                follow_lbl.setVisible(false);
                followed_lbl.setVisible(true);
                if(Controller.getController().getAccModel() instanceof ListenerModel)
                    ListenerController.getListenerController().followArtist(userName_lbl.getText());
            }
            else
            {
                followedBackground.setVisible(false);
                followBackground.setVisible(true);
                follow_lbl.setVisible(true);
                followed_lbl.setVisible(false);
                if(Controller.getController().getAccModel() instanceof ListenerModel)
                    ListenerController.getListenerController().unfollowArtist(userName_lbl.getText());
            }
        }
    }

    @FXML
    void report(MouseEvent event) throws IOException {
        if(!(Controller.getController().getAccModel() instanceof ListenerModel))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You need to login first");
            alert.showAndWait();
        }
        else
        {
            ReportPageController.setReportedArtist(userName_lbl.getText());
            FXMLLoader loader=new FXMLLoader(Main.class.getResource("ReportPage.fxml"));
            Controller.getController().getStackPane().getChildren().addLast(loader.load());
        }
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
            fullName_lbl.setText("Full name: "+Controller.getController().getSelectedArtist().getFullName());
            email_lbl.setText(Controller.getController().getSelectedArtist().getEmail());
            birthDate_lbl.setText("Birth date: "+Controller.getController().getSelectedArtist().getBirthDate().get(Calendar.YEAR)+"/"+(Controller.getController().getSelectedArtist().getBirthDate().get(Calendar.MONTH)+1)+"/"+Controller.getController().getSelectedArtist().getBirthDate().get(Calendar.DATE));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int counter=0;
        userName_lbl.setText(Controller.getController().getSelectedArtist().getUserName());
        if(Controller.getController().getSelectedArtist() instanceof PodcasterModel)
        {
            if(((PodcasterModel) Controller.getController().getSelectedArtist()).getPodcasts().size()>podcastsList.getRowCount()*podcastsList.getColumnCount())
            {
                while (((PodcasterModel) Controller.getController().getSelectedArtist()).getPodcasts().size()>podcastsList.getRowCount()*podcastsList.getColumnCount())
                {
                    podcastsList.addColumn(podcastsList.getColumnCount());
                }
            }
            Podcasts.setVisible(true);
            albums.setVisible(false);
            ArrayList <AudioModel> audios=new ArrayList<>();
            for(PodcastModel temp:((PodcasterModel) Controller.getController().getSelectedArtist()).getPodcasts())
                if(temp!=null)
                {
                    MusicPlayerGeneratorController.setAudio(temp);
                    FXMLLoader loader=new FXMLLoader(Main.class.getResource("MusicBoxGenerator.fxml"));
                    try {
                        podcastsList.add(loader.load(),counter++,0);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    podcastsList.setOpaqueInsets(new Insets(10,40,0,40));
                    audios.add(temp);
                }
            Controller.setMusics(audios);
            for(Node node :podcastsList.getChildren())
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
        else
        {
            Podcasts.setVisible(false);
            albums.setVisible(true);
            if(((SingerModel) Controller.getController().getSelectedArtist()).getAlbums().size()>albumList.getRowCount()*albumList.getColumnCount())
            {
                while (((SingerModel) Controller.getController().getSelectedArtist()).getAlbums().size()>albumList.getRowCount()*albumList.getColumnCount())
                {
                    albumList.addRow(podcastsList.getRowCount());
                }
            }
            for (int i = 0; i < albumList.getChildren().size(); i++)
            {
                albumList.getChildren().get(i).prefHeight(250);
            }
            int counter2=0;
            for(AlbumModel album:((SingerModel) Controller.getController().getSelectedArtist()).getAlbums())
            {
                MusicOrPodcastGeneratorController.setAlbumName(album);
                FXMLLoader loaderAlbum=new FXMLLoader(Main.class.getResource("MusicOrPodcastGenerator.fxml"));
                try {
                    albumList.add(loaderAlbum.load(),0,counter2);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                counter2++;
            }
            for(Node node:albumList.getChildren())
                node.setOnMouseClicked(event ->{
                    ArrayList <AudioModel> audios=new ArrayList<>();
                    for(AlbumModel t:((SingerModel)Controller.getController().getSelectedArtist()).getAlbums())
                        if(t!=null && t.getAlbumID()==Long.parseLong(((Label)((VBox)node).getChildren().getLast()).getText()))
                        {
                            for(MusicModel musicT:t.getMusics())
                                audios.add(musicT);
                        }
                    Controller.setMusics(audios);
                });
        }
    }
}
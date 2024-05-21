package org.example.demo8;

import controller.ListenerController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import controller.Controller;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Database;
import model.audioRelated.AudioModel;
import model.users.AccountUserModel;
import model.users.artists.ArtistModel;
import model.users.listeners.ListenerModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private StackPane blackSearchBox;

    @FXML
    private GridPane resultList;

    @FXML
    private TextField searchBox;

    @FXML
    private TextField searchBoxBlack;

    @FXML
    private ImageView searchIcon;

    @FXML
    private ImageView searchIcon1;

    @FXML
    private StackPane whiteSearchBox;

    @FXML
    private Label noResult;

    @FXML
    void changeColorToBlack(MouseEvent event) {
        whiteSearchBox.setVisible(false);
        blackSearchBox.setVisible(true);
    }
    @FXML
    void changeColorToBlackType(KeyEvent event) {
        whiteSearchBox.setVisible(false);
        blackSearchBox.setVisible(true);
        searchBoxBlack.appendText(searchBox.getText());
    }

    @FXML
    void search(MouseEvent event) throws IOException {
        doSearch();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        noResult.setVisible(false);
        scrollPane.setVisible(true);
        whiteSearchBox.setVisible(true);
        blackSearchBox.setVisible(false);
        Controller.getController().setPageType("search");
        blackSearchBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    try {
                        doSearch();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        if(Controller.getController().getBasePage().getBottom()==null)
        {
            VBox background=new VBox(); background.setStyle("-fx-background-color: black;-fx-pref-height: 110");
            Controller.getController().getBasePage().bottomProperty().setValue(background);
        }
    }
    public void doSearch() throws IOException {
        resultList.getChildren().clear();
        int counter=0;
        ArrayList[] answer= Controller.getController().search(searchBoxBlack.getText());
        if(answer==null)
        {
            resultList.add(noResult,0,2);
            noResult.setVisible(true);
            resultList.setOpaqueInsets(new Insets(0,210,0,220));
        }
        else
        {
            if(answer[0].size()+answer[1].size()>resultList.getColumnCount()* resultList.getRowCount())
                while(answer[0].size()+answer[1].size()>resultList.getColumnCount()* resultList.getRowCount())
                    resultList.addRow(0);
            for(AudioModel temp :((ArrayList <AudioModel>)answer[0]))
                if(temp!=null)
                {
                    SearchResultGeneratorController.setAudioOrArtist(true);
                    resultList.setVgap(5);
                    SearchResultGeneratorController.setAudioModel(temp);
                    FXMLLoader result=new FXMLLoader(Main.class.getResource("SearchResultGenerator.fxml"));
                    resultList.add(result.load(),0,counter++);
                    resultList.setOpaqueInsets(new Insets(0,25,0,20));
                }
            for(AccountUserModel temp: ((ArrayList <AccountUserModel>)answer[1]))
                if(temp!=null)
                {
                    SearchResultGeneratorController.setAudioOrArtist(false);
                    resultList.setVgap(5);
                    SearchResultGeneratorController.setAccount(temp);
                    FXMLLoader result=new FXMLLoader(Main.class.getResource("SearchResultGenerator.fxml"));
                    resultList.add(result.load(),0,counter++);
                    resultList.setOpaqueInsets(new Insets(0,25,0,20));
                }
            for(Node node:resultList.getChildren())
                node.setOnMouseClicked(event ->{
                    if(((Label)((HBox)node).getChildren().getLast()).getText().compareTo("")!=0)
                    {
                        AudioModel previousAudio = null;
                        for(AudioModel temp: Database.getDatabase().getAllAudios())
                            if(temp!=null && temp.getAudioID()==Long.parseLong(((Label)((HBox)node).getChildren().getLast()).getText()))
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
                    }
                    else
                    {
                        for(AccountUserModel acc:Database.getDatabase().getAllUsers())
                            if(acc!=null && acc.getUserName().compareTo(((Label)((HBox)node).getChildren().get(2)).getText())==0)
                                Controller.getController().setSelectedArtist((ArtistModel) acc);
                        FXMLLoader loader=new FXMLLoader(Main.class.getResource("ArtistInfoPage.fxml"));
                        try {
                            Controller.getController().getBasePage().centerProperty().setValue(loader.load());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        }
    }
}
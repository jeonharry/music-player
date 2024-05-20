package org.example.demo8;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.audioRelated.AudioModel;

import java.net.URL;
import java.util.ResourceBundle;

public class MusicPlayerGeneratorController implements Initializable {

    @FXML
    private Label artistName;

    @FXML
    private Rectangle cover;
    @FXML
    private Label songName;

    @FXML
    private VBox vbox;
    private static AudioModel audio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        songName.setText(audio.getAudioName());
        artistName.setText(audio.getNameOfArtist());
        ImagePattern pattern=new ImagePattern(new Image(audio.getCover()));
        cover.setFill(pattern);
        Label ID=new Label(String.valueOf(audio.getAudioID()));
        vbox.getChildren().addLast(ID);
        ID.setVisible(false);
    }


    public static AudioModel getAudio() {
        return audio;
    }

    public static void setAudio(AudioModel audio) {
        MusicPlayerGeneratorController.audio = audio;
    }

}
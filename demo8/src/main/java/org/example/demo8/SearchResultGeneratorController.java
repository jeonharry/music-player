package org.example.demo8;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.example.demo8.model.audioRelated.AudioModel;
import org.example.demo8.model.users.AccountUserModel;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchResultGeneratorController implements Initializable {

    @FXML
    private ImageView pic;

    @FXML
    private HBox result;

    @FXML
    private Label lbl_name;
    private static AudioModel audioModel;
    private static AccountUserModel account;
    private static boolean audioOrArtist=true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(audioOrArtist)
        {
            pic.setImage(new Image(audioModel.getCover()));
            lbl_name.setText(audioModel.getAudioName());
        }
        else
        {
            pic.setImage(new Image(Main.class.getResourceAsStream("images/images (5).png")));
            lbl_name.setText(account.getFullName());
        }
    }

    public static AudioModel getAudioModel() {
        return audioModel;
    }

    public static void setAudioModel(AudioModel audioModel) {
        SearchResultGeneratorController.audioModel = audioModel;
    }

    public static boolean isAudioOrArtist() {
        return audioOrArtist;
    }

    public static void setAudioOrArtist(boolean audioOrArtist) {
        SearchResultGeneratorController.audioOrArtist = audioOrArtist;
    }

    public static void setAccount(AccountUserModel account) {
        SearchResultGeneratorController.account = account;
    }

    public static AccountUserModel getAccount() {
        return account;
    }
}
package org.example.demo8;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.users.artists.ArtistModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ArtistBoxGeneratorController implements Initializable {

    @FXML
    private Label artistName;

    @FXML
    private Rectangle cover;

    @FXML
    private VBox vbox;

    private static ArtistModel acc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        artistName.setText(acc.getUserName());
        ImagePattern pattern=new ImagePattern(new Image(Main.class.getResource("images/images(5).png").toExternalForm()));
        cover.setFill(pattern);
    }

    public static ArtistModel getAcc() {
        return acc;
    }

    public static void setAcc(ArtistModel acc) {
        ArtistBoxGeneratorController.acc = acc;
    }
}

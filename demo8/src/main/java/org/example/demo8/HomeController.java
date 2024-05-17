package org.example.demo8;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.example.demo8.controller.Controller;
import org.example.demo8.controller.ListenerController;
import org.example.demo8.model.Database;
import org.example.demo8.model.audioRelated.AudioModel;
import org.example.demo8.model.users.listeners.ListenerModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private GridPane musicList;

    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                ImageView cover=new ImageView(new Image(Controller.getMusics().get(counter).getCover())); cover.setFitHeight(90); cover.setFitWidth(90); cover.setEffect(new Lighting(new Light.Distant()));
                Label audioName=new Label(Controller.getMusics().get(counter).getAudioName()+" ("+Controller.getMusics().get(counter).getAudioID()+")"); audioName.setTextFill(Color.WHITE); audioName.setFont(Font.font("System", FontWeight.BOLD, 15));
                Label artist=new Label(Controller.getMusics().get(counter).getNameOfArtist()); artist.setTextFill(Paint.valueOf("#e4e4e4")); artist.setFont(Font.font("System", FontPosture.REGULAR, 12));
                VBox vbox=new VBox(cover,audioName,artist);
                vbox.setStyle("-fx-pref-height:80; -fx-pref-width: 80;-fx-alignment: center; -fx-border-radius: 20;");
                vbox.setSpacing(5);
                musicList.add(vbox,j,i);
                counter++;
            }
        for(Node node :musicList.getChildren())
            node.setOnMouseClicked(event -> {
                for(AudioModel temp: Database.getDatabase().getAllAudios())
                    if(temp!=null && (temp.getAudioName()+" ("+temp.getAudioID()+")").compareTo(((Label)(((VBox)node).getChildren().get(1))).getText())==0)
                        PlayBarController.setMusic(temp);
                try {
                    BasePageController.getBasePage().setPlayBar();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
    }

}
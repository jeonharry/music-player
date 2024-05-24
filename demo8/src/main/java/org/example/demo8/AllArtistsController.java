package org.example.demo8;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Database;
import model.users.artists.ArtistModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllArtistsController implements Initializable {

    @FXML
    private GridPane artistList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.getController().setPageType("allartists");
        Controller.setMusics(Database.getDatabase().getAllAudios());
        if(Controller.getController().getArtists().size()>artistList.getRowCount()*artistList.getColumnCount())
        {
            while (Controller.getController().getArtists().size()>artistList.getRowCount()*artistList.getColumnCount())
            {
                artistList.addRow(artistList.getRowCount());
            }
        }
        ArrayList<ArtistModel> artists=Controller.getController().getArtists();
        int counter=0;
        for(int i=0;i<artistList.getRowCount();++i)
            for(int j=0;j<artistList.getColumnCount();++j)
            {
                if(counter==artists.size())
                    break;
                ArtistBoxGeneratorController.setAcc(artists.get(counter));
                FXMLLoader temp=new FXMLLoader(Main.class.getResource("ArtistBoxGenerator.fxml"));
                try {
                    artistList.add(temp.load(),j,i);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                artistList.setOpaqueInsets(new Insets(10,40,0,40));
                counter++;
            }
        for(Node node :artistList.getChildren())
            node.setOnMouseClicked(event -> {
                for(ArtistModel temp: Controller.getController().getArtists())
                    if(temp!=null && temp.getUserName().compareTo(((Label)((VBox)node).getChildren().getLast()).getText())==0)
                    {
                        Controller.getController().setSelectedArtist(temp);
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

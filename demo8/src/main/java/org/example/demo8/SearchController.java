package org.example.demo8;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
import org.example.demo8.controller.Controller;
import org.example.demo8.model.Database;
import org.example.demo8.model.audioRelated.AudioModel;
import org.example.demo8.model.users.AccountUserModel;
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
    }
    public void doSearch() throws IOException {
        resultList.getChildren().clear();
        int counter=0;
        ArrayList[] answer= Controller.getController().search(searchBoxBlack.getText());
        if(answer==null)
            noResult.setVisible(true);
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
        }
    }
}
package org.example.demo8;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import controller.Controller;
import controller.ListenerController;
import model.Database;
import model.audioRelated.Genre;

import java.io.IOException;
import java.util.ArrayList;

public class GenresPageController {

    @FXML
    private StackPane close_btn;

    @FXML
    private StackPane countryNotSelected;

    @FXML
    private StackPane countrySelected;

    @FXML
    private StackPane hipHopNotSelected;

    @FXML
    private StackPane hipHopSelected;

    @FXML
    private StackPane historyNotSelected;

    @FXML
    private StackPane historySelected;

    @FXML
    private StackPane interviewNotSelected;

    @FXML
    private StackPane interviewSelected;

    @FXML
    private StackPane jazzNotSelected;

    @FXML
    private StackPane jazzSelected;

    @FXML
    private StackPane popNotSelected;

    @FXML
    private StackPane popSelected;

    @FXML
    private StackPane rockNotSelected;

    @FXML
    private StackPane rockSelected;

    @FXML
    private AnchorPane root;

    @FXML
    private StackPane societyNotSelected;

    @FXML
    private StackPane societySelected;

    @FXML
    private StackPane submit_btn;

    @FXML
    private StackPane trueCrimeNotSelected;

    @FXML
    private StackPane trueCrimeSelected;

    @FXML
    private Label error;

    private ArrayList <Genre> genres=new ArrayList<>();

    @FXML
    void closePage(MouseEvent event) throws IOException {
        Controller.getController().getStackPane().getChildren().removeLast();
        Controller.getController().getStackPane().getChildren().add(Controller.getController().getNode());
        Database.getDatabase().getAllUsers().remove(LoginSignupPage.getSignedupAcc());
    }

    @FXML
    void selectCountry(MouseEvent event) {
        error.setVisible(false);
        if(countryNotSelected.isVisible())
        {
            if(genres.size()<4)
            {
                countryNotSelected.setVisible(false);
                countrySelected.setVisible(true);
                genres.add(Genre.COUNTRY);
            }
            else
                error.setVisible(true);
        }
        else
        {
            countrySelected.setVisible(false);
            countryNotSelected.setVisible(true);
            genres.remove(Genre.COUNTRY);
        }
    }

    @FXML
    void selectHipHop(MouseEvent event) {
        error.setVisible(false);
        if(hipHopNotSelected.isVisible())
        {
            if(genres.size()<4)
            {
                hipHopNotSelected.setVisible(false);
                hipHopSelected.setVisible(true);
                genres.add(Genre.HIPHOP);
            }
            else
                error.setVisible(true);
        }
        else
        {
            hipHopSelected.setVisible(false);
            hipHopNotSelected.setVisible(true);
            genres.remove(Genre.HIPHOP);
        }
    }

    @FXML
    void selectHistory(MouseEvent event) {
        error.setVisible(false);
        if(historyNotSelected.isVisible())
        {
            if(genres.size()<4)
            {
                historyNotSelected.setVisible(false);
                historySelected.setVisible(true);
                genres.add(Genre.HISTORY);
            }
            else
                error.setVisible(true);
        }
        else
        {
            historySelected.setVisible(false);
            historyNotSelected.setVisible(true);
            genres.remove(Genre.HISTORY);
        }
    }

    @FXML
    void selectInterview(MouseEvent event) {
        error.setVisible(false);
        if(interviewNotSelected.isVisible())
        {
            if(genres.size()<4)
            {
                interviewNotSelected.setVisible(false);
                interviewSelected.setVisible(true);
                genres.add(Genre.INTERVIEW);
            }
            else
                error.setVisible(true);
        }
        else
        {
            interviewSelected.setVisible(false);
            interviewNotSelected.setVisible(true);
            genres.remove(Genre.INTERVIEW);
        }
    }

    @FXML
    void selectJazz(MouseEvent event) {
        error.setVisible(false);
        if(jazzNotSelected.isVisible())
        {
            if(genres.size()<4)
            {
                jazzNotSelected.setVisible(false);
                jazzSelected.setVisible(true);
                genres.add(Genre.JAZZ);
            }
            else
                error.setVisible(true);
        }
        else
        {
            jazzSelected.setVisible(false);
            jazzNotSelected.setVisible(true);
            genres.remove(Genre.JAZZ);
        }
    }

    @FXML
    void selectPop(MouseEvent event) {
        error.setVisible(false);
        if(popNotSelected.isVisible())
        {
            if(genres.size()<4)
            {
                popNotSelected.setVisible(false);
                popSelected.setVisible(true);
                genres.add(Genre.POP);
            }
            else
                error.setVisible(true);
        }
        else
        {
            popSelected.setVisible(false);
            popNotSelected.setVisible(true);
            genres.remove(Genre.POP);
        }
    }

    @FXML
    void selectRock(MouseEvent event) {
        error.setVisible(false);
        if(rockNotSelected.isVisible())
        {
            if(genres.size()<4)
            {
                rockNotSelected.setVisible(false);
                rockSelected.setVisible(true);
                genres.add(Genre.ROCK);
            }
            else
                error.setVisible(true);
        }
        else
        {
            rockSelected.setVisible(false);
            rockNotSelected.setVisible(true);
            genres.remove(Genre.ROCK);
        }
    }

    @FXML
    void selectSociety(MouseEvent event) {
        error.setVisible(false);
        if(societyNotSelected.isVisible())
        {
            if(genres.size()<4)
            {
                societyNotSelected.setVisible(false);
                societySelected.setVisible(true);
                genres.add(Genre.SOCIETY);
            }
            else
                error.setVisible(true);
        }
        else
        {
            societySelected.setVisible(false);
            societyNotSelected.setVisible(true);
            genres.remove(Genre.SOCIETY);
        }
    }

    @FXML
    void selectTrueCrime(MouseEvent event) {
        error.setVisible(false);
        if(trueCrimeNotSelected.isVisible())
        {
            if(genres.size()<4)
            {
                trueCrimeNotSelected.setVisible(false);
                trueCrimeSelected.setVisible(true);
                genres.add(Genre.TRUE_CRIME);
            }
            else
                error.setVisible(true);
        }
        else
        {
            trueCrimeSelected.setVisible(false);
            trueCrimeNotSelected.setVisible(true);
            genres.remove(Genre.TRUE_CRIME);
        }
    }

    @FXML
    void submit(MouseEvent event) {
        if(genres.size()<=4)
            ListenerController.getListenerController().getListener().setFavGenres(genres);
        Controller.getController().getStackPane().getChildren().removeLast();
    }

}
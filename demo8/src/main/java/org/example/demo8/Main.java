package org.example.demo8;

import controller.ArtistController;
import controller.Controller;
import controller.ListenerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Database;
import model.audioRelated.Genre;
import model.audioRelated.MusicModel;
import model.audioRelated.PlayListModel;
import model.audioRelated.PodcastModel;
import model.users.AdminModel;
import model.users.artists.PodcasterModel;
import model.users.artists.SingerModel;
import model.users.listeners.FreeListenerModel;
import model.users.listeners.ListenerModel;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        Main.stage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("BasePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("music player");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        AdminModel admin=AdminModel.getAdmin("MK","jk1997","Marzieh Karami","marzieh666@gmail.com","09132082206","2005/2/1");
        MusicModel music=new MusicModel("lkj","kjhhh", Genre.POP,"https://ts9.tarafdari.com/contents/user749487/content-sound/lana_del_rey_-_radio.mp3",Main.class.getResource("images/pic3.jpg").toExternalForm(),"lkk");
        music.setAudioID(123548);
        Database.getDatabase().getAllAudios().add(music);
        MusicModel music1=new MusicModel("joon","aloo", Genre.POP,"https://ts2.tarafdari.com/contents/user6984/content-sound/09.summertime_sadness.mp3",Main.class.getResource("images/pic1.jpg").toExternalForm(),"lkk");
        music1.setAudioID(1516);
        Database.getDatabase().getAllAudios().add(music1);
//        Database.getDatabase().getAllAudios().add(new MusicModel("joon","aloo", Genre.POP,"https://ts2.tarafdari.com/contents/user6984/content-sound/09.summertime_sadness.mp3",Main.class.getResource("images/pic1.jpg").toExternalForm(),"lkhkj;;k"));
//        Database.getDatabase().getAllAudios().add(new MusicModel("joon","aloo", Genre.POP,"https://ts2.tarafdari.com/contents/user6984/content-sound/09.summertime_sadness.mp3",Main.class.getResource("images/pic1.jpg").toExternalForm(),"lkk"));
//        Database.getDatabase().getAllAudios().add(new MusicModel("joon","aloo", Genre.POP,"https://ts2.tarafdari.com/contents/user6984/content-sound/09.summertime_sadness.mp3",Main.class.getResource("images/pic1.jpg").toExternalForm(),"lkk"));
//        Database.getDatabase().getAllAudios().add(new MusicModel("joon","aloo", Genre.POP,"https://ts2.tarafdari.com/contents/user6984/content-sound/09.summertime_sadness.mp3",Main.class.getResource("images/pic1.jpg").toExternalForm(),"lkk"));
        SingerModel podcaster=new SingerModel("kjcbndc","cvjhcv","aloo","jbjhbc","09132082206","2004/5/8","sjkncjkdcb");
        ArtistController.getArtistController().setArtist(podcaster);
        ArtistController.getArtistController().makeAlbum("hhgf");
        ArtistController.getArtistController().makeAlbum("ooo");
        podcaster.getAlbums().getFirst().getMusics().add(music);
        podcaster.getAlbums().get(1).getMusics().add(music1);
        Database.getDatabase().getAllUsers().add(podcaster);
        PodcastModel o=new PodcastModel("kjh","aloo",Genre.POP,"https://ts2.tarafdari.com/contents/user6984/content-sound/09.summertime_sadness.mp3",Main.class.getResource("images/pic1.jpg").toExternalForm(),"sbvj");
        PodcastModel o2=new PodcastModel("rer","aloo",Genre.POP,"https://ts9.tarafdari.com/contents/user749487/content-sound/lana_del_rey_-_radio.mp3",Main.class.getResource("images/pic3.jpg").toExternalForm(),"sbvj");
//        ArrayList <PodcastModel>ps=new ArrayList<>();
//        ps.add(o); ps.add(o2);
//        o.setAudioID(145); o2.setAudioID(8998);
//        podcaster.setPodcasts(ps);
//        Database.getDatabase().getAllAudios().add(o);
//        Database.getDatabase().getAllAudios().add(o2);
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
}
package org.example.demo8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demo8.model.Database;
import org.example.demo8.model.audioRelated.Genre;
import org.example.demo8.model.audioRelated.MusicModel;
import org.example.demo8.model.users.AdminModel;
import org.example.demo8.model.users.artists.SingerModel;

import java.io.IOException;

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
        Database.getDatabase().getAllAudios().add(new MusicModel("lkj","kjhhh", Genre.POP,"https://ts9.tarafdari.com/contents/user749487/content-sound/lana_del_rey_-_radio.mp3",Main.class.getResource("images/pic3.jpg").toExternalForm(),"lkk"));
        Database.getDatabase().getAllAudios().add(new MusicModel("joon","aloo", Genre.POP,"https://ts2.tarafdari.com/contents/user6984/content-sound/09.summertime_sadness.mp3",Main.class.getResource("images/pic1.jpg").toExternalForm(),"lkk"));
        Database.getDatabase().getAllAudios().add(new MusicModel("joon","aloo", Genre.POP,"https://ts2.tarafdari.com/contents/user6984/content-sound/09.summertime_sadness.mp3",Main.class.getResource("images/pic1.jpg").toExternalForm(),"lkhkj;;k"));
        Database.getDatabase().getAllAudios().add(new MusicModel("joon","aloo", Genre.POP,"https://ts2.tarafdari.com/contents/user6984/content-sound/09.summertime_sadness.mp3",Main.class.getResource("images/pic1.jpg").toExternalForm(),"lkk"));
        Database.getDatabase().getAllAudios().add(new MusicModel("joon","aloo", Genre.POP,"https://ts2.tarafdari.com/contents/user6984/content-sound/09.summertime_sadness.mp3",Main.class.getResource("images/pic1.jpg").toExternalForm(),"lkk"));
        Database.getDatabase().getAllAudios().add(new MusicModel("joon","aloo", Genre.POP,"https://ts2.tarafdari.com/contents/user6984/content-sound/09.summertime_sadness.mp3",Main.class.getResource("images/pic1.jpg").toExternalForm(),"lkk"));
        Database.getDatabase().getAllUsers().add(new SingerModel("kjcbndc","cvjhcv","aloo","jbjhbc","09132082206","2004/5/8","sjkncjkdcb"));
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
}
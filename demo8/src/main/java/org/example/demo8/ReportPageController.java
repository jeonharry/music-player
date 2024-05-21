package org.example.demo8;

import controller.Controller;
import controller.ListenerController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import model.users.listeners.ListenerModel;

public class ReportPageController {

    @FXML
    private StackPane close_btn;

    @FXML
    private TextArea explanation;

    @FXML
    private AnchorPane root;
    private static String  reportedArtist;

    @FXML
    void closePage(MouseEvent event) {
        Controller.getController().getStackPane().getChildren().removeLast();
    }

    @FXML
    void report(MouseEvent event) {
        if(Controller.getController().getAccModel() instanceof ListenerModel)
            ListenerController.getListenerController().report(reportedArtist,explanation.getText());
        Controller.getController().getStackPane().getChildren().removeLast();
    }

    public static String  getReportedArtist() {
        return reportedArtist;
    }

    public static void setReportedArtist(String  reportedArtist) {
        ReportPageController.reportedArtist = reportedArtist;
    }
}
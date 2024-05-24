package org.example.demo8;

import controller.Controller;
import controller.ListenerController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class MakePlayListController {

    @FXML
    private Label error;

    @FXML
    private StackPane close_btn;

    @FXML
    private TextField name;

    @FXML
    private AnchorPane root;

    @FXML
    void closePage(MouseEvent event) {
        Controller.getController().getStackPane().getChildren().removeLast();
    }

    @FXML
    void make(MouseEvent event) {
        error.setVisible(false);
        try
        {
            String answer=ListenerController.getListenerController().makePlayList(name.getText());
            if(answer.compareTo("playlist already exist")==0)
            {
                error.setVisible(true);
                error.setText("playlist already exist");
            }
        }catch (Exception exception)
        {
            error.setVisible(true);
            error.setText(exception.getMessage());
        }
    }

}
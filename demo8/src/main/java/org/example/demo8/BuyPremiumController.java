package org.example.demo8;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import controller.Controller;
import controller.ListenerController;

public class BuyPremiumController {

    @FXML
    private StackPane close_btn;

    @FXML
    private Label error;

    @FXML
    private StackPane oneHeightyNotSelected;

    @FXML
    private StackPane oneHeightySelected;

    @FXML
    private AnchorPane root;

    @FXML
    private StackPane sixtyNotSelected;

    @FXML
    private StackPane sixtySelected;

    @FXML
    private StackPane thirtyNotSelected;

    @FXML
    private StackPane thirtySelected;

    @FXML
    void closePage(MouseEvent event) {
        Controller.getController().getStackPane().getChildren().removeLast();
    }

    @FXML
    void selectOneHEighty(MouseEvent event) {
        sixtySelected.setVisible(false);
        sixtyNotSelected.setVisible(true);
        oneHeightySelected.setVisible(true);
        oneHeightyNotSelected.setVisible(false);
        thirtyNotSelected.setVisible(true);
        thirtySelected.setVisible(false);
    }

    @FXML
    void selectSixty(MouseEvent event) {
        sixtySelected.setVisible(true);
        sixtyNotSelected.setVisible(false);
        oneHeightySelected.setVisible(false);
        oneHeightyNotSelected.setVisible(true);
        thirtyNotSelected.setVisible(true);
        thirtySelected.setVisible(false);
    }

    @FXML
    void selectThirty(MouseEvent event) {
        sixtySelected.setVisible(false);
        sixtyNotSelected.setVisible(true);
        oneHeightySelected.setVisible(false);
        oneHeightyNotSelected.setVisible(true);
        thirtyNotSelected.setVisible(false);
        thirtySelected.setVisible(true);
    }

    @FXML
    void buy(MouseEvent event) {
        error.setVisible(false);
        if(Controller.getController().getAccModel()==null)
        {
            error.setVisible(true);
            error.setText("you are not loged in");
        }
        else
        {
            try
            {
                if(thirtySelected.isVisible())
                {
                    ListenerController.getListenerController().buyPremium("30");
                    Controller.getController().getStackPane().getChildren().removeLast();
                }
                else if(sixtySelected.isVisible())
                {
                    ListenerController.getListenerController().buyPremium("60");
                    Controller.getController().getStackPane().getChildren().removeLast();
                }
                else if(oneHeightySelected.isVisible())
                {
                    ListenerController.getListenerController().buyPremium("180");
                    Controller.getController().getStackPane().getChildren().removeLast();
                }
                else
                {
                    error.setVisible(true);
                    error.setText("you should choose a package");
                }
            }catch (Exception exception)
            {
                error.setVisible(true);
                error.setText(exception.getMessage());
            }
        }
    }

}
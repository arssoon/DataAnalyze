package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public abstract class SwitchScene {
    @FXML
    protected MainController mainController;
    public static double regressionValue1;
    public static double regressionValue2;

    public double getRegressionValue1() {
        return regressionValue1;
    }

    public void setRegressionValue1(double regressionValue1) {
        this.regressionValue1 = regressionValue1;
    }

    public double getRegressionValue2() {
        return regressionValue2;
    }

    public void setRegressionValue2(double regressionValue2) {
        this.regressionValue2 = regressionValue2;
    }

    //-------  passing to object MainController   -----------------------------------
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    //-------  LoginPane and SignUpPane setting on the left side of the pane   -----------------------------------
    public final void setLeftWindow(Pane pane, AnchorPane anchorPaneLeft) {
        anchorPaneLeft.getChildren().clear();
        anchorPaneLeft.getChildren().add(pane);
    }

    //-------  Load from MainController Library pane  -----------------------------------
    public final void setRightWindow(Pane pane, AnchorPane anchorPaneRight) {
        anchorPaneRight.getChildren().clear();
        anchorPaneRight.getChildren().add(pane);
    }
}

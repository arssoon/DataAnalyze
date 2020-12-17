package controller.library;

import controller.Path;
import controller.SwitchScene;
import javafx.event.ActionEvent;

public class MenuLibraryController extends SwitchScene implements Path {

    public void measureAnalize(ActionEvent actionEvent) {
        mainController.loadMeasureWindow(Path.PATH_MEASURE);
    }

    public void histogramAnalize(ActionEvent actionEvent) {
        mainController.loadHistogramWindow(Path.PATH_HISTOGRAM);
    }

    public void scatterAnalize(ActionEvent actionEvent) {
        mainController.loadScatterWindow(Path.PATH_SCATTER);
    }

    public void CorrelationAnalize(ActionEvent actionEvent) {
        mainController.loadCorrelationWindow(Path.PATH_CORRELATION);
    }
    public void boxAnalize(ActionEvent actionEvent) {
        mainController.loadBoxWindow(Path.PATH_BOX);
    }
}

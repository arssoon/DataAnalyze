package controller.analize;

import controller.SwitchScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class ScatterController extends SwitchScene implements Initializable {
    @FXML
    private LineChart<Number, Number> id_chartLine;
    DataToAnalysis dataToAnalysis;
    XYChart.Series<Number, Number>[] series;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        series = Stream.<XYChart.Series<Number, Number>>generate(XYChart.Series::new).limit(2).toArray(XYChart.Series[]::new);

        dataToAnalysis = new DataToAnalysis();
        dataToAnalysis.loadFromFile("cleveland.data");

        sortTable(dataToAnalysis.age);
        series[0].setName("Rozrzut");
        series[1].setName("Regresja liniowa");
        //TODO data input
        for (double i = 0.0; i < dataToAnalysis.sizeRow; i++) {
            series[0].getData().add(new XYChart.Data(String.valueOf(dataToAnalysis.num[(int) i]), dataToAnalysis.age[(int) i]));
            if(i < 5) {
                series[1].getData().add(new XYChart.Data(String.valueOf(i), patternReg(i)));
            }
        }

        id_chartLine.getData().addAll(series[0], series[1]);
    }


    private double patternReg(double x) {
        double y;
        y = getRegressionValue1() * x + getRegressionValue2();

        return y;
    }

    private void sortTable(double[] table) {
        ArrayList<Double> sortList = new ArrayList<>();

        for (int i = 0; i < dataToAnalysis.sizeRow; i++) {
            sortList.add(table[i]);
        }
        Collections.sort(sortList);

        for (int i = 0; i < dataToAnalysis.sizeRow; i++) {
            table[i] = sortList.get(i);
        }
    }



}

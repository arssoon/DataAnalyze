package controller.analize;

import controller.SwitchScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class HistogramController extends SwitchScene implements Initializable {
    @FXML
    private StackedBarChart<?, ?> id_chart;
    XYChart.Series[] series;
    int sizeCategory;
    DataToAnalysis dataToAnalysis;
    public int counterAge;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataToAnalysis = new DataToAnalysis();
        dataToAnalysis.loadFromFile("cleveland.data");
        sizeCategory = 4;

        declareVariablesAxises();
        addDataToCategory();

        id_chart.getData().addAll(series);
    }

    //-------  ASSIGN DATA TO THE CATEGORY  -----------------------------------
    private void addDataToCategory() {
        counterAge = 0;
        int[] counterNum = new int[5];
        int[] counterCp = new int[4];
        int[] counterRestecg = new int[3];
        int[] counterSlope = new int[3];

        for (int i = 0; i < dataToAnalysis.sizeRow; i++) {
            /** ------------    ZERA     ----------------------------------  */
            equalData(counterNum, dataToAnalysis.num, i, 0.0, 0);
            equalData(counterRestecg, dataToAnalysis.restecg, i, 0.0, 0);
            /** ------------    JEDYNKI     ----------------------------------  */
            equalData(counterNum, dataToAnalysis.num, i, 1.0, 1);
            equalData(counterCp, dataToAnalysis.cp, i, 1.0, 0);
            equalData(counterSlope, dataToAnalysis.slope, i, 1.0, 0);
            equalData(counterRestecg, dataToAnalysis.restecg, i, 1.0, 1);
            /** ------------    DWOJKI     ----------------------------------  */
            equalData(counterNum, dataToAnalysis.num, i, 2.0, 2);
            equalData(counterCp, dataToAnalysis.cp, i, 2.0, 1);
            equalData(counterSlope, dataToAnalysis.slope, i, 2.0, 1);
            equalData(counterRestecg, dataToAnalysis.restecg, i, 2.0, 2);
            /** ------------    TRÓJKI     ----------------------------------  */
            equalData(counterNum, dataToAnalysis.num, i, 3.0, 3);
            equalData(counterCp, dataToAnalysis.cp, i, 3.0, 2);
            equalData(counterSlope, dataToAnalysis.slope, i, 3.0, 2);
            /** ------------    CZWÓRKI     ----------------------------------  */
            equalData(counterNum, dataToAnalysis.num, i, 4.0, 4);
            equalData(counterCp, dataToAnalysis.cp, i, 4.0, 3);
        }

        addDataToCategory(counterCp[0], "1 ", series[0]);
        addDataToCategory(counterCp[1], "2 ", series[0]);
        addDataToCategory(counterCp[2], "3 ", series[0]);
        addDataToCategory(counterCp[3], "4 ", series[0]);

        addDataToCategory(counterRestecg[0], " 0", series[1]);
        addDataToCategory(counterRestecg[1], " 1", series[1]);
        addDataToCategory(counterRestecg[2], " 2", series[1]);

        addDataToCategory(counterSlope[0], " 1 ", series[2]);
        addDataToCategory(counterSlope[1], " 2 ", series[2]);
        addDataToCategory(counterSlope[2], " 3 ", series[2]);

        addDataToCategory(counterNum[0], "0", series[3]);
        addDataToCategory(counterNum[1], "1", series[3]);
        addDataToCategory(counterNum[2], "2", series[3]);
        addDataToCategory(counterNum[3], "3", series[3]);
        addDataToCategory(counterNum[4], "4", series[3]);

    }

    private void equalData(int[] counterNum, double[] num, int i2, double v, int i3) {
        if (num[i2] == v) {
            counterNum[i3]++;
        }
    }

    private void addDataToCategory(double age, String name, XYChart.Series series) {
        series.getData().add(new XYChart.Data(name, age));
    }

    // -----    declaration of variables Axis X and Y   ------------------------------------------------------
    private void declareVariablesAxises() {
        series = Stream.<XYChart.Series<?, ?>>generate(XYChart.Series::new).limit(sizeCategory).toArray(XYChart.Series[]::new);

        int i = 0;
        // -----   LOOP WHICH WRITE CATEGORY OF MOVIES TO TABLE   ------------------------------------------------------
        for (Disease disease : Disease.values()) {
            series[i++].setName(String.valueOf(disease));
        }
    }
}

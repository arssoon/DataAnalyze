package controller.analize;

import controller.SwitchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MeasureController extends SwitchScene {
    public Label id_measure;
    public Label id_measure2;
    public Label id_medianaLabel;
    public Label id_iqrLabel;
    public Label id_quartileLabel;
    public Label id_regressionLabel;
    public Label id_pointsLabel;
    double iqr;
    double point, point2;
    DataToAnalysis dataToAnalysis;
    StringBuilder msg;

    @FXML
    void initialize() throws ParseException {
        dataToAnalysis = new DataToAnalysis();
        dataToAnalysis.loadFromFile("cleveland.data");
        msg = new StringBuilder();

        /* --------  WARTOSC MIN I MAX   ---------------------------------------------------------- */
        loadFirstData();
        msg.delete(0, msg.length());

        /* --------  SREDNIA I ODCHYLENIE   ---------------------------------------------------------- */
        loadSecondData();
        msg.delete(0, msg.length());

        /* --------  MEDIANA   ---------------------------------------------------------- */
        loadThirdData();
        msg.delete(0, msg.length());

        /* --------  ROZSTEP MIEDZYKWARTYLOWY   ---------------------------------------------------------- */
        loadFourthData();
        msg.delete(0, msg.length());

        /* --------  KWARTYLE RZĘDU 0.1 I 0.9   ---------------------------------------------------------- */
        loadFifthData();
        msg.delete(0, msg.length());

        /* --------  PUNKTY ODDALONE   ---------------------------------------------------------- */
        loadSixthData();
        msg.delete(0, msg.length());

        setRegressionValue1(formatNumber(DataToAnalysis.df2.format(regression().getSlope())));
        setRegressionValue2(formatNumber(DataToAnalysis.df2.format(regression().getIntercept())));

        id_regressionLabel.setText("Wzór prostej: \n" +
                "y = x * " + getRegressionValue1() + " + " +
                getRegressionValue2()
        );
    }

    private double formatNumber(String text) throws ParseException {
        double formatDoble;
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        Number number = format.parse(text);
        formatDoble = number.doubleValue();

        return formatDoble;
    }

    private boolean getPoint(double p) {
        point = dataToAnalysis.stats.getPercentile(25) - 1.5;
        point2 = dataToAnalysis.stats.getPercentile(75) + 1.5;

        return p < point || p > point2;
    }

    private void loadSixthData() {
        dataToAnalysis.addData(dataToAnalysis.age);
        int j = 0;
        for (int i = 0; i < dataToAnalysis.sizeRow; i++) {
            if(getPoint(dataToAnalysis.age[i]) && j < 14)
            {
                appendText("kolumna: " + 1 + "\nwiersz: ", i + 1);
                appendText("wartosc: ", Double.parseDouble(DataToAnalysis.df0.format(dataToAnalysis.age[i])));
                j++;
            }
        }
        id_pointsLabel.setText(msg.toString());
    }

    private SimpleRegression regression() {
        SimpleRegression simpleRegression = new SimpleRegression();

        for (int i = 0; i < dataToAnalysis.sizeRow; i++) {
            simpleRegression.addData(dataToAnalysis.num[i], dataToAnalysis.age[i]);
        }
        return simpleRegression;
    }

    private void loadFifthData() {
        int i = 0;
        for( Object temp : dataToAnalysis.tab) {
            dataToAnalysis.addData((double[]) temp);
            appendText(dataToAnalysis.attributesName[i], "-quartil-0.1: ", dataToAnalysis.stats.getPercentile(10));
            appendText(dataToAnalysis.attributesName[i], "-quartil-0.9: ", dataToAnalysis.stats.getPercentile(90));

            dataToAnalysis.stats.clear();
            i++;
        }

        id_quartileLabel.setText(msg.toString());
    }

    private double quartile() {
        iqr = dataToAnalysis.stats.getPercentile(75) - dataToAnalysis.stats.getPercentile(25);
        return iqr;
    }

    private void loadFourthData() {
        int i = 0;
        for( Object temp : dataToAnalysis.tab) {
            dataToAnalysis.addData((double[]) temp);
            quartile();
            appendText(dataToAnalysis.attributesName[i], " : ", Double.parseDouble(DataToAnalysis.df0.format(quartile())));
            dataToAnalysis.stats.clear();
            i++;
        }

        id_iqrLabel.setText(msg.toString());
    }

    private void loadThirdData() {
        int i = 0;

        for( Object temp : dataToAnalysis.tab) {
            dataToAnalysis.addData((double[]) temp);
            appendText(dataToAnalysis.attributesName[i], "-median: ", dataToAnalysis.stats.getPercentile(50));
            dataToAnalysis.stats.clear();

            i++;
        }

        id_medianaLabel.setText(msg.toString());
    }

    private void loadSecondData() throws ParseException {
        int i = 0;
        for( Object temp : dataToAnalysis.tab) {
            dataToAnalysis.addData((double[]) temp);
            appendText(dataToAnalysis.attributesName[i], "-average: ", Double.parseDouble(DataToAnalysis.df0.format(dataToAnalysis.stats.getMean())));
            appendText(dataToAnalysis.attributesName[i], "-stand-dev: ", formatNumber(DataToAnalysis.df2.format(dataToAnalysis.stats.getStandardDeviation())));
            dataToAnalysis.stats.clear();

            i++;
        }

        id_measure2.setText(msg.toString());
    }


    private void loadFirstData() {
        int i = 0;
        for( Object temp : dataToAnalysis.tab) {
            dataToAnalysis.addData((double[]) temp);
            appendText(dataToAnalysis.attributesName[i], "-min: ", dataToAnalysis.stats.getMin());
            appendText(dataToAnalysis.attributesName[i], "-max: ", dataToAnalysis.stats.getMax());
            dataToAnalysis.stats.clear();

            i++;
        }

        id_measure.setText(msg.toString());
    }

    private void appendText(String s, String s2, double d1) {
        msg.append(s + s2).append(d1).append("\n");
    }

    private void appendText(String s2, double d1) {
        msg.append(s2).append(d1).append("\n");
    }

}

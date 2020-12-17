package controller.analize;

import controller.SwitchScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class CorrelationController extends SwitchScene implements Initializable {

    @FXML
    ListView<Double> listView1;
    @FXML
    ListView<Double> listView2;
    @FXML
    ListView<Double> listView3;
    @FXML
    ListView<Double> listView4;
    @FXML
    ListView<Double> listView5;
    @FXML
    ListView<Double> listView6;
    @FXML
    ListView<Double> listView7;
    @FXML
    ListView<Double> listView8;
    @FXML
    ListView<Double> listView9;
    @FXML
    ListView<Double> listView10;
    @FXML
    ListView<Double> listView11;
    @FXML
    ListView<Double> listView12;
    @FXML
    ListView<Double> listView13;
    @FXML
    ListView<Double> listView14;
    @FXML
    ListView<String> listView15;
    @FXML
    ListView<String> listView16;
    DataToAnalysis dataToAnalysis;
    BigDecimal bd;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataToAnalysis = new DataToAnalysis();
        dataToAnalysis.loadFromFile("cleveland.data");

        for (int i=1; i<= 14; i++){
            listView15.getItems().add("X"+i);
            listView16.getItems().add("X"+i);
        }

        listView15.setCellFactory(param -> new ListCell<String>() {
            {
                prefWidthProperty().set(43.68);
                setAlignment(Pos.CENTER);
            }
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item);
                } else {
                    setText(null);
                }
            }
        });

        PearsonsCorrelation correlation = new PearsonsCorrelation();
        final RealMatrix matrix = correlation.computeCorrelationMatrix(dataToAnalysis.attributesValue);

        for (int i=0; i< 14; i++) {
            for (int j =0; j < 14; j++) {
                bd = new BigDecimal(matrix.getEntry(i, j)).setScale(2, RoundingMode.HALF_UP);
                if(j == 0){
                    listView1.getItems().add(bd.doubleValue());
                }
                else if(j == 1) {
                    listView2.getItems().add(bd.doubleValue());
                }
                else if(j == 2) {
                    listView3.getItems().add(bd.doubleValue());
                }
                else if(j == 3) {
                    listView4.getItems().add(bd.doubleValue());
                }
                else if(j == 4) {
                    listView5.getItems().add(bd.doubleValue());
                }
                else if(j == 5) {
                    listView6.getItems().add(bd.doubleValue());
                }
                else if(j == 6) {
                    listView7.getItems().add(bd.doubleValue());
                }
                else if(j == 7) {
                    listView8.getItems().add(bd.doubleValue());
                }
                else if(j == 8) {
                    listView9.getItems().add(bd.doubleValue());
                }
                else if(j == 9) {
                    listView10.getItems().add(bd.doubleValue());
                }
                else if(j == 10) {
                    listView11.getItems().add(bd.doubleValue());
                }
                else if(j == 11) {
                    listView12.getItems().add(bd.doubleValue());
                }
                else if(j == 12) {
                    listView13.getItems().add(bd.doubleValue());
                }
                else if(j == 13) {
                    listView14.getItems().add(bd.doubleValue());
                }
            }
        }
    }
}

package controller.analize;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoxChart {
    DataToAnalysis dataToAnalysis;
    DefaultCategoryDataset dataCategory;
    ArrayList<String> categoryPatientList;
    BoxAndWhiskerCategoryDataset dataset;
    CategoryPlot plot;

    public BoxChart() {
        dataToAnalysis = new DataToAnalysis();
        dataToAnalysis.loadFromFile("cleveland.data");
        dataCategory = new DefaultCategoryDataset();
        declareVariablesAxises();

        dataset = createSampleDataset();

        CategoryAxis xAxis = new CategoryAxis("ATRYBUTY");
        NumberAxis yAxis = new NumberAxis("LICZBA WARTOŚCI");
        yAxis.setAutoRangeIncludesZero(false);
        BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(false);
        plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

    }

    public JFreeChart createChart() {
        JFreeChart chart = new JFreeChart(
                "BOX CHART",
                new Font("SansSerif", Font.BOLD, 10),
                plot,
                true
        );

        return chart;
    }

    private BoxAndWhiskerCategoryDataset createSampleDataset() {
        int seriesCount = 0;
        final int entityCount = 303;

        final DefaultBoxAndWhiskerCategoryDataset dataset
                = new DefaultBoxAndWhiskerCategoryDataset();
        List list;

        for (DiseaseAll diseaseAll : DiseaseAll.values()) {
            list = new ArrayList();
                for (int k = 0; k < entityCount; k++) {
                    list.add(dataToAnalysis.attributesValue[k][seriesCount]);
                    dataset.add(list, diseaseAll, "Type 1");
                }
            seriesCount++;
        }

        return dataset;
    }

    // -----    declaration of variables Axis X and Y   ------------------------------------------------------
    private void declareVariablesAxises() {
        //----  GATUNKI FILMÓW
        categoryPatientList = new ArrayList();

        // -----   LOOP WHICH WRITE CATEGORY OF MOVIES TO TABLE   ------------------------------------------------------
        for (DiseaseAll disease : DiseaseAll.values()) {
            categoryPatientList.add(String.valueOf(disease));
        }
    }

}
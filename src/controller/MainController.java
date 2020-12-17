package controller;

import controller.analize.*;
import controller.library.*;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.jfree.chart.ChartPanel;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainController extends SwitchScene implements Path {
    @FXML
    public StackPane mainPane;
    @FXML
    public AnchorPane anchorPaneRight;
    @FXML
    public AnchorPane anchorPaneLeft;
    @FXML
    public SplitPane splitPaneLibrary;
    @FXML
    public Pane boxPane;
    BoxChart chart;

    @FXML
    public void initialize() {
        chart = new BoxChart();

        loadLibraryMenuWindow(PATH_MENU_LIBRARY);
        loadMeasureWindow(Path.PATH_MEASURE);
    }

    public void loadCorrelationWindow(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Pane pane = new Pane();
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CorrelationController correlationController = loader.getController();
        correlationController.setMainController(this);
        setLeftWindow(pane, anchorPaneRight);
    }

    public void loadLibraryMenuWindow(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Pane pane = new Pane();
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuLibraryController menuLibraryController = loader.getController();
        menuLibraryController.setMainController(this);
        setLeftWindow(pane, anchorPaneLeft);
    }

    public void loadMeasureWindow(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Pane pane = new Pane();
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MeasureController measureController = loader.getController();
        measureController.setMainController(this);
        setRightWindow(pane, anchorPaneRight);
    }

    public void loadHistogramWindow(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Pane pane = new Pane();
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HistogramController histogramController = loader.getController();
        histogramController.setMainController(this);
        setRightWindow(pane, anchorPaneRight);
    }

    public void loadScatterWindow(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Pane pane = new Pane();
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScatterController scatterController = loader.getController();
        scatterController.setMainController(this);
        setRightWindow(pane, anchorPaneRight);
    }

    public void loadBoxWindow(String fxmlPath) {
        final SwingNode swingNode = new SwingNode();

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Pane pane = new Pane();
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.getChildren().add(swingNode);
        createSwingContent(swingNode);

        BoxController boxController = loader.getController();
        boxController.setMainController(this);
        setRightWindow(pane, anchorPaneRight);
        anchorPaneRight.setBottomAnchor(pane, 0.0);
        anchorPaneRight.setRightAnchor(pane, -20.0);
    }

    private void createSwingContent(final SwingNode swingNode) {
        ChartPanel chartPanel = new ChartPanel(chart.createChart());
        chartPanel.setPreferredSize(new Dimension(633, 550));

        JPanel panel = new JPanel();
        JLabel label = new JLabel();

        label.setText("WYKRES PUDEŁKOWY");
        label.setForeground(Color.decode("#daa3a3"));
        label.setFont(new Font("SansSerif", Font.BOLD, 35));

        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.add(chartPanel, BorderLayout.SOUTH);

        panel.setSize(633, 600);
        panel.setBackground(new Color(70,0,0));
//        panel.setVisible(true);

        swingNode.setContent(panel);
    }
}

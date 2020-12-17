package controller.analize;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class DataToAnalysis {
    public static DecimalFormat df2 = new DecimalFormat("#.##");
    public static DecimalFormat df0 = new DecimalFormat("#");
    public DescriptiveStatistics stats;
    public double[][] attributesValue;
    public String[] attributesName;
    public ArrayList tab;
    ArrayList<Double> attr;
    ArrayList<ArrayList<Double>> matrix;

    int sizeColumn, sizeRow;
    public double[] age, sex;
    public double[] cp;
    public double[] trestbps;
    public double[] chol;
    public double[] fbs;
    public double[] restecg;
    public double[] thalach;
    public double[] exang;
    public double[] oldpeak;
    public double[] slope;
    public double[] ca;
    public double[] thal;
    public double[] num;

    DataToAnalysis() {
        sizeColumn = 14;
        sizeRow = 303;
        attributesValue = new double[sizeRow][sizeColumn];
        attributesName = new String[]{"AGE", "SEX", "CP", "TRESTBPS", "CHOL", "FBS", "RESTECG", "THALACH",
                "EXANG", "OLDPEAK", "SLOPE", "CA", "THAL", "NUM"};
        attr = new ArrayList<>();
        matrix = new ArrayList<>();

        tab = new ArrayList<>();
        age = new double[sizeRow];
        sex = new double[sizeRow];
        cp = new double[sizeRow];
        trestbps = new double[sizeRow];
        chol = new double[sizeRow];
        fbs = new double[sizeRow];
        restecg = new double[sizeRow];
        thalach = new double[sizeRow];
        exang = new double[sizeRow];
        oldpeak = new double[sizeRow];
        slope = new double[sizeRow];
        ca = new double[sizeRow];
        thal = new double[sizeRow];
        num = new double[sizeRow];
        stats = new DescriptiveStatistics();


        tab.add(age);
        tab.add(sex);
        tab.add(cp);
        tab.add(trestbps);
        tab.add(chol);
        tab.add(fbs);
        tab.add(restecg);
        tab.add(thalach);
        tab.add(exang);
        tab.add(oldpeak);
        tab.add(slope);
        tab.add(ca);
        tab.add(thal);
        tab.add(num);
    }

    public void loadFromFile(String file) {
        File log = new File(file);
        BufferedReader br;
        if (log.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                loadDataFromFile(br);
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void loadDataFromFile(BufferedReader br) throws IOException {
        String line;
        String FieldDelimiter = ",";
        int i = 0;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(FieldDelimiter, -1);

            age[i] = Double.parseDouble(fields[0]);
            sex[i] = Double.parseDouble(fields[1]);
            cp[i] = Double.parseDouble(fields[2]);
            trestbps[i] = Double.parseDouble(fields[3]);
            chol[i] = Double.parseDouble(fields[4]);
            fbs[i] = Double.parseDouble(fields[5]);
            restecg[i] = Double.parseDouble(fields[6]);
            thalach[i] = Double.parseDouble(fields[7]);
            exang[i] = Double.parseDouble(fields[8]);
            oldpeak[i] = Double.parseDouble(fields[9]);
            slope[i] = Double.parseDouble(fields[10]);
            ca[i] = Double.parseDouble(fields[11]);
            thal[i] = Double.parseDouble(fields[12]);
            num[i] = Double.parseDouble(fields[13]);

            matrix.add(i, new ArrayList<>());
            for (int j = 0; j < 14; j++) {
                attr.add(j, Double.parseDouble(fields[j]));
                matrix.get(i).add(attr.get(j));
                attributesValue[i][j] = Double.parseDouble(fields[j]);
            }
            i++;
        }
    }

    public void addData(double [] data) {
        for(int i = 0; i < sizeRow; i++) {
            stats.addValue(data[i]);
        }
    }

}

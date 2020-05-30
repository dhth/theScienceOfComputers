package CS61B.CLab7;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {

        Random r = new Random();
        BST<Integer> bst = new BST<>();
        List<Integer> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();

        for (int x = 1; x < 5000; x += 1) {
            int randomNum = RandomGenerator.getRandomInt(10000);
            bst.add(randomNum);
            double thisY = bst.getAverageDepth();
            xValues.add(x);
            yValues.add(thisY);

            double thisY2 = ExperimentHelper.optimalAverageDepth(x);
            y2Values.add(thisY2);
        }

        XYChart chart =
                new XYChartBuilder().width(800).height(600).xAxisTitle(
                        "Number of nodes").yAxisTitle("Average Depth").build();
        chart.addSeries("Random insertions", xValues, yValues);
        chart.addSeries("Optimal case", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        BST<Integer> bst = new BST<>();
        // initial setup
        int N = 5000;
        for (int i = 0; i < N; i++) {
            ExperimentHelper.randomInsert(bst);
        }

        List<Integer> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        double start_depth = bst.getAverageDepth();
        xValues.add(0);
        yValues.add(start_depth);

        for (int x = 1; x < 10000000; x += 1) {
            ExperimentHelper.asymmetricalRandomDelete(bst);
            ExperimentHelper.randomInsert(bst);
            if (x % 100000 == 0) {
                double thisY = bst.getAverageDepth();
                xValues.add(x);
                yValues.add(thisY);
            }
        }

        XYChart chart =
                new XYChartBuilder().width(800).height(600).xAxisTitle(
                        "Number of operations").yAxisTitle("Average Depth").build();
        chart.addSeries("Asymmetric deletions", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        BST<Integer> bst2 = new BST<>();
        // initial setup
        int N = 5000;
        for (int i = 0; i < N; i++) {
            ExperimentHelper.randomInsert(bst2);
        }

        List<Integer> xValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();

        for (int x = 1; x <= 10000000; x += 1) {
            ExperimentHelper.symmetricalRandomDelete(bst2);
            ExperimentHelper.randomInsert(bst2);
            if (x % 100000 == 0) {
                double thisY2 = bst2.getAverageDepth();
                xValues.add(x);
                y2Values.add(thisY2);
            }
        }

        XYChart chart =
                new XYChartBuilder().width(800).height(600).xAxisTitle(
                        "Number of operations").yAxisTitle("Average Depth").build();
        chart.addSeries("Symmetric deletions", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment1();
//        experiment2();
//        experiment3();
    }
}

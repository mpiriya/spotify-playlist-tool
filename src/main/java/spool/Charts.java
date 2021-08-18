package spool;

import java.util.HashMap;

import org.knowm.xchart.RadarChart;
import org.knowm.xchart.RadarChartBuilder;

import java.util.HashMap;
import java.util.Map;

public class Charts {
  public static RadarChart makeChart(HashMap<String, Float> avgs) {
    RadarChart toReturn = new RadarChartBuilder().width(960).height(600).title("Your Playlist").build();

    // customize chart

    // calculate values
    double[] values = new double[avgs.size()];
    String[] labels = new String[avgs.size()];
    int i = 0;
    for(Map.Entry<String, Float> e : avgs.entrySet()) {
      labels[i] = e.getKey();
      values[i] = e.getValue().doubleValue();
      i++;
    }

    // set values and labels
    toReturn.setRadiiLabels(labels);
    toReturn.addSeries("butt", values);
    

    return toReturn;
  }
  
}
package graphs;

import java.awt.Color;

import org.jfree.chart.*;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.*;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.*;


public class GRAPH_CoolantTemperature  {
	private TimeSeries timeSeries;
	TimeSeriesCollection dataset;
	JFreeChart chart;
	ChartPanel chartPanel;
	
	public GRAPH_CoolantTemperature()
	{
		timeSeries = new TimeSeries("CoolantTempSeries");
		dataset= new TimeSeriesCollection(timeSeries);
         chart= createChart(dataset);
         chartPanel = new ChartPanel(chart);
         chartPanel.setBackground(Color.WHITE);
	}
	
	public ChartPanel getChart()
	{
		return chartPanel;
	}
	
	
	private JFreeChart createChart(XYDataset dataset)
	{
		JFreeChart chart = ChartFactory.createTimeSeriesChart("Engine Coolant Temperature", "Time", "°C", dataset,true,true,false);
		chart.setBackgroundPaint(Color.WHITE);
		XYPlot plot = chart.getXYPlot();
		ValueAxis axis = plot.getDomainAxis();
		axis.setAutoRange(true);
		axis.setFixedAutoRange(60000.0);
		axis = plot.getRangeAxis();
		axis.setRange(0.0,200.0);
		return chart;
		
	}
	
	public void addValue(int f)
	{
		timeSeries.add(new Millisecond(),f);
	}
}

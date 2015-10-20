package graphs;

import java.awt.Dimension;
import java.util.Date;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class GRAPH_BasicTimeSeriesChart {
	String titleOfGraph;
	String titleOfDomain;
	String titleOfRange;
	String [] titleOfTimeSeries;
	
	int minRangeValue;
	int maxRangeValue;
	
	TimeSeries[] timeSeries;
	TimeSeriesCollection dataSet;
	JFreeChart chart;
	ChartPanel chartPanel;
	Dimension chartPanelDimension;
	
	public GRAPH_BasicTimeSeriesChart(String titleOfGraph,String titleOfDomain,String titleOfRange,String [] coolantTemperatureTitle,int minRangeValue, int maxRangeValue,int width,int height,int noOfDataSets)
	{
		this.titleOfGraph = titleOfGraph;
		this.titleOfDomain = titleOfDomain;
		this.titleOfRange = titleOfRange;
		this.titleOfTimeSeries = coolantTemperatureTitle;
		this.minRangeValue = minRangeValue;
		this.maxRangeValue = maxRangeValue;
		chartPanelDimension = new Dimension(width,height);
		timeSeries = new TimeSeries[noOfDataSets];
		
		dataSet = new TimeSeriesCollection(timeSeries[0]);
		//Creating timeSeries
		for (int i =0; i <titleOfTimeSeries.length;i++)
		{
			timeSeries[i] = new TimeSeries(titleOfTimeSeries[i],titleOfDomain,titleOfRange);
			dataSet.addSeries(timeSeries[i]);
		}
		
		//Creating Chart
		chart = createChart();
		
		//Changing the Axis
		changeRange(minRangeValue,maxRangeValue,30000);
		
		//Creating ChartPanel
		chartPanel = new ChartPanel(chart);
		chartPanel.setSize(chartPanelDimension);
	}
		//Constructor for one dataset
		public GRAPH_BasicTimeSeriesChart(String titleOfGraph,String titleOfDomain,String titleOfRange,String coolantTemperatureTitle,int minRangeValue, int maxRangeValue,int width,int height)
		{
			this.titleOfGraph = titleOfGraph;
			this.titleOfDomain = titleOfDomain;
			this.titleOfRange = titleOfRange;
			this.titleOfTimeSeries = new String[1];
			this.titleOfTimeSeries[0] = coolantTemperatureTitle;
			this.minRangeValue = minRangeValue;
			this.maxRangeValue = maxRangeValue;
			chartPanelDimension = new Dimension(width,height);
			
			timeSeries = new TimeSeries[1];
			timeSeries[0] = new TimeSeries(titleOfTimeSeries[0],titleOfDomain,titleOfRange);
			
			dataSet = new TimeSeriesCollection(timeSeries[0]);
			
			//Creating Chart
			chart = createChart();
			
			//Changing the Axis
			changeRange(minRangeValue,maxRangeValue,30000);
			
			removeDatasetLabel();
			
			//Creating ChartPanel
			chartPanel = new ChartPanel(chart);
			chartPanel.setSize(chartPanelDimension);
		}
		//Initialising the Dataset with the timeseries

		

	
	//Method to create JFreeChart
	private JFreeChart createChart()
	{
			JFreeChart temp_Chart = ChartFactory.createTimeSeriesChart(titleOfGraph, titleOfDomain, titleOfRange, dataSet,true,true,false);
			return temp_Chart;

		
	}
	
	private void changeRange(int minRange, int maxRange, int autoRangeTime)
	{
		XYPlot plot = chart.getXYPlot();
		ValueAxis axis = plot.getRangeAxis();
		axis.setRange(minRange,maxRange);
		DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
		dateAxis.setFixedAutoRange(autoRangeTime);
	}
	
	private void removeDatasetLabel()
	{
		XYPlot plot = chart.getXYPlot();
		plot.getRenderer().removeAnnotations();
	}
	

	
	public void addPoint(float value,Date date,int noOfDataSet)
	{

		RegularTimePeriod period = new Millisecond(date);
		timeSeries[noOfDataSet].add(period, value);
	}

	public void setWidth(double width) {
		Dimension panelD= chartPanel.getSize(); 
		Double widthD = new Double(width);
		Double heightD = new Double(panelD.getHeight());
		int widthI = widthD.intValue();
		int heightl = heightD.intValue();
		System.out.println("Width:" + widthI + " Height: " + heightl);
		chartPanel.setPreferredSize(new Dimension(widthI,heightl));
		
		
	}
	
	public ChartPanel getChartPanel()
	{
		return chartPanel;
	}
	
}

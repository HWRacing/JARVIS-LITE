package mainframe;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Date;
import java.util.Random;

import javax.swing.*;

import graphs.GRAPH_BasicMeterChart;
import graphs.GRAPH_BasicTimeSeriesChart;

public class GUI_GraphsPanel extends JPanel implements ComponentListener {
	//External Sensors and ECU Sensors Graph Panel
	GUI_MainFrame parent_FRA;
	JPanel externalSensor_PAN;
	JPanel ecuSensor_PAN;
	JPanel sensor_PAN;
	
	GRAPH_BasicTimeSeriesChart [] externalTimeSeriesChart = new GRAPH_BasicTimeSeriesChart[5];
	Thread [] externalTimeSeriesThreads = new Thread[externalTimeSeriesChart.length];
	boolean [] externalTimeSeriesEnabled = new boolean[externalTimeSeriesChart.length];
	
	GRAPH_BasicTimeSeriesChart [] ecuTimeSeriesChart = new GRAPH_BasicTimeSeriesChart[2];
	
	public GUI_GraphsPanel(GUI_MainFrame parent_FRA)
	{
		this.parent_FRA = parent_FRA;
		this.addComponentListener(this);
		createGraphsExternalSensors();
	}
		
	
	private void createGraphsExternalSensors()
	{	
		externalSensor_PAN = new JPanel(new GridLayout(3,3));
		
		//Creating Array to hold the DatasetTitles
		String[] pedalForceDataSetTitles = new String[2];
		pedalForceDataSetTitles[0] = "Acceleration";
		pedalForceDataSetTitles[1] = "Brake";
		
		
		
		externalTimeSeriesChart[0] = new GRAPH_BasicTimeSeriesChart("Coolant Temperature","Time","°C","Temperature",0, 100,150,400);
		externalTimeSeriesChart[1] = new GRAPH_BasicTimeSeriesChart("Exhaust Temperature","Time","°C","Temperature",0, 200,150,400);
		externalTimeSeriesChart[2] = new GRAPH_BasicTimeSeriesChart("Oil Temperature","Time","°C","Temperature",0,200,150,400);
		externalTimeSeriesChart[3] = new GRAPH_BasicTimeSeriesChart("Pedal Force","Time","Force (N)",pedalForceDataSetTitles,0,100,150,400,2);
		externalTimeSeriesChart[4] = new GRAPH_BasicTimeSeriesChart("Steering Angle","Time","°","Angle",-130,130,150,400);
		
		//Creating separate threads for each time series chart
		for (int i=0;i<externalTimeSeriesChart.length;i++)
		{
			externalTimeSeriesThreads[i] = new Thread(externalTimeSeriesChart[i]);
			externalTimeSeriesThreads[i].start();
		}
		
		addExternalChartsToExternalPanel();
		

		
			}
	
	
	private void createECUSensorsPanel()
	{
		ecuSensor_PAN = new JPanel(new FlowLayout());
	}
	
	
	private void addExternalChartsToExternalPanel()
	{
		
		for(int i = 0; i<externalTimeSeriesChart.length;i++)
		{
			if(externalTimeSeriesEnabled[i] ==true)
			{
				externalSensor_PAN.add(externalTimeSeriesChart[i].getChartPanel());
			}
			else if(externalTimeSeriesEnabled[i]==false)
			{
				externalSensor_PAN.remove(externalTimeSeriesChart[i].getChartPanel());
			}
			
		}
		this.add(externalSensor_PAN);
	}
	
	public void updateExternalSensorsPanel(float value)
	{
		Date date = new Date();
		externalTimeSeriesChart[0].addPoint(value,date,0);
	}
	
	private void setTimeSeriesChartSize(GRAPH_BasicTimeSeriesChart chart)
	{
		double width = parent_FRA.getWidth();
		double width2 = (width) /3;
		System.out.println(width2);
		chart.setWidth(width2);
	
	}
	
	
	
	public void enableCoolantTemperature()
	{
		if (externalTimeSeriesEnabled[0] == false)
		{
			externalTimeSeriesEnabled[0] = true;
		}
		else
		{
			externalTimeSeriesEnabled[0] = false;
		}
		
		addExternalChartsToExternalPanel();
	}


	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		for ( int i = 0; i<externalTimeSeriesChart.length;i++)
		{
			setTimeSeriesChartSize(externalTimeSeriesChart[i]);
		}
		this.repaint();
	}



	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}



	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}



	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


}

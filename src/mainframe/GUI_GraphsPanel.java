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
	GRAPH_BasicTimeSeriesChart [] externalTimeSeriesChart = new GRAPH_BasicTimeSeriesChart[4];
	boolean [] externalTimeSeriesEnabled = new boolean[externalTimeSeriesChart.length];
	
	GRAPH_BasicTimeSeriesChart [] ecuTimeSeriesChart = new GRAPH_BasicTimeSeriesChart[2];
	
	
	GRAPH_BasicMeterChart testChart;
	
	public GUI_GraphsPanel(GUI_MainFrame parent_FRA)
	{
		this.parent_FRA = parent_FRA;
		this.addComponentListener(this);
		createExternalSensorsPanel();
	}
		
	
	private void createExternalSensorsPanel()
	{
		externalSensor_PAN = new JPanel();
		externalSensor_PAN.setLayout(new GridLayout(3,3));
		
		
		//Creating Arrays with each 

		String[] pedalForceTitle = new String[2];
		pedalForceTitle[0] = "Acceleration";
		pedalForceTitle[1] = "Brake";
		
		externalTimeSeriesChart[0] = new GRAPH_BasicTimeSeriesChart("Coolant Temperature","Time","°C","Temperature",0, 100,20,400);
		externalTimeSeriesChart[1] = new GRAPH_BasicTimeSeriesChart("Exhaust Temperature","Time","°C","Temperature",0, 200,150,400);
		externalTimeSeriesChart[2] = new GRAPH_BasicTimeSeriesChart("Oil Temperature","Time","°C","Temperature",0,200,150,400);
		externalTimeSeriesChart[3] = new GRAPH_BasicTimeSeriesChart("Pedal Force","Time","Force (N)",pedalForceTitle,0,100,150,400,2);
		
		
		addExternalChartsToExternalPanell();
		
		 testChart= new GRAPH_BasicMeterChart();
		externalSensor_PAN.add(testChart.createPanel());
		this.add(externalSensor_PAN);
		
			}
	
	
	private void createECUSensorsPanel()
	{
		ecuSensor_PAN = new JPanel(new FlowLayout());
		
	}
	
	
	private void addExternalChartsToExternalPanell()
	{
		for(int i = 0; i<externalTimeSeriesChart.length;i++)
		{
			externalTimeSeriesEnabled[i] =false;
			externalSensor_PAN.add(externalTimeSeriesChart[i].getChartPanel());
			setTimeSeriesChartSize(externalTimeSeriesChart[i]);
		}
	}
	
	
	public void updateExternalSensorsPanel(float value)
	{
		Date date = new Date();
		externalTimeSeriesChart[0].addPoint(value,date,0);
		
		
		/*while (true)
		{
			int counter =0;
			Random rand = new Random();
			for(int i = 0; i<externalTimeSeriesChart.length;i++)
			{
				float point = rand.nextFloat();
				Date currentDate = new Date();
				externalTimeSeriesChart[i].addPoint(point,currentDate,0);
			}
			float point = rand.nextFloat();
			Date currentDate = new Date();
			externalTimeSeriesChart[3].addPoint(point*100,currentDate,1);
			
			testChart.addPoint(counter);
			counter++;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}*/
	}
	
	private void setTimeSeriesChartSize(GRAPH_BasicTimeSeriesChart chart)
	{
		double width = parent_FRA.getWidth();
		double width2 = (width) /3;
		System.out.println(width2);
		chart.setWidth(width2);
	
	}


	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		for ( int i = 0; i<externalTimeSeriesChart.length;i++)
		{
			setTimeSeriesChartSize(externalTimeSeriesChart[i]);
		}
		this.repaint();
	}


	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


}

package graphs;

import java.awt.Font;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.general.DefaultValueDataset;

public class GRAPH_BasicMeterChart {
	MeterPlot meterPlot;
	JFreeChart meterChart;
	DefaultValueDataset data; 
	
	public GRAPH_BasicMeterChart()
	{
		//
		data = new DefaultValueDataset(20.0);
		//Create MeterPlot
		meterPlot = new MeterPlot(data);
		meterChart = createMeterChart();
	}
	
	public ChartPanel createPanel()
	{
		return new ChartPanel(this.meterChart);
	}
	
	public void addPoint(int number)
	{
		data.setValue(number);
	}
		
	private JFreeChart createMeterChart()
	{
		JFreeChart temp_Chart = new JFreeChart("Speed",new Font("Gill Sans MT",Font.TRUETYPE_FONT,20),meterPlot,false);
		return temp_Chart;
	}


}



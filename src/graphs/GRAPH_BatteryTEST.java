package graphs;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;



public class GRAPH_BatteryTEST  {
	
	
	
	public GRAPH_BatteryTEST()
	{
		super("Battery Graph");
		
		
	}

	
	public JFreeChart createChart(XYDataset dataSet)
	{
		JFreeChart chart = ChartFactory.createTimeSeriesChart("Battery Voltages", "Time","Potential Difference (V)", dataSet);
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.GRAY);
		plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
		
		XYItemRenderer r = plot.getRenderer();
		if (r instanceof XYLineAndShapeRenderer)
		{
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
			renderer.setBaseShapesVisible(true);
			renderer.setBaseShapesFilled(true);
			renderer.setDrawSeriesLineAsPath(true);
		}
	
		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("MM-yyyy"));
		
		return chart;
	}
	
	private XYDataset createDataset()
	{
		TimeSeries s1 = new TimeSeries("Battery");
		s1.add(new Month(2, 2001), 181.8);
	       s1.add(new Month(3, 2001), 167.3);
		     s1.add(new Month(4, 2001), 153.8);
		       s1.add(new Month(5, 2001), 167.6);
		      s1.add(new Month(6, 2001), 158.8);
		       s1.add(new Month(7, 2001), 148.3);
		     s1.add(new Month(8, 2001), 153.9);
		     s1.add(new Month(9, 2001), 142.7);
		      s1.add(new Month(10, 2001), 123.2);
		      s1.add(new Month(11, 2001), 131.8);
		      s1.add(new Month(12, 2001), 139.6);
		      s1.add(new Month(1, 2002), 142.9);
		     s1.add(new Month(2, 2002), 138.7);
      s1.add(new Month(3, 2002), 137.3);
		        s1.add(new Month(4, 2002), 143.9);
      s1.add(new Month(5, 2002), 139.8);
		s1.add(new Month(6, 2002), 137.0);
		s1.add(new Month(7, 2002), 132.8);
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(s1);
		return dataset;
	}
	
	public JPanel createTestPanel()
	{
		JFreeChart chart = createChart(createDataset());
		ChartPanel panel = new ChartPanel(chart);
		panel.setMouseWheelEnabled(true);
		panel.setFillZoomRectangle(true);
		return panel;
	}
}
	
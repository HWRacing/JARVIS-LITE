package startprogram;

import java.io.File;
import java.util.Date;

import javax.swing.JOptionPane;

import fileconfiguration.GUI_COMPort;
import mainframe.GUI_MainFrame;
import txrx.XBeeRx;

public class StartProgram {
GUI_MainFrame mainFrame_FRA;
File[] file_F = new File[4];


//XBee Receiving object
XBeeRx rxXbee;

	public StartProgram()
	{
		file_F[0] = new File("D://Desktop/JARVIS LITE//");
		file_F[1] = new File("D://Desktop/JARVIS LITE//");
		file_F[2] = new File("D://Desktop/JARVIS LITE//");
		file_F[3] = new File("D://Desktop/JARVIS LITE//");
		mainFrame_FRA= new GUI_MainFrame(this,file_F );
		Thread tMainFrame_FRA = new Thread(mainFrame_FRA);
		tMainFrame_FRA.start();
		

	}
	public void updateTestGraph(float value)
	{
		mainFrame_FRA.updateGraphs(value);
	}
	
	
	public void setCOMPortAndCreateXbee(String comPort)
	{
		
		rxXbee = new XBeeRx(this,comPort);
		rxXbee.startReading();
	}
	
	
	public void comPortNotValid()
	{
		JOptionPane.showMessageDialog(mainFrame_FRA, "Connection to XBee Failed \nPlease ensure that you have selected the correct port","Cannot Connect Error",JOptionPane.ERROR_MESSAGE);
		GUI_COMPort getDifferentPort = new GUI_COMPort(mainFrame_FRA,this);
	}
	
	public static void main(String [] args)
	{
		StartProgram test = new StartProgram();
	}
}

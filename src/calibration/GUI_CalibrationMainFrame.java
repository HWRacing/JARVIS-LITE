package calibration;

//Importing Libraries
import javax.swing.*;

import mainframe.GUI_MainFrame;

import java.awt.*;
import startprogram.StartProgram;

/**Class to Show A Calibration Options for Calibrating Sensors*/
public class GUI_CalibrationMainFrame extends JFrame {

	//Variables to hold the startProgram and the mainFrame
	StartProgram startProgram;
	GUI_MainFrame main_FRA;
	
	public GUI_CalibrationMainFrame(StartProgram startProgram,GUI_MainFrame mainFrame)
	{
		this.startProgram = startProgram;
		this.main_FRA = mainFrame;
	}
	
	
	//Method to initialise the GUI
	private void initialiseGUI()
	{
		this.setTitle("Calibrate Sensors");
		this.setSize("400,400");
		
	}
	
}

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
	
	GUI_LinearPot linearPot_PAN;
	
	JPanel okCancel_PAN;
	JButton ok_BUT;
	JButton cancel_BUT;
	
	public GUI_CalibrationMainFrame(StartProgram startProgram,GUI_MainFrame mainFrame)
	{
		this.startProgram = startProgram;
		this.main_FRA = mainFrame;
	}
	
	
	//Method to initialise the GUI
	private void initialiseGUI()
	{
		this.setLayout(new BorderLayout());
		this.setTitle("Calibrate Sensors");
		this.setSize(400,400);
		
		
		linearPot_PAN = new GUI_LinearPot();
		this.add(linearPot_PAN);
		
		//Okay Cancel Panel
		okCancel_PAN = new JPanel(new GridLayout(1,4));
		
		ok_BUT = new JButton("OK");
		cancel_BUT = new JButton("Cancel");
		JLabel temp_LAB = new JLabel();
		okCancel_PAN.add(temp_LAB);
		okCancel_PAN.add(ok_BUT);
		okCancel_PAN.add(cancel_BUT);
		okCancel_PAN.add(temp_LAB);
		
		
		
		
		
	}
	
}

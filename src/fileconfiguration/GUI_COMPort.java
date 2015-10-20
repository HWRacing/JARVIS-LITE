package fileconfiguration;

import javax.swing.*;

import mainframe.GUI_MainFrame;
import startprogram.StartProgram;

public class GUI_COMPort extends JOptionPane{
	//Start Program
	StartProgram startProgram;
	
	//Parent Frame
	GUI_MainFrame main_FRA;
	
	String [] comPortStrings;
	String comPortSelectedByUser;
	
	public GUI_COMPort(GUI_MainFrame mainFrame,StartProgram startProgram)
	{
		this.startProgram = startProgram;
		main_FRA = mainFrame;
		initialiseGUI();
	}
	
	public void initialiseGUI()
	{
			comPortStrings = new String[8];
			comPortStrings[0] = new String("COM1");
			comPortStrings[1] = new String("COM2");
			comPortStrings[2] = new String("COM3");
			comPortStrings[3] = new String("COM4");
			comPortStrings[4] = new String("COM5");
			comPortStrings[5] = new String("COM6");
			comPortStrings[6] = new String("COM7");
			comPortStrings[7] = new String("COM8");
 		
			 comPortSelectedByUser = (String) JOptionPane.showInputDialog(null, "Please select the COM port that the Xbee is connected to.\nPlease See Device Manager.","Select COM Port", JOptionPane.OK_CANCEL_OPTION, null, comPortStrings, comPortStrings[0]);
			
			 if (comPortSelectedByUser.equals(null))
			 {
				 main_FRA.closeProgram();
					System.exit(1);
			 }else
			 {
			 startProgram.setCOMPortAndCreateXbee(comPortSelectedByUser);
			 }
	}
	
	public String returnComPort()
	{
		return comPortSelectedByUser;
	}
	
}
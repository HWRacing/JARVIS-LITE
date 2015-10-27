package calibration;

import javax.swing.*;
import java.awt.*;

/*Class For the Linear Potentiometer's Panel*/
public class GUI_LinearPot extends JPanel {

	
	public GUI_LinearPot() 
	{
		initialiseGUI();
	}
	
	//Initialise GUI
	private  void initialiseGUI()
	{
		this.setLayout(new GridLayout(1,2));
		
		JLabel initialisePot_LAB = new JLabel("Suspension Travel Sensor (Linear Potentiometer)");
		JButton initialisePot_BUT = new JButton("Initialise");
		this.add(initialisePot_LAB);
		this.add(initialisePot_BUT);
	}
}

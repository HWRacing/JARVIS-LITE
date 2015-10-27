package mainframe;
//Importing Swing Libraries
import javax.swing.*;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXStatusBar;

import fileconfiguration.GUI_COMPort;
import fileconfiguration.GUI_FileConfig;
import graphs.GRAPH_BatteryTEST;
import graphs.GRAPH_CoolantTemperature;

import java.awt.event.*;
import java.io.File;
import java.util.Date;
import java.awt.*;

//Importing Objects from other namespaces
import logger.GUI_Logger;
import startprogram.StartProgram;

public class GUI_MainFrame extends JXFrame implements Runnable,WindowListener {
//Reference to Parent Program
	StartProgram startProgram;
	
	
//Panel Variables
	
	//Status Bar Variables
	GUI_StatusBar status_BAR;
	Thread tStatusBar;
	
	//Logger Variables
	JPanel logger_PAN;
	Thread tLogger;
	
	//Variable which holds where each file is
	File[] file_F = new File[4];
	
	//User Variables
	User[] users = new User[1];
	
	//COMPort Variable
	String comPort = null;
	
	// Menu Bar Variables
	GUI_MenuBar menu_BAR;
	
	//Variables for Graphs Panel
	JScrollPane graphs_SP;
	GUI_GraphsPanel graphs_PAN;
	
	public GUI_MainFrame(StartProgram startProgram, File[] file_F)
	{
		this.startProgram = startProgram;
		this.file_F = file_F;
		users[0] = new User("bwt3","Bruce Thomson");

	}
	
	private void settingWindowsLookAndFeel()
	{
		//Setting Windows Look and Feel
				try {
					  UIManager.setLookAndFeel(
					            UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	//Method to initialise GUI
	private void initialiseGUI()
	{	
			//Setting OS Look and Feel
			settingWindowsLookAndFeel();
			
			//Create Select COMPORT Window
			GUI_COMPort comPort_FRA = new GUI_COMPort(this,startProgram);
			comPort_FRA.setVisible(true);
			
			//Create FileConfig Window
			GUI_FileConfig fileConfig_FRA = new GUI_FileConfig(this);
			Thread tFileConfig_FRA = new Thread(fileConfig_FRA);
			tFileConfig_FRA.start();
			
			//Setting Frame Name
			this.setTitle("JARVIS LITE");
			ImageIcon icon = new ImageIcon("Icon.JPG");
			this.setIconImage(icon.getImage());
			//Setting Maximised
			this.setExtendedState(JXFrame.MAXIMIZED_BOTH);
			this.setVisible(true);
	
			//Add Panels
			
			
			
			addPanels();
	}
	
	private void initaliseStatusBar()
	{
		status_BAR = new GUI_StatusBar(this);
		this.setStatusBar(status_BAR);
		tStatusBar = new Thread(status_BAR);
		tStatusBar.start();
	}
	
	private void initialiseScrollPane()
	{

		graphs_PAN = new GUI_GraphsPanel(this);
		graphs_SP = new JScrollPane(graphs_PAN);
		graphs_SP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		graphs_SP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.add(graphs_SP,BorderLayout.CENTER);
		
	}
	
	//Method to Add Panels to Main Frame
	private void addPanels()
	{
		//Initialising Status Bar
		initaliseStatusBar();

		
		//Menu Bar Instantiation
		menu_BAR = new GUI_MenuBar(this);
		this.setJMenuBar(menu_BAR);
		
		//Setting Layout of Window to BorderLayout
		this.setLayout(new BorderLayout());
		
		//Adding Logger
		logger_PAN = new GUI_Logger(this);
		
		
		//Adding Logger to Bottom
		this.add(logger_PAN,BorderLayout.SOUTH);
	
		//Creating ScrollPane for Graphs
		initialiseScrollPane();
		
	}
	
	//GRAPH_BatteryTEST testGraph = new GRAPH_BatteryTEST();
	//this.add(testGraph.createTestPanel(),BorderLayout.CENTER);
		
	
	
	
	
	public void run() {
		initialiseGUI();
	}

	//Mutator Method 
	public void updateFilePaths(File[] files_F) {
		
		this.file_F = files_F;
	}
	//Method to get the FilesPaths for Users and Raw Data etc
	public File[] getFiles()
	{
		return file_F;
	}
	
	public User[] getUsers()
	{
		return users;
	}
	
	// Window Listener to close the window when the close button is pressed
	
	
	public void closeProgram()
	{
		System.exit(DISPOSE_ON_CLOSE);
	}
	
	public void setRecording(boolean recording)
	{
		status_BAR.setRecording(recording);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		closeProgram();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateGraphs(float value)
	{
		graphs_PAN.updateExternalSensorsPanel(value);
	}
	
}

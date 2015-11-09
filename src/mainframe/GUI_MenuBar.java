package mainframe;
//importing Java GUI Libraries
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import fileconfiguration.GUI_COMPort;
import fileconfiguration.GUI_FileConfig;
import mainframe.GUI_MainFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class GUI_MenuBar extends JMenuBar{
	//Variables
	GUI_MainFrame mainFrame_FRA;
	
	//Recording State Variables
	boolean recording;
	
	//Menu 
	JMenu [] menus = new JMenu[6];

	//File Menu Items
	JMenuItem [] fileMenuItems= new JMenuItem[5];
	GUI_FileConfig file_Config_FRA;
	
	//ECU Menu Items
	 JCheckBoxMenuItem [] ecuMenuItems = new  JCheckBoxMenuItem[8];
	
	 //External Sensors Menu Items
	 JCheckBoxMenuItem[] externalSensorMenuItems = new JCheckBoxMenuItem[7];
	
	public GUI_MenuBar(GUI_MainFrame mainFrame){
		this.mainFrame_FRA = mainFrame;
		
		
		initialiseMenuBar();
		initialiseFileMenu();
		addActionListenerToRecord();
		initialiseECUMenu();
		initialiseExternalSensorsMenu();
		
	}
	
	private void initialiseMenuBar()
	{
		menus[0] = new JMenu("File, Settings and Calibration");
		menus[1] = new JMenu("Start Recording");
		recording = false;
		menus[2] = new JMenu("External Sensors");
		menus[3] = new JMenu("ECU Data");
		menus[4] = new JMenu("Driver Training");
		menus[5] = new JMenu("Help");
		
		//Add Menus to MenuBar
		for (int i = 0; i<menus.length;i++)
		{
			this.add(menus[i]);
		}
		
	}
	
	private void initialiseFileMenu()

	{
		fileMenuItems[0] = new JMenuItem("File Path Settings");
		fileMenuItems[1] = new JMenuItem("Calibration");
		fileMenuItems[2] = new JMenuItem("Xbee Connection");
		fileMenuItems[3] = new JMenuItem("Open XCTU");
		fileMenuItems[4] = new JMenuItem("Close");
		
		//ActionListeners for File Menu
		fileMenuItems[0].addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e) {
				file_Config_FRA= new GUI_FileConfig(mainFrame_FRA);
				Thread tfile_Config_FRA = new Thread(file_Config_FRA);
				tfile_Config_FRA.start();
				file_Config_FRA.setVisible(true);
			}
				});
		
		fileMenuItems[2].addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				GUI_COMPort comPort_JOption = new GUI_COMPort(mainFrame_FRA,mainFrame_FRA.getStartProgram());
				comPort_JOption.setVisible(true);
			}
				});
		fileMenuItems[3].addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				openXCTU();
			}
				});
		fileMenuItems[4].addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				mainFrame_FRA.closeProgram();
			}
				});
		
		
		for(int i =0;i<fileMenuItems.length;i++)
		{
			menus[0].add(fileMenuItems[i]);
		}
	}
	
	private void addActionListenerToExternalSensorsMenu()
	{
		for(int i=0;i<externalSensorMenuItems.length;i++)
		{
			externalSensorMenuItems[i].addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) {
					JMenuItem activatedItem =(JMenuItem)  e.getSource();
					String itemString = activatedItem.getText();
					//Case Statement to deal to enable the correct item.
					
					switch (itemString)
					{
					case "Battery" : 
						{
							
						}
					case "Fuel Map":
						{
							
						}
					case "General Information":
						{
							
						}
					case "Inlet Air Pressure":
						{
						
						}
					case "Lambda":
						{
						
						}
					case "Manifold Air Pressure":
						{
						
						}
					case "Spark Map":
						{
						
						}
					case "Water Temperature":
						{
						
						}
					case "Coolant Temperature":
						{
							mainFrame_FRA.getGraphsPanel().enableCoolantTemperature();
						}
					case "Exhaust Temperature":
						{
							
						}
					case "GPS":
						{
						
						}
					case "Oil Temperature":
						{
				
						}
					case "Pedal Forces":
						{
						
						}
					case "Steering Angle":
						{
						
						}
					case "Wheel Speed":
						{
						
						}
					}
					
				}
				
			});
		}
	}
	
	private void addActionListenerToRecord()
	{
		menus[1].addMenuListener(new MenuListener()
				{
			public void menuSelected(MenuEvent e) {
				if (recording==false)
				{
					recording =true;
					menus[1].setText("Stop Recording");
				}else
				{
					recording =false;
					menus[1].setText("Start Recording");
				}
				mainFrame_FRA.setRecording(recording);
				
				
			}

	
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
				});
	}
	
	private void initialiseECUMenu()
	{
		ecuMenuItems[0] = new  JCheckBoxMenuItem("Battery");
		ecuMenuItems[1] = new  JCheckBoxMenuItem("Fuel Map");
		ecuMenuItems[2] = new JCheckBoxMenuItem("General Information");
		ecuMenuItems[3] = new  JCheckBoxMenuItem("Inlet Air Pressure");
		ecuMenuItems[4] = new  JCheckBoxMenuItem("Lambda");
		ecuMenuItems[5] = new  JCheckBoxMenuItem("Manifold Air Pressure");
		ecuMenuItems[6] = new JCheckBoxMenuItem("Spark Map");
		ecuMenuItems[7] = new  JCheckBoxMenuItem("Water Temperature");

		
		for(int i = 0;i<ecuMenuItems.length;i++ )
		{
			ecuMenuItems[i].setSelected(false);
			menus[3].add(ecuMenuItems[i]);
		}
		
	}
	
	private void initialiseExternalSensorsMenu()
	{
		externalSensorMenuItems[0] = new JCheckBoxMenuItem("Coolant Temperature");
		externalSensorMenuItems[1] = new JCheckBoxMenuItem("Exhaust Temperature");
		externalSensorMenuItems[2] = new JCheckBoxMenuItem("GPS");
		externalSensorMenuItems[3] = new JCheckBoxMenuItem("Oil Temperature");
		externalSensorMenuItems[4] = new JCheckBoxMenuItem("Pedal Forces");
		externalSensorMenuItems[5] = new JCheckBoxMenuItem("Steering Angle");
		externalSensorMenuItems[6] = new JCheckBoxMenuItem("Wheel Speed");
	
		for(int i = 0;i<externalSensorMenuItems.length;i++ )
		{
			externalSensorMenuItems[i].setSelected(false);
			menus[2].add(externalSensorMenuItems[i]);
		}
		
		addActionListenerToExternalSensorsMenu();
	
	}
	
	
	
	private void openXCTU()
	{
			Runtime runTime = Runtime.getRuntime();
			File pathOfXCTU = new File("C://Program Files (x86)//Digi/XCTU/X-CTU.exe");
			try {
				if (pathOfXCTU.exists())
				{
					Process process =runTime.exec("C://Program Files (x86)//Digi/XCTU/X-CTU.exe runas /profile /user:Administrator");
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
}

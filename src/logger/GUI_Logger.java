package logger;
import mainframe.GUI_MainFrame;
import mainframe.User;

/* This program is a class which extends JPanel which contains the code for the logger
 * 
 * 
 * 
 */

//Import Java Swing and IO Libraries
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.text.*;
import java.util.Date;
import java.util.Properties;

public class GUI_Logger extends JPanel implements Runnable  {
	
	
	//JPanel for setting Grid Layout for Radio Buttons and Username
	JPanel subPanelForRadioPassword;
	
	//Radio Button Variables and Panel
	JRadioButton [] typeOfLog_RB = new JRadioButton[8];
	ButtonGroup typeOfLog_RBG;
	JPanel typeOfLog_PAN;
	
	//Username Panel
	JLabel username_LAB;
	JTextField username_TF;
	JPanel username_PAN;
	JButton messageSave_BUT;
	String validUsernames; 
	
	//Multiline TextField for Inputting Text
	JPanel userComment_PAN;
	JTextArea userComment_TA;
	JTextArea previousComment_TA;
	JScrollPane previousComment_SP;
	
	//Variables for where to store files
	File logFilePath;
	
	//Variables for Users.ini File
	File[] files_F;
	User[] users_U;
	
	//Contructor
	public GUI_Logger(GUI_MainFrame mainframe)
	{
		GUI_MainFrame parent_FRA = mainframe;
		files_F = parent_FRA.getFiles();
		users_U = parent_FRA.getUsers();
		initialiseGUI();
		
		logFilePath = createLogFile();

	}
	
	
	//Initialise Components in GUI
	private void initialiseGUI()
	{
		//Splitting Panel
		this.setLayout(new GridLayout(1,2));
		
		//SubPanel for Radio Buttons and Password
		subPanelForRadioPassword = new JPanel(new GridLayout(1,2));
		this.add(subPanelForRadioPassword);
		
		// Method call to initalising Radio Buttons 
		initialiseRadioButtons();
		
		//Method call to initalising Send Button
		initialiseUsername();
		
		//Method call to initalising Text Field
		initialiseTextArea();
	
	}
	
	private void initialiseRadioButtons()
	{
		//Initalising JPanel for buttons to go into
		typeOfLog_PAN = new JPanel(new GridLayout(4,2));
		subPanelForRadioPassword.add(typeOfLog_PAN);
		
		/* Creating Radio Buttons where
		 * 0 = General Technical, 1= Chassis,2=Driver 3= Engine and Drivetrain, 4 = Electrics, 5 = Vehicle Dynamics, 6 = HSE, 7= Logistics 
		 */
		typeOfLog_RBG = new ButtonGroup();
		
		
		typeOfLog_RB[0] = new JRadioButton("General Technical");
		typeOfLog_RB[0].setSelected(true);
		typeOfLog_RB[1] = new JRadioButton("Chassis");
		typeOfLog_RB[2] = new JRadioButton("Driver");
		typeOfLog_RB[3] = new JRadioButton("Engine");
		typeOfLog_RB[4] = new JRadioButton("Electrics");
		typeOfLog_RB[5] = new JRadioButton("Vehicle Dynamics");
		typeOfLog_RB[6] = new JRadioButton("HSE");
		typeOfLog_RB[7] = new JRadioButton("Logistics");
		
		for (int i = 0; i<typeOfLog_RB.length; i++)
		{
			typeOfLog_RBG.add(typeOfLog_RB[i]);
			typeOfLog_PAN.add(typeOfLog_RB[i]);
		}
		
		
	}
	
	
	private void initialiseUsername()
	{
		//Initialise Username JPanel for Username
		username_PAN = new JPanel(new GridLayout(3,1));
		subPanelForRadioPassword.add(username_PAN);
		
		//Initialise Label and Text Field and Button for Information
		username_LAB = new JLabel("User:");
		username_TF = new JTextField("",10);
		messageSave_BUT = new JButton("Save");
		
		//Add Label, Text Field and Button to JPanel
		username_PAN.add(username_LAB);
		username_PAN.add(username_TF);
		username_PAN.add(messageSave_BUT);
	
		//Adding Action Listener for the save button
		messageSave_BUT.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						saveButtonActivated();
					}
				}
				);
	}
	
	private void initialiseTextArea()
	{
		userComment_PAN = new JPanel();
		userComment_PAN.setLayout(new BorderLayout());
		this.add(userComment_PAN);
		
		//Edible Text Area for User to Type
		userComment_TA = new JTextArea(2,40);
		userComment_TA.setEditable(true);
		userComment_PAN.add(userComment_TA,BorderLayout.SOUTH);
		
		//Non Edible Text Area for Previously Saved Logs
		previousComment_TA =  new JTextArea(1,20);
		previousComment_TA.setEditable(false);
		previousComment_SP = new JScrollPane(previousComment_TA);
		previousComment_SP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		previousComment_SP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		userComment_PAN.add(previousComment_SP, BorderLayout.CENTER);
		
	}

	private String getDateForFile()
	{
		DateFormat fileDateFormat = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
		Date dateWhenCreated = new Date();
		return  fileDateFormat.format(dateWhenCreated);
	}
	
	public File createLogFile()
	{
		String filePathString = files_F[3] + "//" + "Log_" + getDateForFile() + ".txt";
		File logFilePath = new File(filePathString);
		FileWriter fileCreator;
		try {
			fileCreator = new FileWriter(logFilePath);
			fileCreator.write("");
			fileCreator.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return logFilePath;
		}
	
private void writeLogToFile(String textToBeWritten)

{
	FileWriter appendToFile;
		try{
			appendToFile = new FileWriter(logFilePath,true);
			appendToFile.write(textToBeWritten);
			appendToFile.write(System.getProperty( "line.separator" ));
			appendToFile.close();
		}catch (IOException e)
		{
			e.printStackTrace();
		}
}

private String constructStringToBeWritten()
{
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	Date currentTime = new Date();
	String timeAndDate = dateFormat.format(currentTime) + "T" + timeFormat.format(currentTime);
	
	return timeAndDate + " - " + checkUserName().getName() + " - " + (checkForRadioButton()) +  " - " + userComment_TA.getText() ;
}

private User checkUserName()
{
	boolean userNameValid = false;
	if (username_TF.getText().equals(""))
	{
		JOptionPane.showMessageDialog(null,"Please enter a user.","No User",JOptionPane.WARNING_MESSAGE);
		return null;
	}else
		for (User userTemp: users_U)
		{
			if (username_TF.getText().equals(userTemp.getUserName()))
					{
						userNameValid = true;
						return userTemp;		
					}
		}
	JOptionPane.showMessageDialog(null,"Please a valid User.","User Not Valid",JOptionPane.WARNING_MESSAGE);
	return null;
}

private boolean checkForText()
{
	if (userComment_TA.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please enter a comment.","No Comment",JOptionPane.WARNING_MESSAGE);
				return false;
			}
	return true;
}

private String checkForRadioButton()
{
	for (int i =0; i<typeOfLog_RB.length;i++)
	{
		if (typeOfLog_RB[i].isSelected())
		{
			return typeOfLog_RB[i].getText();
		}
	}
	return null;
}

//Method activated when Save Button activated
private void saveButtonActivated()
{
	if (checkUserName() !=null && checkForText() == true)
	{
		String textWritten = constructStringToBeWritten();
		writeLogToFile(textWritten);
		printToTextArea(textWritten);
		clearUserCommentArea();
		
	}
}

//Method to clear user comment and username area;
private void clearUserCommentArea()

{
	username_TF.setText("");
	userComment_TA.setText("");
}

private void printToTextArea(String newComment)
{
	Dimension tempSize = previousComment_TA.getSize();
	tempSize.setSize(tempSize.getHeight()+1,tempSize.getWidth());
	previousComment_TA.setSize(tempSize);
	String previousComTemp = previousComment_TA.getText();
	previousComment_TA.setText(newComment + "\n" + previousComTemp);

}

private void readUsersIn()
{

		try {
			Properties properties = new Properties();
			FileReader reader = new FileReader(files_F[2]);
			properties.load(reader);	
			reader.close();
			validUsernames = properties.getProperty("user");
			System.out.println(validUsernames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

}

//Runnable 
public void run() {
	//

}
}


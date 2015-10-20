package mainframe;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdesktop.swingx.JXStatusBar;
import mainframe.GUI_MainFrame;

public class GUI_StatusBar extends JXStatusBar implements Runnable {
	//Parent Frame Variable
	GUI_MainFrame parent_FRA;
	
	//Status Bar Variables
	JPanel bar_PAN;
	JLabel time_LAB;
	JLabel GPS_LAB;
	JLabel recording_LAB;
	
	public GUI_StatusBar(GUI_MainFrame parentFrame)
	{
		parent_FRA = parentFrame;
		 initialiseBar();
	}
	
	public void initialiseBar()
	{
		
		Font barFont_FONT = new Font("Gill Sans MT",Font.TRUETYPE_FONT,14);
		
		//TimeBar
		time_LAB = new JLabel("Time:");
		time_LAB.setFont(barFont_FONT);
		this.add(time_LAB);
		
		//Recording Lab
		recording_LAB=new JLabel("Not Recording");
		recording_LAB.setFont(barFont_FONT);
		this.add(recording_LAB);
		
		
		//GPS Lab
		GPS_LAB = new JLabel("GPS:");
		GPS_LAB.setFont(barFont_FONT);
		this.add(GPS_LAB);
		
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true)
		{
			try {
				time_LAB.setText("Time: " + generateTime());
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private String generateTime()
	{
		Date currentTime = new Date();
		SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
		return formatTime.format(currentTime);
	}
	
	public void setRecording(boolean recording)
	{
		if (recording == true)
		{
			recording_LAB.setText("Recording");
			recording_LAB.setForeground(Color.RED);
		}else
		{
			recording_LAB.setText("Not Recording");
			recording_LAB.setForeground(Color.BLACK);
		}
	}

	}
	

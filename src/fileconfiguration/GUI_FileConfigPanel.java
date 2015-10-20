package fileconfiguration;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class GUI_FileConfigPanel extends JPanel{
	String typeOfData;
	
	JLabel path_LAB;
	JTextField path_TF;
	JButton browse_BUT;
	JFileChooser file_FC;
	File file_F;
	
	public GUI_FileConfigPanel(String typeOfData,File file)
	{
		file_F= file;
		this.typeOfData = typeOfData;
		path_LAB =new JLabel("Path:");
		path_TF = new JTextField("null");
		browse_BUT = new JButton("Browse");
		
		Font defaultFont = new Font("Gill Sans MT",Font.TRUETYPE_FONT,14);
		path_LAB.setFont(defaultFont);
		path_TF.setFont(defaultFont);
		browse_BUT.setFont(defaultFont);
		
		this.setLayout(new BorderLayout());
		this.add(path_LAB,BorderLayout.WEST);
		this.add(path_TF, BorderLayout.CENTER);
		this.add(browse_BUT, BorderLayout.EAST);
		
		//Adding Default Path
		if (file_F.exists())
		{
			path_TF.setText(file_F.getAbsolutePath());
		}

		
		//Create a File Chooser
		file_FC = new JFileChooser(file_F);
		if ((typeOfData.equals("RAW Files"))||(typeOfData.equals("Processed Files")||(typeOfData.equals("Testing Log"))))
		{
				file_FC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}
		
		file_FC.setDialogTitle(typeOfData);
		
		//ActionListener for Browse Button
		browse_BUT.addActionListener(new ActionListener()
				{
				
				public void actionPerformed(ActionEvent e)
				{
					int returnValue = file_FC.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION)
					{
						file_F = file_FC.getSelectedFile();
						path_TF.setText( file_F.getAbsolutePath());
					}
				}
				});
}
	public File getFile()
	{
		return file_F;
	}
	
	
}

package rawfilewriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.*;
import java.util.Date;

public class RawFileWriter {

	int writeCounter;
	File filePath;
	String currentFilePath;
	FileOutputStream fileOutputStream;
	
	public RawFileWriter(File filePath)
	{
		this.filePath = filePath;
	}
	//Mutator Method to set the filePath
	public void setFilePath(File path)
	{
		filePath = path;
	}
	
	
	private void setCurrentFilePath()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyyddMMTHH:mm:ss");
		Date currentDateTime = new Date();
		String tempString = dateFormat.format(currentDateTime);
		
		currentFilePath= filePath.toString() + "//" + tempString + ".bin";
		
	}
	
	public void writeToBinaryFile(byte[] byteToWrite) throws FileNotFoundException
	{
		//Create a new FileOutputStream if the file doesn't exist
		if (writeCounter==0)
		{
			fileOutputStream = new FileOutputStream(currentFilePath);
		}
		
		if (writeCounter>1000)
		{
			setCurrentFilePath();
			fileOutputStream = new FileOutputStream(currentFilePath);
		}
		
		try {
			fileOutputStream.write(byteToWrite);
			writeCounter++;
			if(writeCounter%10==0)
			{
				fileOutputStream.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
}
	//Method to close the file outputstream when the program is stopped recording
	public void closeFIle()
	{
		try {
			fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

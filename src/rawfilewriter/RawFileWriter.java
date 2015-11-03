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
		DateFormat dateFormat = new SimpleDateFormat("yyyyddMMHHmmss");
		Date currentDateTime = new Date();
		String tempString = dateFormat.format(currentDateTime);
		
		currentFilePath= filePath.toString() + System.getProperty("file.separator") + tempString + ".bin";
		System.out.println(currentFilePath);
		File currentFile = new File(currentFilePath);
		
		if (currentFile.exists()==false)
		{
			try {
				currentFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void writeToBinaryFile(byte[] byteToWrite) throws FileNotFoundException
	{	
		//Create a new FileOutputStream if the file doesn't exist
		if (writeCounter==0)
		{
			setCurrentFilePath();
			fileOutputStream = new FileOutputStream(currentFilePath);
		}
		
		if (writeCounter/100==0)
		{
			fileOutputStream = new FileOutputStream(currentFilePath,true);
		}
		
		if (writeCounter>1000)
		{
			setCurrentFilePath();
			fileOutputStream = new FileOutputStream(currentFilePath);
			writeCounter = 0;
		}
		
		try {
			fileOutputStream.write(byteToWrite);
			writeCounter++;
			if(writeCounter%100==0)
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

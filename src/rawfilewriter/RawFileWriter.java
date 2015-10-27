package rawfilewriter;

import java.io.File;
import java.text.*;
import java.util.Date;

public class RawFileWriter {

	int writeCounter;
	File filePath;
	String currentFilePath;
	FileOutputStream
	
	public RawFileWriter()
	{
		
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
	
	public writeToBinaryFile(byte[] byteToWrite)
	{
		if (writeCounter==10000)
		{
			
		}
		
		
	}
	
}

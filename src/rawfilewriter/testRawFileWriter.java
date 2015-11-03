package rawfilewriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class testRawFileWriter {

	
	public testRawFileWriter()
	{
		File testFile = new File("D://Desktop//JARVIS LITE//TestFileWriter");
		
		RawFileWriter testFileWriter = new RawFileWriter(testFile);
		
		byte [] testArray = new byte[6];
		testArray[0] = 100;
		testArray[1] = 50;
		testArray[2] = 75;
		testArray[3] = -24;
		testArray[4] = 6;
		testArray[5] = 100;
		
		Random rand = new Random();
		byte [] byteToWrite = testArray;
				
		try {
			testFileWriter.writeToBinaryFile(byteToWrite);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String [] args)
	{
		testRawFileWriter test = new testRawFileWriter();
		
		
	}
}

package txrx;

import java.io.FileNotFoundException;

import com.digi.xbee.api.listeners.IDataReceiveListener;
import com.digi.xbee.api.models.XBeeMessage;

import startprogram.StartProgram;

public class Listener implements IDataReceiveListener{
	StartProgram startProgram;
	
	public Listener(StartProgram startProgram)
	{
		this.startProgram = startProgram;
	}
	
	public void dataReceived(XBeeMessage arg0) {
	        
		byte[] data = arg0.getData();
		
		//Get The FileWriter Object
		try {
			startProgram.getRawFileWriter().writeToBinaryFile(data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}

package txrx;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBeeMessage;

import startprogram.StartProgram;

public class XBeeRx {

		
		private String port; // TODO Replace with COM0,COM1, etc if Windows
		private static final int BAUD_RATE = 9600; // TODO Baud Rate in XBee modules and code must be the same
		private StartProgram program;
		
		public XBeeRx(StartProgram program,String port)
		{
			this.port = port;
			this.program = program;
		}
		
		
		public void startReading() {
			
			// Launch GUI
			
			// Create log

		    Logger logger = Logger.getLogger("XBeeLog");  
		    FileHandler fh;  

		    try {  

		        // This block configure the logger with handler and formatter  
		        fh = new FileHandler("D://Desktop/Log.log.txt");  
		        logger.addHandler(fh);
		        SimpleFormatter formatter = new SimpleFormatter();  
		        fh.setFormatter(formatter);  

		        // the following statement is used to log any messages  
		        logger.info("Start");  

		    } catch (SecurityException e) {  
		        e.printStackTrace();  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    }
		    
			XBeeDevice myDevice = new XBeeDevice(port, BAUD_RATE);
			try {
				myDevice.open();
			} catch (XBeeException e) {
				//Create a Warning Message to User saying Invalid Port
				program.comPortNotValid();
				return;
			}
			while (true) {
				XBeeMessage xbeeMessage = myDevice.readData();
				if (xbeeMessage != null) {
					String str = new String(xbeeMessage.getData());
					//System.out.println("Waiting for data...");
					System.out.println(str);	
					
					logger.info(str);
					updateGraph(str);
				}
			}
		}
		
		private void updateGraph(String value)
		{
			Date currentDate = new Date();
			float valueF= Float.parseFloat(value);
			program.updateTestGraph(valueF);
			
			
		}
}

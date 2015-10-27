package com.digi.xbee.hwracing;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBeeMessage;

public class XBeeRx {
		
		private static final String PORT = "/dev/ttyUSB0"; // TODO Replace with COM0,COM1, etc if Windows
		private static final int BAUD_RATE = 9600; // TODO Baud Rate in XBee modules and code must be the same

		public static void main(String[] args) {
			
			// Launch GUI
			GUI view = new GUI();
			view.setVisible(true);
			
			// Create log

		    Logger logger = Logger.getLogger("XBeeLog");  
		    FileHandler fh;  

		    try {  

		        // This block configure the logger with handler and formatter  
		        fh = new FileHandler("/home/ismael/Desktop/HWRacingXBees.log");  
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
		    
			XBeeDevice myDevice = new XBeeDevice(PORT, BAUD_RATE);

			try {
				myDevice.open();
			} catch (XBeeException e) {
				e.printStackTrace();
				System.exit(1);
			}

			while (true) {
				XBeeMessage xbeeMessage = myDevice.readData();
				if (xbeeMessage != null) {
					String str = new String(xbeeMessage.getData());
					view.textArea.setText(str + "\n");
					logger.info(str);
				}
			}
		}
}

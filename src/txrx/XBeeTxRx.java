package txrx;

import com.digi.xbee.api.*;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.listeners.IDataReceiveListener;

import startprogram.StartProgram;

/**This class is for the XBee Transmission and Receiving*/
public class XBeeTxRx {
	//Start Program Declaration
	private StartProgram startProgram;
	
    private static final String REMOTE_NODE_IDENTIFIER = "REMOTE";
	
	//Local XBee Device Declaration
	private int baudRate;
	private String comPort;
	private XBeeDevice localXBee;
	
	//Remote Xbee Device Declaration
	private RemoteXBeeDevice remoteXBee;
	
	//XBee Network
	private XBeeNetwork xbeeNetwork;
	
	public XBeeTxRx(int baudRate, String comPort,StartProgram startProgram) {
		this.startProgram = startProgram;
		this.baudRate = baudRate;
		this.comPort = comPort;		
		
		//Create XBee Device Object
		localXBee = new XBeeDevice(comPort,baudRate);
	}
	
	public void openConnectionToXbee()
	{
		//Try and open the connection to the xbee else throw an error message.
		try{
			localXBee.open();
			//TODO Network Configuration?
		} catch (XBeeException e) {
			e.printStackTrace();
			System.out.println("Problem connecting XBee. COM Port correct?");
			System.exit(1);
		}
		
		// Create the data reception listener
		Listener DataListener = new Listener();
		 
		// Subscribe to data reception.
		localXBee.addDataListener(DataListener);
		
		
	}
	
	
}

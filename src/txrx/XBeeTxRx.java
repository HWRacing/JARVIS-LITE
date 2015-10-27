package txrx;

import com.digi.xbee.api.*;

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
			
            // Obtain the remote XBee device from the XBee network.
            xbeeNetwork = localXBee.getNetwork();
            remoteXBee = xbeeNetwork.discoverDevice(REMOTE_NODE_IDENTIFIER);
            
            //When there is not a remote xbee show a 
            if (remoteXBee == null)
            {
            	//TODO need 
            }
		}
	}
	
	public void sendCommand()

}

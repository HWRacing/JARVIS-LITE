package XBeeCom.src.TX;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.XBeeNetwork;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.utils.HexUtils;

/**
 * XBee Java Library Send Data sample application.
 * 
 * <p>This example sends data to a remote device whose Node Identifier is 
 * 'REMOTE'.</p>
 * 
 * <p>For a complete description on the example, refer to the 'ReadMe.txt' file
 * included in the root directory.</p>
 */
public class SendMessage {
	
	/**
	 * Application main method.
	 * 
	 * @param args Command line arguments.
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	public static void main(String[] args) throws TimeoutException, XBeeException {
		
		// Launch GUI
		final GUI view = new GUI();
		view.setVisible(true);
		
		ActionListener actionListener;
		final String data = "hola";
		
		// Instantiate an XBee device object.
		final XBeeDevice myLocalXBeeDevice = new XBeeDevice("COM3", 9600);
		try {
			myLocalXBeeDevice.open();
		} catch (XBeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Instantiate a remote XBee device object.
		final RemoteXBeeDevice myRemoteXBeeDevice = new RemoteXBeeDevice(myLocalXBeeDevice,
		                                   new XBee64BitAddress("0013A20040A2B995"));
       
		actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {      
          	  String text = view.textField.getText();
        		// Send data using the remote object.
        		try {
					myLocalXBeeDevice.sendDataAsync(myRemoteXBeeDevice, data.getBytes());
				} catch (XBeeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
      };                
      view.getButton().addActionListener(actionListener);   
  } 
    
}
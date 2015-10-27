package txrx;

import com.digi.xbee.api.listeners.IDataReceiveListener;
import com.digi.xbee.api.models.XBeeMessage;

public class Listener implements IDataReceiveListener{

	public void dataReceived(XBeeMessage arg0) {
	        
		byte[] data = arg0.getData();
		//TODO Write data in .bin
		//TODO GUI
	}
	
	

}

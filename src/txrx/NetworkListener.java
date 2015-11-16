package txrx;

import startprogram.StartProgram;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.listeners.IDiscoveryListener;

/**
 * Class to manage the XBee network discovery events.
 * 
 * <p>Acts as a discovery listener by implementing the 
 * {@code IDiscoveryListener} interface, and is notified when new 
 * devices are found, an error occurs or the discovery process 
 * finishes.</p>
 * 
 * @see IDiscoveryListener
 *
 */
public class NetworkListener implements IDiscoveryListener {
	StartProgram startProgram;
	
	public NetworkListener(StartProgram startProgram)
	{
		this.startProgram = startProgram;
	}
	
	public void deviceDiscovered(RemoteXBeeDevice discoveredDevice) {
		System.out.format(">> Device discovered: %s%n", discoveredDevice.toString());
	}
	
	public void discoveryError(String error) {
		System.out.println(">> There was an error discovering devices: " + error);
	}
	
	public void discoveryFinished(String error) {
		if (error == null)
			System.out.println(">> Discovery process finished successfully.");
		else
			System.out.println(">> Discovery process finished due to the following error: " + error);
		
		System.exit(0);
	}
}

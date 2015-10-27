package calibration;

import txrx.XBeeTx;

/**This class is a calibration class which sets the sensors to the initial values as the should*/
public class Calibration {
	//XbeeTx
	XBeeTx xbeeTx;
	
	
	public Calibration(XBeeTx xbeetx)
	{
		//xbee Transmision Object
		this.xbeeTx = xbeetx;
		
	}
	
	
	public void calibrateLinearPot()
	{
		xbeeTx.sendMessage()
	}
	
	
}

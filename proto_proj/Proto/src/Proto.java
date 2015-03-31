import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Proto {
	private static InputStream protoStandardInput;
	private static OutputStream protoStandardOutput;

	
	//The interface's methods
	
	/**
	 * Makes a jump call on the robo. Pulled out from the main logic
	 * to reach integrity, and for possible further extension in future
	 * @param robo the Bot to initiate the jump on
	 */
	public void jump(Bot robo)
	{
		robo.jump();
	}
	
	
	/**
	 * Changes the robo's speed vector. Yet does nothing else.
	 * @param robo the Bot for which we change speed
	 * @param x the first value of the vector of speed
	 * @param y the second value of the speedvector
	 */
	public void changeSpeed(Bot robo, int x, int y)
	{
		robo.changeSpeedVector(x, y);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Skeleton class is used to give the needed states and make the needed decisions for the Pheobe game's structure
 * that deosn't contain the business logics in itself yet.
 */
public abstract class Skeleton {
	//Constants for the user interaction.
	private static final String hasPuttyMessage = "Does the field have a putty on it?\n (a) Yes\n(b) No\n";
	private static final String hasOilMessage = "Does the field have any oil on it?\n (a) Yes\n(b) No\n";
	private static final String invalidAnswerMessage = "Please choose from the answers above \n";
	
	/**
	 * Print out information, so user knows what's 'under the hood'.
	 * @param info The information that is to be printed out
	 */
	public static void showInfo(String info)
	{
		System.out.println(info);
	}
	
	/**
	 * This method determines whether the user wants the corresponding field to have putty on it.
	 *  Proxy to hasOstacle.
	 * @return The user's decision: true if the answer is yes, no otherwise, the default return is false
	 */
	public static boolean hasPutty()
	{
		return hasObstacle(hasPuttyMessage);
	}
	
	/**
	 * This method determines whether the user wants the corresponding field to have oil on it.
	 * Proxy to hasOstacle.
	 * @return The user's decision: true if the answer is yes, no otherwise, the default return is false
	 */
	public static boolean hasOil()
	{
		return hasObstacle(hasOilMessage);
	}

	/**
	 * This method determines whether the user wants the corresponding field to have oil or putty on it, depending
	 * on the proxy method that calls it. Its param has to inform the user that yes is 'a' no is 'b'.
	 * @param obstacleMessage A string containing a yes/no question and information about correct answers to the user.
	 * @return The user's decision: true if the answer is yes, no otherwise, the default return is false
	 */
	private static boolean hasObstacle(String obstacleMessage)
	{
		System.out.println(obstacleMessage);
		try{
			BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
	 
			String input = null;
			while((input=br.readLine())!=null){
				if(input.equalsIgnoreCase("a") || input.equalsIgnoreCase("b"))
				{
					if(input.equalsIgnoreCase("a"))
						return true;
					else
						return false;
				}
				else
				{
					System.out.println(invalidAnswerMessage);
				}
					
			}
	 
		}catch(IOException io){
			io.printStackTrace();
		}
		return false;	
	}
	
	public static void main(String[] args) {

	}

}

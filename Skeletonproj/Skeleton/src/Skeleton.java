import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Skeleton class is used to give the needed states and make the needed decisions for the Pheobe game's structure
 * that deosn't contain the business logics in itself yet.
 */
public abstract class Skeleton {
	//Constants for the user interaction.
	private static final String isPuttiedMessage = "Does the field have a putty on it?\n(a) Yes\n(b) No\n";
	private static final String isOiledMessage = "Does the field have any oil on it?\n(a) Yes\n(b) No\n";
	private static final String invalidAnswerMessage = "Please choose from the answers above \n";
	private static final String isLandingSuccessfullMassege = "Would you like the robot to succeed in landing?\n(a) Yes\n(b)No\n";
	private static final String isOccupiedMassege = "Is there a bot on the field you want land? \n(a)Yes\n(b)No\n";
	private static final String[] mainMenuItems = {"(a)Put putty on current field\n",
												   "(b)Put oil on current field\n",
												   "(c)Jump to next field\n",
												   "(d)Change speed vector\n",
												   "(x)Exit\n"};
	
	private static int oilCount = 3;
	private static int puttyCount = 3;
	private static String prefix = "";
	
	/**
	 * Print out information, so user knows what's 'under the hood'.
	 * @param info The information that is to be printed out
	 */
	public static void showInfo(String info)
	{
		if(info.charAt(info.length()-1) != '\n')
		{
			info = info.concat("\n");
		}
		System.out.println(info);
	}
	
	/**
	 * Determines whether the imaginary bot carries any oils or not.
	 * @return true if has oil, false otherwise
	 */
	public static boolean hasOil()
	{
		if(oilCount > 0)
		{
			oilCount--;
			showInfo(String.format("Bot has oil.\n%d oiles left to put", oilCount));
			return true;
		}
		else
		{
			showInfo("No oils left.\n");
			return false;
		}
	}
	
	/**
	 * Determines whether the imaginary bot carries any putties or not.
	 * @return true if has putty, false otherwise
	 */
	public static boolean hasPutty()
	{
		if(puttyCount > 0)
		{
			puttyCount--;
			showInfo(String.format("Bot has Putty.\n%d putties left to put", puttyCount));
			return true;
		}
		else
		{
			showInfo("No putties left./n");
			return false;
		}
	}
	
	/**
	 * This method determines whether the user wants the corresponding field to have putty on it.
	 *  Proxy to yesNoQuestion.
	 * @return The user's decision: true if the answer is yes, no otherwise, the default return is false
	 */
	public static boolean isPuttied()
	{
		return yesNoQuestion(isPuttiedMessage);
	}
	
	/**
	 * This method determines whether the user wants the corresponding field to have oil on it.
	 * Proxy to yesNoQuestion.
	 * @return The user's decision: true if the answer is yes, no otherwise, the default return is false
	 */
	public static boolean isOiled()
	{
		return yesNoQuestion(isOiledMessage);
	}
	
	/**
	 * This method determines whether the user wants the landing to succeed or not.
	 * Proxy to yesNoQuestion.
	 * @return The user's decision: true if the answer is yes, no otherwise, the default return is false
	 */
	public static boolean isLandingSuccessfull()
	{
		return yesNoQuestion(isLandingSuccessfullMassege);
	}
	
	/**
	 * Lets the user decide if the field the bot wants to land is occupied already by an other bot or not.
	 * Proxy to yesNoQuestion.
	 * @return The user's decision: true if the answer is yes, no otherwise, the default return is false
	 */
	public static boolean isOccupied()
	{
		return yesNoQuestion(isOccupiedMassege);
	}

	/**
	 * This method gives an answer to a yesNoQuestion. The question has to contain approptiate info so the user
	 * knows that 'a' is yes 'b' is no.
	 * @param question A string containing a yes/no question and information about correct answers to the user.
	 * @return The user's decision: true if the answer is yes, no otherwise, the default return value is false
	 */
	private static boolean yesNoQuestion(String question)
	{
		System.out.println(question);
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
	
	/**
	 * This function initiates the actual program.
	 * Based on the user's choose from the menu items starts the use-cases.
	 * @param args Possible argument of the program
	 */
	public static void main(String[] args) {
		Bot bot = new Bot();
		boolean exited = false;
		while(!exited)
		{
			System.out.println("\nChoose a menu item\n");
			for(int i = 0; i < mainMenuItems.length; i++)
			{
				System.out.println(mainMenuItems[i]);
			}
			try{
				BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		 
				String input = null;
				if((input = br.readLine())!=null){
					switch(input.charAt(0))
					{
						case 'a':
							bot.putPutty();
							break;
						case 'b':
							bot.putOil();
							break;
						case 'c':
							bot.jump();
							break;
						case 'd':
							bot.changeSpeedVector(2, 0);
							break;
						case 'x':
							exited = true;
							break;
					}
				}
		 
			}catch(IOException io){
				io.printStackTrace();
			}
		}
	}

}

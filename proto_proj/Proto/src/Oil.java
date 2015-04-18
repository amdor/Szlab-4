/**
 * Realization of the interface Obstacle. 
 * When the bot steps in oil, its function is called
 */
public class Oil implements Obstacle{

<<<<<<< HEAD
	public int roundCount;	
=======
	public int roundCount;
>>>>>>> origin/master
	
	public Oil(){
		roundCount = 0;
	}
<<<<<<< HEAD
	
=======
>>>>>>> origin/master
	/**
	 * If the bot steps in oil, the slowBot makes his DirectVector the half of what it was
	 * @param bot The bot to be make uncontrollable
	 */
	public void punishBot(Bot bot){
		bot.isDirectable = false;
<<<<<<< HEAD

=======
>>>>>>> origin/master
	}
}

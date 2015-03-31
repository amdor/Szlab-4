/**
 * Realization of the interface Obstacle. 
 * When the bot steps in putty, its function is called
 */
public class Putty implements Obstacle{

	/**
	 * If the bot steps in putty, the isDerictible is changed to false, so it's DirectVector can't be changed.
	 * @param bot The bot's DirectVector can't be changed, because it stepped on a putty.
	 */
	public void punishBot(Bot bot){
		Skeleton.showInfo("Function called: Putty: punishBot()");
		bot.slowBot();
		Skeleton.showInfo("Function ended: punishBot()");
	}
}
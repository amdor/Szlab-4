/**
 * Realization of the interface Obstacle. 
 * When the bot steps in putty, its function is called
 */
public class Putty implements Obstacle{

	public int stepCount;
	
	public Putty() {
		stepCount = 0;
	}
	
	/**
	 * If the bot steps in putty, than its speed is halfed and
	 * properly according to the stepCount, putty can destroy itself
	 * @param bot The bot to punish
	 */
	public void punishBot(Bot bot){
		bot.slowBot();
		stepCount++;
		if(stepCount >= 4) {
			bot.currentField.removeObstacle();
		}
	}
}
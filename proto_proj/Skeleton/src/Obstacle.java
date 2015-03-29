/**
 * Obstacle interface contains punishBot function, which is called when the bot steps on an oil or putty.
 */
public interface Obstacle {

	/**
	 * Makes the punishes on the bot.
	 * @param bot This bot is being punished, because it stepped on an obstacle
	 */
	public void punishBot(Bot bot);
}

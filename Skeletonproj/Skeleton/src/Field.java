
public class Field {

	private int hasBot;
	private boolean isValid;
	public int x;
	public int y;
	private Obstacle obstacle;
	
	public Field (int i, int j){
		this.x = i;
		this.y = j;
		this.hasBot = 0;
		this.isValid = true;
		this.obstacle = null;
	}
	/*
	 * Handles the landing of the bot, tells the bot, if the landing was successful, 
	 * or not, or it landed on an obstacle
	 * @param bot This bot lands on the field.
	 */
	public boolean handleLanding(Bot bot){
		Skeleton.showInfo("Function called: Field: handleLanding(Bot)");
		if(Skeleton.isOiled()){
			obstacle = new Oil();
			obstacle.punishBot(bot);
		}
		if(Skeleton.isPuttied()){
			obstacle = new Putty();
			obstacle.punishBot(bot);
		}
		if(Skeleton.isOccupied()){
			bot.collison();
			
		}
		if(Skeleton.isLandingSuccessfull())
			Skeleton.showInfo("The landing was successful\nThis field now has this Bot");
		
			
		else
			;//Amikor a robot leesik, meg kéne még írni!!!
			Skeleton.showInfo("bot destroy");
		return false;
		
	}
	
	/**
	 * When the Bot jumps from a field, to another, the Bot has to be removed from the previous field
	 * hasBot is set to false
	 */
	public void removeBotFromField(){
		Skeleton.showInfo("Function called: Field: removeBotFromField()");
		Skeleton.showInfo("The Bot was removed from this field");
	}
	
	public void setObstacle(Obstacle obs){
		
	}
	
}

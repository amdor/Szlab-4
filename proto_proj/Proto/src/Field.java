
public class Field {

	private int hasBot;
	private boolean isValid;
	private int x;
	private int y;
	private Obstacle putty;
	private Obstacle oil;
	private boolean hasOil;
	private boolean hasPutty;
	
	public Field (int i, int j){
		//Skeleton.showInfo("Function called: Field: Field(int,int)");
		this.x = i;
		this.y = j;
		this.hasBot = 0;
		this.isValid = true;
	//	Skeleton.showInfo("Function ended: Field()");
	}
	/*
	 * Handles the landing of the bot, tells the bot, if the landing was successful, 
	 * or not, or it landed on an obstacle
	 * @param bot This bot lands on the field.
	 */
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public boolean handleLanding(Machine mch){
		if(hasBot > 1){
			mch.collision();
			return false;
		}
		else return true;
	}
	
	public boolean handleLanding(Bot bot){
		if(hasOil){
			oil.punishBot(bot);
			return false;
		}
		else if(!hasOil) return true;
		if(hasPutty){
			putty.punishBot(bot);
			return false;
		}
		if(!isValid){
			
			return false;
		}
		if(hasBot > 1){
			bot.collison();
			return false;
		}
		else return true;
/*		Skeleton.showInfo("Function called: Field: handleLanding(Bot)");
		boolean isOiled = false;
		if(Skeleton.isOiled()){
			obstacle = new Oil();
			obstacle.punishBot(bot);
			isOiled = true;
		}
		if(!isOiled && Skeleton.isPuttied()){
			obstacle = new Putty();
			obstacle.punishBot(bot);
		}
		if(Skeleton.isOccupied()){
			bot.collison();
			Skeleton.showInfo("Function ended: handleLanding()");
			return false;
		}
		if(Skeleton.isLandingSuccessfull()){
			Skeleton.showInfo("The landing was successfull, this field now has this Bot");
			Skeleton.showInfo("Function ended: handleLanding()");
			return true;
		}
		else
			Skeleton.showInfo("Bot has fallen off the map");
		Skeleton.showInfo("Function ended: handleLanding()");
		return false;
	*/	
	}
	
	/**
	 * When the Bot jumps from a field, to another, the Bot has to be removed from the previous field
	 * hasBot is set to false
	 */
	public void removeBotFromField(){
		if(this.hasBot>0){
			this.hasBot--;
		}
		Skeleton.showInfo("Function called: Field: removeBotFromField()");
		Skeleton.showInfo("The Bot was removed from this field");
		Skeleton.showInfo("Function ended: removeBotFromField()");
	}
	
	public void removePuttyFromField(){
		if(hasPutty){
			hasPutty = false;
			putty = null;
		}
	}
	
	public void removeOilFromField(){
		if(hasOil){
			hasOil = false;
			oil = null;
		}
	}
	
	
/*	public void setObstacle(Obstacle obs){
		obstacle = obs;
	}
*/
	
}


public class Field {

//	private int hasBot;
	private Machine currentMachine;
	public boolean isValid;//public for prototype only
	public int x;
	public int y;
//	private Obstacle putty;
//	private Obstacle oil;
	private Obstacle obstacle;
	private boolean hasOil;
	private boolean hasPutty;
	
	/**
	 * Initalizer for the Field
	 * @param i vertical coordanate for the field on the map
	 * @param j horizontal coordanate for the field on the map
	 */
	public Field (int i, int j){
		//Skeleton.showInfo("Function called: Field: Field(int,int)");
		currentMachine = null;
		this.x = i;
		this.y = j;
//		this.hasBot = 0;
		this.isValid = true;
//		oil = null;
//		putty = null;
		hasOil = false;
		hasPutty = false;
		obstacle = null;
	//	Skeleton.showInfo("Function ended: Field()");
	}

	/*public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}*/ //either override spec, or delete these getters!
	
	/**
	 * Handles the landing of the bot, tells the bot, if the landing was successful, 
	 * or not, or it landed on an obstacle
	 * @param bot This bot lands on the field.
	 * @return Indicates if the landing was successful or a failure.
	 */
	public boolean handleLanding(Bot bot) {
		if(!isValid) {
			return false;
		}

		if(this.currentMachine != null) {
			Machine oldMachine = currentMachine;
			bot.collision();
			//suggestion:if currentMachine lives after collision, than return false
			//and that kills bot, instead of sending a destroy to it from currentBot's collision
			if(this.currentMachine != null) {//currentMachine is still alive, so bot is dead, and if bot is dead, currentMachine has new speed already
				return false;
			}else {//oldMachine should die, and bot should have new speed, so we tell currentMachine this news
				this.addMachineToField(bot);
				if(oldMachine.ID < 20) {//if it was a smallBot, than it was removed
					oldMachine.collision();
				}
			}
		}
		else {
			this.addMachineToField(bot);
		}
		//either it's a putty or an oil does the very same thing at this level of abstraction
		if(this.obstacle != null) {
			this.obstacle.punishBot(bot);
		}
		//if method hasn't yet returned, than it succeeded
		return true;	
	}
	
	
	/**
	 * Under any circumstances that needs to remove the machine which is 
	 * currently on @this field, this method is to be called
	 */
	public void removeMachineFromField(){
		if(currentMachine != null){
			currentMachine = null;
		}
/*		Skeleton.showInfo("Function called: Field: removeBotFromField()");
		Skeleton.showInfo("The Bot was removed from this field");
		Skeleton.showInfo("Function ended: removeBotFromField()");*/
	}
	
	/**
	 * Adds a machine to the field
	 * @param newMachine the machine that is added to the field
	 * @return false if it wasn't succesfull, true if it was
	 */
	public boolean addMachineToField(Machine newMachine){
		if(this.currentMachine == null) {
			this.currentMachine = newMachine;
			return true;
		}
		return false;
	}
	
	/**
	 * puts the obstacle on the field
	 * @param obs the obstacle that will be put on the field
	 */
	public void setObstacle(Obstacle obs){
		obstacle = obs;
	}
	
	
	/**
	 * return with the obstacle
	 */
	public Obstacle getObstacle(){
		return obstacle;
	}
	
	/**
	 * Removes the obstacle from the field if it has any
	 */
	public void removeObstacle() {
		this.obstacle = null; //don't care if it's already null....
		hasOil = false;
		hasPutty = false;
	}
	
	/**
	 * Tells the caller if @this field has a putty or an oil on it
	 * @return True if has obstacle, false if not
	 */
	public boolean hasObstacle() {
		return (this.obstacle != null);
	}
	
	/**
	 * @return the ID of the machine, that is on the field
	 */
	public int getMachineID(){
		return (currentMachine == null) ? -1 : currentMachine.getID();
	}
	
	/**
	 * 
	 * @return the Machine that is on the field
	 */
	public Machine getMachine(){
		return currentMachine;
	}
	
	/**
	 * 
	 * @param b
	 */
	public void setHasOil(boolean b){
		if(hasPutty && b)
		{
			hasPutty = false;
		}
		hasOil = b;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getHasOil(){
		return hasOil;
	}
	
	/**
	 * 
	 * @param b
	 */
	public void setHasPutty(boolean b){
		if(hasOil && b)
		{
			hasOil = false;
		}
		hasPutty = b;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getHasPutty(){
		return hasPutty;
	}
}

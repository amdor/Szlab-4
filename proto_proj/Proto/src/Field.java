
public class Field {

<<<<<<< HEAD
//	private int hasBot;
	private Machine currentMachine;
	private boolean isValid;
	private int x;
	private int y;
//	private Obstacle putty;
//	private Obstacle oil;
	private Obstacle obstacle;
	private boolean hasOil;
	private boolean hasPutty;
	
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
	//	Skeleton.showInfo("Function ended: Field()");
=======
	private Machine currentMachine;
	private boolean isValid;
	public int x;
	public int y;
	private Obstacle obstacle;

	/**
	 * Designated constructor, creates a valid field with the given position
	 * @param i The first coordinate of the field under creation
	 * @param j The second coordinate of the field
	 */
	public Field (int i, int j){
		this.x = i;
		this.y = j;
		this.currentMachine = null;
		this.isValid = true;
		this.obstacle = null;
>>>>>>> origin/master
	}


	/**
	 * Handles the landing of the bot, tells the bot, if the landing was successful, 
	 * or not, or it landed on an obstacle
	 * @param bot This bot lands on the field.
	 * @return Indicates if the landing was successful or a failure.
	 */
<<<<<<< HEAD
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
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
			this.currentMachine.collision();
			//suggestion:if currentMachine lives after collision, than return false
			//and that kills bot, instead of sending a destroy to it from currentBot's collision
			if(this.currentMachine != null) {
				return false;
			}else {
				this.currentMachine = bot;
			}
		}
		//either it's a putty or an oil does the very same thing at this level of abstraction
		if(this.obstacle != null) {
			this.obstacle.punishBot(bot);
		}
		//if method hasn't yet returned, than it succeeded
		return true;	
	}
	
	
	public boolean handleLanding(Machine mch){
		if(currentMachine != null){
			mch.collision();
			return false;
		}
		else return true;
	}
/*	
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
		if(currentMachine != null){
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
=======
	public boolean handleLanding(Bot bot) {
		if(!isValid) {
			return false;
		}

		if(this.currentMachine != null) {
			this.currentMachine.collision();
			//suggestion:if currentMachine lives after collision, than return false
			//and that kills bot, instead of sending a destroy to it from currentBot's collision
			if(this.currentMachine != null) {
				return false;
			}else {
				this.currentMachine = bot;
			}
>>>>>>> origin/master
		}
		//either it's a putty or an oil does the very same thing at this level of abstraction
		if(this.obstacle != null) {
			this.obstacle.punishBot(bot);
		}
<<<<<<< HEAD
		else
			Skeleton.showInfo("Bot has fallen off the map");
		Skeleton.showInfo("Function ended: handleLanding()");
		return false;
	
	} */
	
=======
		//if method hasn't yet returned, than it succeeded
		return true;	
	}

>>>>>>> origin/master
	/**
	 * Under any circumstances that needs to remove the machine which is 
	 * currently on @this field, this method is to be called
	 */
	public void removeMachineFromField(){
<<<<<<< HEAD
		if(currentMachine != null){
			currentMachine = null;
		}
/*		Skeleton.showInfo("Function called: Field: removeBotFromField()");
		Skeleton.showInfo("The Bot was removed from this field");
		Skeleton.showInfo("Function ended: removeBotFromField()");*/
	}
	
=======
		if(this.currentMachine != null){
			this.currentMachine = null;
		}
	}
	
	/**
	 * This method is to add a machine to the field, if there is none
	 * holding it. (Useful in case of SmallBots for instance, where no landing
	 * is needed.)
	 * @param newMachine the mahcine to be added to the field
	 * @return Indication of success
	 */
>>>>>>> origin/master
	public boolean addMachineToField(Machine newMachine){
		if(this.currentMachine == null) {
			this.currentMachine = newMachine;
			return true;
		}
		return false;
	}
<<<<<<< HEAD
	
	public void setObstacle(Obstacle obs){
		obstacle = obs;
	}
	
	/**
	 * Removes the obstacle from the field if it has any
	 */
	public void removeObstacle() {
		this.obstacle = null; //don't care if it's already null....
	}
	
	public boolean hasObstacle() {
		return (this.obstacle != null);
	}
	
/*	public void setObstacle(Obstacle obs){
		obstacle = obs;
=======

	public void setObstacle(Obstacle obs){
		obstacle = obs;
	}
	
	/**
	 * Removes the obstacle from the field if it has any
	 */
	public void removeObstacle() {
		this.obstacle = null; //don't care if it's already null....
>>>>>>> origin/master
	}
	
	/**
	 * Tells the caller if @this field has a putty or an oil on it
	 * @return True if has obstacle, false if not
	 */
	public boolean hasObstacle() {
		return (this.obstacle != null);
	}

}

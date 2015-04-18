
public class Field {

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

	/**
	 * Under any circumstances that needs to remove the machine which is 
	 * currently on @this field, this method is to be called
	 */
	public void removeMachineFromField(){
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
	public boolean addMachineToField(Machine newMachine){
		if(this.currentMachine == null) {
			this.currentMachine = newMachine;
			return true;
		}
		return false;
	}

	public void setObstacle(Obstacle obs){
		obstacle = obs;
	}
	
	/**
	 * Removes the obstacle from the field if it has any
	 */
	public void removeObstacle() {
		this.obstacle = null; //don't care if it's already null....
	}
	
	/**
	 * Tells the caller if @this field has a putty or an oil on it
	 * @return True if has obstacle, false if not
	 */
	public boolean hasObstacle() {
		return (this.obstacle != null);
	}

}

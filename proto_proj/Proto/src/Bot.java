 
public class Bot extends Machine{
	public boolean isDirectable;
	private int oilCount;
	private int puttyCount;
	
	/**
	 * Initializer to be used 
	 * @param ID The ID of the bot
	 * @param speed Initial speed of the Bot
	 * @param startingField The field where the Bot is to be start its life
	 */
	public Bot(int ID, DirectVector speed, Field startingField) throws IllegalArgumentException {
		isDirectable = true;
		oilCount = 3;
		puttyCount = 3;
		if(ID >= 20)
		{
			isDirectable = false;
			throw(new IllegalArgumentException());
		}else {
			this.ID = ID;
		}
		if(speed == null)
		{
			throw(new IllegalArgumentException());
		} else {
			this.speedVector = speed;
		}
		//if there's no field or the field is occupied, than it's not good
		if((startingField == null) || !(startingField.addMachineToField(this))){
			throw(new IllegalArgumentException());
		} else {//else field is valid, and now holds our new bot
			this.currentField = startingField;
		}
		
	}
	
	/**
	 * makes the Bot jump from one Field to another
	 * Calculates the params of getNExtField from the directVector, and current position
	 * @return true if jump was successful, false if it wasn't
	 */
	@Override
	public boolean jump() {
		this.isDirectable = true; //we're out of oil
		currentField.removeMachineFromField();
		Field tmp = Map.getNextField(this.currentField, this.speedVector);
		this.currentField = tmp;
		//az ezutáni currentField már az ahova ugrott a robot
		if(this.currentField.handleLanding(this)){
		return true;	
		}
		return false;
		
	}
	
	@Override
	public void collision(){
	}

	/**
	 * Changes the DirectVector
	 * @param x	Vertical component of the DirectVector
	 * @param y	Horizontal component of the DirectVector
	 * @return true if succeeds false if not
	 */
	public boolean changeSpeedVector(int x, int y){
		if(isDirectable) {
			speedVector.changeDirection(x, y);
			return true;
		}
		return false;
		
	}
	
	/**
	 * Puts down an oil to the currentField, if the Bot has oil
	 * If oilCount is 0 it can't put down an oil, error message
	 */
	public void putOil(){
		if(this.oilCount > 0){
			this.currentField.setObstacle(new Oil());
			this.oilCount--;
		}		
	}
	
	/**
	 * Puts down a Putty to the currentField, if the Bot has oil
	 * If puttyCount is 0 it can't put down an oil, error message
	 */
	public void putPutty(){
		if(puttyCount > 0){
			currentField.setObstacle(new Putty());
			puttyCount--;
		}
	}
	
	public int getOilCoint(){
		return this.oilCount;
	}
	
	public int getPuttyCount(){
		return this.puttyCount;
	}
	
	public int getID() {
		return this.ID;
	}
	/**
	 * Makes the DirectVector of the Bot the half as it was before
	 */
	public void slowBot(){
		this.changeSpeedVector(this.speedVector.right/2, this.speedVector.up/2);
	}
	
}

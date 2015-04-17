 
public class Bot extends Machine{
	public boolean isDirectable;
	private int oilCount;
	private int puttyCount;
	
	/**
	 * makes the Bot jump from one Field to another
	 * Calculates the params of getNExtField from the directVector, and current position
	 * @return true if jump was successful, false if it wasn't
	 */
	@Override
	public boolean jump(){
		Skeleton.showInfo("Function called: Bot: jump()");
		currentField.removeBotFromField();
		Field tmp = Map.getNextField(this.currentField, this.directVector);
		this.currentField = tmp;
		//az ezutáni currentField már az ahova ugrott a robot
		if(this.currentField.handleLanding(this)){
		return true;	
		}
		Skeleton.showInfo("Function ended: jump()");
		return false;
		
	}
	/**
	 * Changes the DirectVector
	 * @param x	Vertical component of the DirectVector
	 * @param y	Horizontal component of the DirectVector
	 */
	public boolean changeSpeedVector(int x, int y){
		Skeleton.showInfo("Function called: Bot: changeSpeedVector(int, int)");
		directVector.changeDirection(x, y);
		Skeleton.showInfo("Function ended: changeSpeedVector()");
		return false;
		
	}
	
	/**
	 * Puts down an oil to the currentField, if the Bot has oil
	 * If oilCount is 0 it can't put down an oil, error message
	 */
	public void putOil(){
		Skeleton.showInfo("Function called: Bot: putOil");
		if(Skeleton.hasOil()){
			Skeleton.showInfo("An oil was put down on currentField");
		}
		else{
			Skeleton.showInfo("You can't put down Oil on this field");
		}
		Skeleton.showInfo("Function ended: putOil()");
		
	}
	/**
	 * Puts down a Putty to the currentField, if the Bot has oil
	 * If puttyCount is 0 it can't put down an oil, error message
	 */
	public void putPutty(){
		Skeleton.showInfo("Function called: Bot: putPutty");
		if(Skeleton.hasPutty()){
			Skeleton.showInfo("A Putty was put down on currentField");
		}
		else{
			Skeleton.showInfo("You can't put down Putty on this field");
		}
		Skeleton.showInfo("Function ended: putPutty()");
	}
	
	public int getOilCoint(){
		return this.oilCount;
	}
	
	public int getPuttyCount(){
		return this.puttyCount;
	}
	/**
	 * Makes the DirectVector of the Bot the half as it was before
	 */
	public void slowBot(){
		Skeleton.showInfo("Function called: Bot: slowBot()\nThe DirecVector of the Bot is half as before");
		Skeleton.showInfo("Function ended: slowBot()");
	}
	
	
	public void collison(){
		Skeleton.showInfo("Function called: Bot: collison()\nThe Bots will be destroyed");
		Skeleton.showInfo("Function ended: collision()");
	}

}

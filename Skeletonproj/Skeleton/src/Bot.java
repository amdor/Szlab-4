
public class Bot {
	public boolean isDirectable;
	private int oilCount;
	private int puttyCount;
	private int ID;
	private Field currentField = new Field(0,0);
	private DirectVector directVector = new DirectVector();
	
	/**
	 * makes the Bot jump from one Field to another
	 * Calculates the params of getNExtField from the directVector, and current position
	 * @return true if jump was successful, false if it wasn't
	 */
	public boolean jump(){
		Skeleton.showInfo("Function called: Bot: jump()");
		currentField.removeBotFromField();
		Map.getNextField(0, 0);
		currentField.handleLanding(this);
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
		
	}
	/**
	 * Puts down a Putty to the currentField, if the Bot has oil
	 * If puttyCount is 0 it can't put down an oil, error message
	 */
	public void putPutty(){
		Skeleton.showInfo("Function called: Bot: putPutty");
		if(Skeleton.hasOil()){
			Skeleton.showInfo("A Putty was put down on currentField");
		}
		else{
			Skeleton.showInfo("You can't put down Putty on this field");
		}
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
	}
	
	public void collison(){
		Skeleton.showInfo(""Function called: Bot: collison()\nThe Bots will be destroyed");
	}

}

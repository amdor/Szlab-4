import java.lang.Math;

public class Bot extends Machine {
	public boolean isDirectable;
	private int oilCount;
	private int puttyCount;

	/**
	 * Initializer to be used 
	 * @param ID The ID of the bot
	 * @param speed Initial speed of the Bot
	 * @param startingField The field where the Bot is to be start its life
	 */
	
	public Bot(int id, Field startingField)throws IllegalArgumentException{
		isDirectable = true;
		oilCount = 3;
		puttyCount = 3;
		directVector = new DirectVector();
		if(ID >= 20)
		{
			isDirectable = false;
			throw(new IllegalArgumentException());
		}else {
			this.ID = id;
		}
		//if there's no field or the field is occupied, than it's not good
		if((startingField == null) || !(startingField.addMachineToField(this))){
			throw(new IllegalArgumentException());
		} else {//else field is valid, and now holds our new bot
			this.currentField = startingField;
		}
	}
	
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
			this.directVector = speed;
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
	public boolean jump(){
		this.isDirectable = true;
		currentField.removeMachineFromField();
		Field tmp = Map.getNextField(this.currentField, this.directVector);
		this.currentField = tmp;
		//this is the next currentField, where the robot will jump
		if(this.currentField.handleLanding(this)){
		return true;	
		}
		return false;
	}
	
	/**
	 * the function is called when two Bot collides
	 */
	@Override
	public void collision(){
		if(currentField.getMachineID() < 20)
		{
			if(compareSpeed((Bot)currentField.getMachine()))
			{
				Bot b = (Bot) currentField.getMachine();
				b.averageVector(this);
				// TODO Kill this Bot!!!!!!!! KILLLL
			}
			else
			{
				this.averageVector((Bot) currentField.getMachine());
				// TODO KILL THE OTHER BOT
			}
		}
		else if(currentField.getMachineID() > 19)
		{
			currentField.setObstacle(new Oil());
			// TODO KILL THE SMALLBOT
		}
	}

	/**
	 * Changes the DirectVector
	 * @param x	Vertical component of the DirectVector
	 * @param y	Horizontal component of the DirectVector
	 * @return true if succeeds false if not
	 */
	public boolean changeSpeedVector(int x, int y){
		/*x+=directVector.getright();
		y+=directVector.getup();*/
		directVector.changeDirection(x, y);
		return true;
		
	}
	
	/**
	 * Puts down an oil to the currentField, if the Bot has oil
	 * If oilCount is 0 it can't put down an oil, error message
	 */
	public void putOil(){
		if(this.oilCount > 0){
			this.currentField.setObstacle(new Oil());
			this.currentField.setHasOil(true);
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
			this.currentField.setHasPutty(true);
			puttyCount--;
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
		int jobbra = directVector.getright();
		int fel = directVector.getup();
		jobbra=jobbra/2;
		fel=fel/2;
		this.changeSpeedVector(jobbra, fel);
//		Skeleton.showInfo("Function called: Bot: slowBot()\nThe DirecVector of the Bot is half as before");
//		Skeleton.showInfo("Function ended: slowBot()");
	}
	
	/**
	 * compares the speed of 2 robots, that collided
	 * @param bot2 this objects speed is compared to this bot2
	 * @return true, if the speed of bot2 is bigger, false otherwise
	 */
	public boolean compareSpeed(Bot bot2){
		double b1 = Math.sqrt((double)this.directVector.getright()*(double)this.directVector.getright() +
				(double)this.directVector.getup()*(double)this.directVector.getup());
		double b2= Math.sqrt(bot2.directVector.getright()*bot2.directVector.getright() +
				bot2.directVector.getup()*bot2.directVector.getup());
		return b2>b1? true : false;
		
	}
	
	public DirectVector getDirectVector(){
		return this.directVector;
	}
	
	/**
	 * calculates the average of the speed of two robots, that collided
	 * @param bot2 the bot whit which the collision happened
	 */
	public void averageVector(Bot bot2){
		int x = (this.directVector.getright() + bot2.directVector.getright())/2;
		int y = (this.directVector.getup() + bot2.directVector.getup())/2;
		directVector.changeDirection(x, y);		
	}
}


public class SmallBot extends Machine{
	public int round;

	/**
	 * Initalizer for the SmallBot
	 * @param id the ID of the the SmallBott
	 * @param current The field, where the SmallBot will start the game
	 */
	public SmallBot(int id, Field current){
		round = 0;
		ID = id;
		directVector = new DirectVector();
		currentField = current;
	}

	/**
	 * the SmallBot search for the obstacle, that's the nearest to it
	 * @return true if the object is found, false, if not found
	 */
	public boolean searchNext(){
		int x = this.currentField.x;
		int y = this.currentField.y;
		
		return false;
		
	}

	@Override
	/**
	 * makes the smallBot move from a field, to another
	 * @return true, if the jump was succesfull false if unsuccesfull
	 */
	public boolean jump() {
		currentField.removeMachineFromField();
		if(directVector.right>1 || directVector.up>1)
			return false;
		else
		{
			Field tmp = Map.getNextField(this.currentField, this.directVector);
			this.currentField = tmp;
			//this is the next currentField, where the robot will jump
			if(this.currentField.handleLanding(this)){
				return true;	
			}
			return false;
		}
	}
	/**
	 * When a SmallBot collides with any other Bot
	 * Changes the directon of the SmallBot
	 */
	@Override
	public void collision() {
		int x = this.directVector.getright();
		int y = this.directVector.getup();
		x = x*(-1);
		y = y*(-1);
		this.directVector.changeDirection(x, y);
	}
}

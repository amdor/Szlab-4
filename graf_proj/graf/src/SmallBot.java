
public class SmallBot extends Machine{
	public int round;
	Field fieldToFind=new Field(0,0);

	/**
	 * Initalizer for the SmallBot
	 * @param id the ID of the the SmallBott
	 * @param current The field, where the SmallBot will start the game
	 */
	public SmallBot(int id, Field current){
		round = 0;
		if(id < 20)
		{
			ID = 100 + id;
		}
		else
		{
			ID = id;
		}
		directVector = new DirectVector();
		if((current == null) || !(current.addMachineToField(this))){
			throw(new IllegalArgumentException());
		} else {//else field is valid, and now holds our new smallbot
			currentField = current;
			currentField.addMachineToField(this);
		}
		
	}

	/**
	 * the SmallBot search for the obstacle, that's the nearest to it
	 * @return true if the object is found, false, if not found
	 */
	public boolean searchNext() {
		int x = this.currentField.x;
		int y = this.currentField.y;
		int minDistance=1000;
		
		for(int i=0; i<Map.fields.size(); i++)
		{
			for(int j=0; j<Map.fields.get(i).size(); j++)
			{
				Field v=Map.getInstance().getField(i, j);
				if(v.getHasOil() || v.getHasPutty())
				{
					int xd = x-v.x;
					int yd = y-v.y;
					int Distance = Math.max(Math.abs(yd),Math.abs(xd));
					//double Distance = Math.sqrt(xd*xd+yd*yd);
					if(minDistance > Distance)
					{
						fieldToFind=new Field(v.x, v.y);
						minDistance=Distance;
					}

				}
			}
		}
		if(minDistance == 1000)
		{
			return false;
		}
		else 
		{
			return true;
		}
		
	}

	
	@Override
public boolean jump(){
		if(currentField.hasObstacle())
		{
			round++;
			if(round == 2) {
				round = 0;
				currentField.removeObstacle();
			}
			return true;
		}
		if(!searchNext()){//no obstacle
			return true;
		}
		
		int vx=0;
		int vy=0;
		
		if(this.currentField.x < fieldToFind.x){
			vx=1;
		}
		else if(this.currentField.x > fieldToFind.x)
			vx=-1;
		else
			vx=0;
		
		if(this.currentField.y < fieldToFind.y){
			vy=1;
		}
		else if(this.currentField.y > fieldToFind.y)
			vy=-1;
		else
			vy=0;
		
		directVector.changeDirection(vx, vy);
		Field tmp = Map.getNextField(this.currentField, this.directVector);
		if(!tmp.isValid)
			return false;
		if(tmp.getMachine() != null && tmp.getMachine() != this)
		{
			collision();
			jump();
		}
		this.currentField = tmp; 
		currentField.addMachineToField(this);
		return true;	
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

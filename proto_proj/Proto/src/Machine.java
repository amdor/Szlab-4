
public abstract class Machine {
<<<<<<< HEAD
	protected DirectVector directVector;
	protected Field currentField;
	protected int ID;
	
/*	public boolean jump(){
		Skeleton.showInfo("Function called: Bot: jump()");
		currentField.removeBotFromField();
		Field tmp = Map.getNextField(this.currentField, this.directVector);
		this.currentField = tmp;
		//az ezut�ni currentField m�r az ahova ugrott a robot
		if(this.currentField.handleLanding(this)){
		return true;	
		}
		Skeleton.showInfo("Function ended: jump()");
		return false;
		
	}*/
	
=======
	protected DirectVector speedVector;
	protected Field currentField;
	protected int ID;
	
>>>>>>> origin/master
	public abstract boolean jump();
	
	public abstract void collision();
}

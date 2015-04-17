
public class Machine {
	protected DirectVector directVector;
	protected Field currentField;
	protected int ID;
	
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
	
	public void collision(){
		
	}
}

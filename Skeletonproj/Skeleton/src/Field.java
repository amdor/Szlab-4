
public class Field {

	private int hasBot;
	private boolean isValid;
	public int x;
	public int y;
	private Obstacle obstacle;
	
	public Field (int i, int j){
		this.x = i;
		this.y = j;
		this.hasBot = 0;
		this.isValid = true;
		this.obstacle = null;
	}
	
	public boolean handleLanding(Bot bot){
		return false;
		
	}
	
	public void removeBotFromField(){
		
	}
	
	public void setObstacle(Obstacle obs){
		
	}
	
}


public class SmallBot extends Machine{
	public int round;
	
	public SmallBot(int id, int x, int y){
		round = 0;
		ID = id;
		directVector = new DirectVector();
		currentField = new Field(x,y);
	}
	
	public boolean searchNext(){
		return true;
	}
	
	public int getID() {
		return this.ID;
	}

	@Override
	public boolean jump() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void collision() {
		// TODO Auto-generated method stub
		
	}
<<<<<<< HEAD

=======
>>>>>>> origin/master
}

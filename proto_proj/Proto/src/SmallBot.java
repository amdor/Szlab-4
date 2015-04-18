
public class SmallBot extends Machine{
	public int round;
	
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
}

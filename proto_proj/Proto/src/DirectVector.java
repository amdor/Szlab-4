/**
 * 
 *
 */
public class DirectVector {

	public int right;
	public int up;

	
	public DirectVector(){
		right = 0;
		up = 0;
	}
	
	/**
	 *Adds the new direct to the previous DirectVector
	 * @param x Vertical component of the DirectVector
	 * @param y Horizontal component of the DirectVector
	 */
	public void changeDirection(int x, int y){
//		Skeleton.showInfo("Function called: DirectVector: changeDirection(int, int)");
//		Skeleton.showInfo("Function ended: changeDirection()");
		this.right = x;
		this.up = y;
	}
	
	public int getright(){
		return this.right;
	}
	
	public int getup(){
		return this.up;
	}
	
}

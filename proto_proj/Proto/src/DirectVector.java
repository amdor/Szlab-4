/**
 * 
 *
 */
public class DirectVector {

	public int right;
	public int up;

	/**
	 * Initializer to be used
	 */
	public DirectVector(){
		right = 1;
		up = 1;
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
	
	/**
	 * Get function for right attribute
	 * @return the value of right
	 */
	public int getright(){
		return this.right;
	}
	
	/**
	 * Get function for up attribute
	 * @return the value of up
	 */
	public int getup(){
		return this.up;
	}
	
}

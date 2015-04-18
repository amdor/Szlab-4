/**
 * 
 *
 */
public class DirectVector {

	public int right;
	public int up;
	
<<<<<<< HEAD
	public DirectVector(){
		right = 0;
		up = 0;
	}
	
	/*
=======
	/**
	 * Designated initializer
	 * @param x X coordinate of the direction
	 * @param y Y coordinate of the direction
	 */
	public DirectVector(int x, int y) {
		right = x;
		up = y;
	}
	/**
>>>>>>> origin/master
	 *Adds the new direct to the previous DirectVector
	 * @param x Vertical component of the DirectVector
	 * @param y Horizontal component of the DirectVector
	 */
	public void changeDirection(int x, int y){
<<<<<<< HEAD
//		Skeleton.showInfo("Function called: DirectVector: changeDirection(int, int)");
//		Skeleton.showInfo("Function ended: changeDirection()");
		this.right = x;
		this.up = y;
		
=======
		/*Skeleton.showInfo("Function called: DirectVector: changeDirection(int, int)");
		Skeleton.showInfo("Function ended: changeDirection()");*/

>>>>>>> origin/master
	}
	
	public int getright(){
		return this.right;
	}
	
	public int getup(){
		return this.up;
	}
	
}

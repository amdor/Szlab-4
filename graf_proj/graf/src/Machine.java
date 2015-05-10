
public abstract class Machine {

	protected DirectVector directVector;
	public Field currentField; 
	protected int ID;
	
	public abstract boolean jump();
	
	public abstract void collision();
	
	/**
	 * 
	 * @return the ID of the machine
	 */
	public int getID() {
		return this.ID;
	}
}


public abstract class Machine {
	protected DirectVector speedVector;
	protected Field currentField;
	protected int ID;
	
	public abstract boolean jump();
	
	public abstract void collision();
}

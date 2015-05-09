import java.util.ArrayList;


public final class Map {

	public static ArrayList<ArrayList<Field>> fields;
	private static Map instance = null;
	public static ArrayList<SmallBot>smallBots;
	
	private Map(){
		fields = new ArrayList<ArrayList<Field>>();
		//commented for test reasons
		/*for(int i=0;i < 50; i++){
			fields.add(new ArrayList<Field>());
			for (int j=0; j < 25;j++)
				fields.get(i).add(new Field(i, j));
		}*/
		smallBots = new ArrayList<SmallBot>();
		
	}
	
	//helper method for the prototype only
	public boolean addField(Field newField) {
		int x = newField.x;
		int y = newField.y;
		if(x < 0 || y < 0)//cos arraylists
		{
			return false;
		}
		if(fields.size() <= x)//otherwise it would throw an exceptions
		{
			fields.add(x, new ArrayList<Field>());
		}
		fields.get(x).add(y, newField);
		
		return true;
		
	}
	
	//for prototype test only
	public Field getField(int x, int y)
	{
		return fields.get(x).get(y);
	}
	
	public static Map getInstance(){
        if (instance == null){
            instance = new Map();
        }
        return instance;
	}
	
	public static void timeEnd(){
		
	}
	
	/**
	 * Tells the Bot where it will land after the jump
	 * @param x Vertical coordinate of the fields place on the Map 
	 * @param y Horizontal coordinate of the fields place on the Map 
	 * @return The Field, where the Bot will jump to
	 */
	public static Field getNextField(Field currentField, DirectVector dv){
		
		int x = currentField.x + dv.getright();
		int y = currentField.y + dv.getup();
		if(x >= fields.size() || y >= fields.get(x).size())
		{
			return new Field(-1, -1);
		}
		return fields.get(x).get(y);
//		Skeleton.showInfo("Function called: Map: getNextField(int,int)");
//		Skeleton.showInfo("Function ended: getNextField()");
	}
	
	
	
}

import java.util.ArrayList;


public final class Map {

	private static ArrayList<ArrayList<Field>> fields;
	private static Map instance = null;
	
	private Map(){
		fields = new ArrayList<ArrayList<Field>>();
		for(int i=0;i < 50; i++){
			fields.add(new ArrayList<Field>());
			for (int j=0; j < 25;j++)
				fields.get(i).add(new Field(i, j));
		}
		
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
	public static Field getNextField(int x, int y){
		Skeleton.showInfo("Function called: Map: getNextField(int,int)");
		Skeleton.showInfo("Function ended: getNextField()");
		return null;
	}
	
	
	
}

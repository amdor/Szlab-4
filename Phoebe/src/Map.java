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
	
	public static Field getNextField(int x, int y){
		
	}
	
	
	
}

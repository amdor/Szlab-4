import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class Proto {
//	private static InputStream protoStandardInput;
	private static OutputStreamWriter protoStandardOutput;//i.e.:protoStandardOutput = new OutputStreamWriter(System.out);
	private static int currentMaxBotID = 0;
	private static int currentMaxSmallBotID = 20;
	
	private static String testFolderPath = System.getProperty("user.dir") + "\\tests";
	private static String testMapName = "\\map.txt";
	
	private static String[] inputFiles = {"\\change_speed.txt", "\\change_speed_output.txt",
		"\\jump.txt", "\\jump_output.txt"};
	
	private static Map testMap;
	private static ArrayList<Machine> testMachines = new ArrayList<Machine>();//ID is the index
	private static Bot playerBot;
	
	
	/**
	 * Makes a jump call on the robo. Pulled out from the main logic
	 * to reach integrity, and for possible further extension in future
	 * @param robo the Bot to initiate the jump on
	 */
	private static void jump(Bot robo)
	{
		robo.jump();
	}
	
	
	//Proto interface methods
	
	/**
	 * Changes the robo's speed vector. Yet does nothing else.
	 * @param robo the Bot for which we change speed
	 * @param x the first value of the vector of speed
	 * @param y the second value of the speedvector
	 */
	private static void changeSpeed(Bot robo, int x, int y)
	{
		robo.changeSpeedVector(x, y);
	}
	
	//it is handled at higher level if there is no param given (in processCommand)
	private static void putOil(int x, int y)
	{
		//TODO
	}
	
	//it is handled at higher level if there is no param given (in processCommand)
	private static void putPutty(int x, int y)
	{
		//TODO
	}
	
	private static void putBot(int x, int y, Bot bot) 
	{
		//TODO
	}
	
	private static void putCleanerBot(int x, int y, SmallBot smallBot)
	{
		//TODO
	}	
	
	private static void modifyMapWithField(Field field)
	{
		//TODO
	}
	
	private static void advanceTime(int numberOfRounds)
	{
		//TODO
	}

	//it is handled at higher level if there is no param given (in processCommand)
	private static String getBotState(int ID)
	{
		Bot currentBot = (Bot) testMachines.get(ID);
		return ("getBotState " + currentBot.isDirectable + " " + currentBot.getOilCoint() 
				+ " " + currentBot.getPuttyCount() + " " + ID + " " + currentBot.currentField.x
				+ " " + currentBot.currentField.y);
	}

	private static String getMap()
	{
		//TODO
		return null;
	}
	
	private static String getField(int x, int y)
	{
		//TODO
		return null;
	}
	
	private static String getBots() 
	{
		//TODO
		return null;
	}
	
	
	//Test runner
	
	private static void runAutomatedTests() {
		try {
			
			int inputIndex = 0;
			//read all test files
			while(inputIndex < inputFiles.length)
			{
				//clear file
				protoStandardOutput = new OutputStreamWriter(new FileOutputStream(new File(testFolderPath + "\\output.txt")));
				protoStandardOutput.write(new String());
				protoStandardOutput = new OutputStreamWriter(new FileOutputStream(new File(testFolderPath + "\\output.txt"), true));
				BufferedReader br =  new BufferedReader(new FileReader(testFolderPath + inputFiles[inputIndex]));
				String inputLine = null;
				//reading in the file doing the test and comparing the output
				while((inputLine = br.readLine()) != null)
				{
					processCommand(inputLine);
				}
				
				//tearDown
				br.close();
				protoStandardOutput.close();
				if(!validateOutput(inputFiles[inputIndex+1]))
				{
					return;//failed					
				}
				inputIndex += 2;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//get a line of command from the user and give it to processCommand
	private static void runManualTests() {
		//TODO
	}
	
	//to be use from manual and automatic testrunning as well
	//starts the corresponding methods with proper input
	//getter command must write on protoStandartOutput
	private static void processCommand(String command) throws NumberFormatException, IOException
	{
		String[] input = command.split(" ");
		switch(input[0])
		{
		case "jump":
			jump(playerBot);
			break;
		case "changeSpeed":
			int x = Integer.parseInt(input[1]);
			int y = Integer.parseInt(input[2]);
			if( (x >= 0) && (y >= 0) ) {//arraylist cannot recieve negative index
				changeSpeed(playerBot, x, y);
			}
			break;
		case "putOil":

			break;
		case "putPutty":

			break;
		case "getBotState":
			if(input.length  == 2){
				protoStandardOutput.write( getBotState(Integer.parseInt(input[1])) );
			}else {
				protoStandardOutput.write( getBotState(playerBot.ID) );
			}
			protoStandardOutput.write(System.getProperty("line.separator"));//!

			break;
		case "setBotState":

			break;
		case "putBot":

			break;
		case "putCleanerBot":

			break;
		case "modifyMapWithField":

			break;
		case "advanceTime":

			break;
		case "getMap":

			break;
		case "getField":

			break;
		case "getBots":

			break;
		case "acquireBot":

			break;
		}
	}
	
	//prints successs if the to files are identical
	private static boolean validateOutput(String awaitedOutputFileName) 
	{
		try {
			BufferedReader awaitedReader = new BufferedReader(new FileReader(testFolderPath + awaitedOutputFileName));
			BufferedReader outputReader = new BufferedReader(new FileReader(testFolderPath + "\\output.txt"));
			
			String line = null;
			while( (line = awaitedReader.readLine()) != null)
			{
				if(!outputReader.readLine().equals(line)) {
					System.out.println(awaitedOutputFileName.substring(1) + " is not identical to the output.txt's content");
					awaitedReader.close();
					outputReader.close();
					return false;
				}
			}
			
			awaitedReader.close();
			outputReader.close();
			System.out.println(awaitedOutputFileName.substring(1) + "is identical to the output, success");
			System.out.println("The problem occured at line: " + line);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	
	//builds the map from the content of the given file
	private static void buildMapFromFile(String filePath) {
		//building map
		BufferedReader br;
		testMap = Map.getInstance();
		try {
			br = new BufferedReader(new FileReader(filePath));

			String inputLine = null;
			while((inputLine = br.readLine()) != null)
			{
				String[] fieldData = inputLine.split(" ");//0:hasBot, 1:isValid, 2:x, 3:y
				boolean hasBot = Integer.parseInt(fieldData[0]) == 1;
				boolean isValid = Integer.parseInt(fieldData[1]) == 1;
				Field newField = new Field(Integer.parseInt(fieldData[2]), Integer.parseInt(fieldData[3]));
				if(hasBot)
				{
					if(currentMaxBotID < 20) {//so at 19 it runs
						Bot newBot = new Bot(currentMaxBotID, newField);//also adds bot to the field
						if(currentMaxBotID == 0){
							playerBot = newBot; //the initial bot of the user
						}
						testMachines.add(newBot);
						currentMaxBotID++;
					}
				}
				if(!isValid) {//initially valid...
					newField.isValid = false;
				}
				testMap.addField(newField);			
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try{
			buildMapFromFile(testFolderPath + testMapName);
			//user interaction
			System.out.println("Would you prefer automated or manual test run?\na:Automated\nb:Manual");
			BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
			String input = null;
			if((input = br.readLine())!=null){
				switch(input.charAt(0))
				{
				case 'a':
					runAutomatedTests();
					break;
				case 'b':
					runManualTests();
					break;
				}
			}
			br.close();

		}catch(IOException io){
			io.printStackTrace();
		}
	}

}

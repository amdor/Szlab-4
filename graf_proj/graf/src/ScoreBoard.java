import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public class ScoreBoard extends JFrame {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 630969723500046995L;
	private JScrollPane scrollpane;
	
	private static ArrayList<ArrayList<String>> scoreTable = new ArrayList<ArrayList<String>>();
	
	
	public ScoreBoard(){
		
		 setSize(500, 300);
		 this.setTitle("Eredmények");
	     setVisible(true);	     
		 
		 JPanel panel = new JPanel();
		 panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		 
		 Font font24 = new Font("Tahoma", 0, 24);
		 
		 JLabel title = new JLabel("Eredmények");
		 title.setFont(font24);
		 title.setHorizontalAlignment(SwingConstants.CENTER);
		 panel.add(Box.createRigidArea(new Dimension(0,20)));
		 panel.add(title);
		 panel.add(Box.createRigidArea(new Dimension(0,30)));
		 
		 //panel.setBorder(BorderFactory.createLineBorder(Color.black));
		 /*
		 String[] pl = new String[]{"Béla","Sanyi","Kati"};
		 String[] pl2 = new String[]{"Árpi","Mari","Peti","Reni","ASDASD"};
		 this.AddToScoreBoard(pl);
		 this.AddToScoreBoard(pl2);
		 */
		 
		 
		 JPanel gameScore;
		 //gameScore.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.red));
		 		 
		 scoreTable = ReadScoreBoard();
		 
		 JLabel label;
		 for(ArrayList<String> list : scoreTable){
			 gameScore = new JPanel();
			 gameScore.setAlignmentX(CENTER_ALIGNMENT);
			 gameScore.setLayout(new BoxLayout(gameScore, BoxLayout.Y_AXIS));
			 gameScore.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
			 
			int i = 1;
			gameScore.add(Box.createRigidArea(new Dimension(0,10)));
			for(String str : list){
				label = new JLabel(i++ + ". helyezett: " + str);
				label.setAlignmentX(Component.CENTER_ALIGNMENT);
				gameScore.add(label);
			}
			gameScore.add(Box.createRigidArea(new Dimension(0,10)));
			
			//gameScore.setPreferredSize(getPreferredSize());
			panel.add(gameScore);
		 } 
		 
		 scrollpane = new JScrollPane(panel);

		 //scrollpane.setAlignmentX(CENTER_ALIGNMENT);
		 scrollpane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
		 scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 getContentPane().add(scrollpane, BorderLayout.CENTER);
	}

	/**
	 * Reads the scoreboard from file
	 * @return Scoreboard
	 */
	private static ArrayList<ArrayList<String>> ReadScoreBoard() {
		ArrayList<ArrayList<String>> scores = new ArrayList<ArrayList<String>>();
		try {
			FileInputStream fis = new FileInputStream("scoreboard.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);	
			
			Object obj = ois.readObject();
			
			if(obj instanceof java.util.ArrayList)
	            {
				scores = (ArrayList<ArrayList<String>>) obj;
	            }
			
			ois.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return scores;
	}
	
	/**
	 * Add a game result to the scoreboard
	 * @param players A String array containing the names of the players
	 */
	public static void AddToScoreBoard(String[] players){
		ArrayList<String> gameScore = new ArrayList<String>();
		for(int i = 0; i < players.length; i++)
		{
			gameScore.add(players[i]);
		}
		scoreTable = ReadScoreBoard();
		
		scoreTable.add(gameScore);
		
		SaveScoreBoard(scoreTable);
		
	}

	/**
	 * Write the scoreboard to file
	 * @param tbale Scoreboard ArrayList
	 */
	private static void SaveScoreBoard(ArrayList<ArrayList<String>> table) {
		
		try{
		      //use buffering
		      OutputStream file = new FileOutputStream("scoreboard.dat");
		      OutputStream buffer = new BufferedOutputStream(file);
		      ObjectOutput output = new ObjectOutputStream(buffer);
		      try{
		        output.writeObject(table);
		      }
		      finally{
		        output.close();
		      }
		    }  
		    catch(IOException ex){
		    	ex.printStackTrace();
		    }
		
	}
}

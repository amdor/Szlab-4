import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class MapFrame extends JFrame implements ActionListener {
	public MapPanel mapPanel;
	private ArrayList<Bot> bots;
	private int activeBotIndex;
	private static int round=15;
	private static String[] playerListForScoreBoard;
	
	SpinnerNumberModel model1;
	SpinnerNumberModel model2;
    JSpinner dx;
    JSpinner dy;
    JLabel currentPlayer;
    JLabel inform;
	
	public MapFrame()
	{
		//frame
		super("Phoebe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setResizable(false);
		
		//map
		buildMap();
        mapPanel = new MapPanel();
        bots = new ArrayList<>();
        //example bots generation
        //zeroth bot is the default bot of player1, and so its is highlighted by the panel
        activeBotIndex = 0;
	    for(int i = 0; i < Menu.playerCount; i++)
	    {
	    	Bot bot = new Bot(i, Map.getInstance().getField(0, i+1));
	    	bots.add(bot);
	    }
	    playerListForScoreBoard = new String[bots.size()];
	    mapPanel.setBots(bots);
		Map.smallBots.add(new SmallBot(10,Map.getInstance().getField(0, 0)));
	    
        setLayout(new BorderLayout());
        add(mapPanel, BorderLayout.NORTH);
        
        //buttonPanel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 25, 10));
        JButton jumpButton = new JButton("Jump");
        jumpButton.setActionCommand("Jump");
        jumpButton.addActionListener(this);
        buttonPanel.add(jumpButton);
        
		JButton putPuttyButton = new JButton("Put putty");
        putPuttyButton.setActionCommand("Putputty");
        putPuttyButton.addActionListener(this);
        buttonPanel.add(putPuttyButton);
        
        JButton putOilButton = new JButton("Put Oil");
        putOilButton.setActionCommand("Put Oil");
        putOilButton.addActionListener(this);
        buttonPanel.add(putOilButton);

        model1 = new SpinnerNumberModel(0, -1, 1, 1);
        model2 = new SpinnerNumberModel(0, -1, 1, 1);
        dx = new JSpinner(model1);
        dy = new JSpinner(model2);
        ((JSpinner.NumberEditor) dx.getEditor()).getTextField().setEditable(false);//disable their textfield
        ((JSpinner.NumberEditor) dy.getEditor()).getTextField().setEditable(false);
        buttonPanel.add(dx);
        buttonPanel.add(dy);		
        
        currentPlayer=new JLabel("Player: 0");
        buttonPanel.add(currentPlayer);
        inform=new JLabel();
        buttonPanel.add(inform);
        
        add(buttonPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);		
	}
	
	/** Button actions handling */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Jump"))
		{
			
			if(bots.size() == 0)
				return; 
			
			for(int i=0; i<Map.fields.size(); i++){
			for(int j=0; j<Map.fields.get(i).size(); j++){
				Field v=Map.getInstance().getField(i, j);
				
				if(v.getHasOil()){
					Oil o =(Oil)v.getObstacle();
					if(o.roundCount<3)
						o.roundCount++;
					else
						Map.getInstance().getField(i, j).removeObstacle();
				}
			}
		}
			
			Integer x = (Integer)dx.getValue();
			Integer y = (Integer)dy.getValue();
			
			bots.get(activeBotIndex).changeSpeedVector(x, y);
			
			if(!bots.get(activeBotIndex).jump())
			{
				playerListForScoreBoard[playerListForScoreBoard.length - bots.size()] = "Player " + bots.get(activeBotIndex).getID();
				bots.remove(activeBotIndex);//jumped out, kill
				inform.setText(activeBotIndex + ".bot out");
				activeBotIndex--;
				//TODO tell the user, the bot is dead
			}
			//jump succeeded, but other bots might've died, remove all next bots that are dead
			while(activeBotIndex < bots.size() - 1 && bots.get(activeBotIndex + 1).currentField == null)
			{
				playerListForScoreBoard[playerListForScoreBoard.length - bots.size()] = "Player " + bots.get(activeBotIndex).getID();
				bots.remove(activeBotIndex + 1);
			}
			//last normal bot has jumped or we have players left in this round
			if(activeBotIndex == bots.size() - 1)
			{
				activeBotIndex = 0;
				for(int i=0;i<Map.smallBots.size();i++){
					if(!Map.smallBots.get(i).jump())
					{
						Map.smallBots.remove(i);
					}
				}
				round--;
			
			}
			else
			{
				activeBotIndex++;

			}
			mapPanel.setActiveBotIndex(activeBotIndex);
			//So the spinner represents the current bot's state
			if(bots.size() != 0)
			{
				int right = bots.get(activeBotIndex).getDirectVector().getright(); 
				int up = bots.get(activeBotIndex).getDirectVector().getup();	
				model1.setMaximum(right+1);
				model1.setMinimum(right-1);
				model2.setMaximum(up+1);
				model2.setMinimum(up-1);
				model1.setValue(right);
				model2.setValue(up);
				dx.setEnabled(bots.get(activeBotIndex).isDirectable);
				dy.setEnabled(bots.get(activeBotIndex).isDirectable);
				
				if(bots.size() == 1) { //game over
					playerListForScoreBoard[playerListForScoreBoard.length - bots.size()] = "Player " + bots.get(0).getID();
					ScoreBoard.AddToScoreBoard(playerListForScoreBoard);
					this.setVisible(false);
					new ScoreBoard();
				}
			}
			mapPanel.repaint();
			
			currentPlayer.setText("Player:" + activeBotIndex );
			
		}//jump end
		else if(e.getActionCommand().equals("Putputty")){
			bots.get(activeBotIndex).putPutty();
			inform.setText(bots.get(activeBotIndex).puttyCount +"putty");
			
		}	
		else if(e.getActionCommand().equals("Put Oil")){
			bots.get(activeBotIndex).putOil();
			inform.setText(bots.get(activeBotIndex).oilCount +"oil");
			
		}
	}
	
	private void buildMap()
	{
		for(int row = 0; row < MapPanel.rowCount; row++)
		{
			for(int column = 0; column < MapPanel.columnCount; column++)
			{
				Map.getInstance().addField(new Field(column, row));
			}
		}
		//setting obstacles, and blank spots
		//set up a gap in the middle of the map
		Field fieldToCustomize;
		for(int i = 0; i < MapPanel.rowCount; i++)
		{		
			fieldToCustomize = Map.getInstance().getField(5, i);
			fieldToCustomize.isValid = false;
		}
		fieldToCustomize = Map.getInstance().getField(3, 3);
		fieldToCustomize.setObstacle(new Putty());
		fieldToCustomize.setHasPutty(true);
	}
	
}

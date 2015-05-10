import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class MapFrame extends JFrame implements ActionListener {
	public MapPanel mapPanel;
	private ArrayList<Bot> bots;
	private int activeBotIndex;
	SpinnerNumberModel model1;
	SpinnerNumberModel model2;
    JSpinner dx;
    JSpinner dy;
	
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
        
        add(buttonPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);		
	}
	
	/** Button actions handling */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Jump"))
		{
			int right = bots.get(activeBotIndex).getDirectVector().getright(); 
			int up = bots.get(activeBotIndex).getDirectVector().getup();	
//			model1.setMaximum(right+1);
//			model1.setMinimum(right-1);
//			model2.setMaximum(up+1);
//			model2.setMinimum(up-1);

			
			Integer x = (Integer)dx.getValue();
			Integer y = (Integer)dy.getValue();
			
			bots.get(activeBotIndex).changeSpeedVector(x, y);
			
			if(bots.size() == 0)
				return;
			if(!bots.get(activeBotIndex).jump())
			{
				bots.remove(activeBotIndex);//jumped out, kill
				activeBotIndex--;
				//TODO tell the user, the bot is dead
			}
			//jump succeeded, but other bots might've died, remove all next bots that are dead
			while(activeBotIndex < bots.size() - 1 && bots.get(activeBotIndex + 1).currentField == null)
			{
				bots.remove(activeBotIndex + 1);
			}
			//last normal bot has jumped or we have players left in this round
			if(activeBotIndex == bots.size() - 1)
			{
				activeBotIndex = 0;
				//TODO : the last bot has jumped, now it's time for smallbots, and we need to tell the map, that a round has passed (timehandling)
			}
			else
			{
				activeBotIndex++;

			}
			mapPanel.setActiveBotIndex(activeBotIndex);
			//So the spinner represents the current bot's state
			if(bots.size() != 0)
			{
				right = bots.get(activeBotIndex).getDirectVector().getright(); 
				up = bots.get(activeBotIndex).getDirectVector().getup();	
				model1.setMaximum(right+1);
				model1.setMinimum(right-1);
				model2.setMaximum(up+1);
				model2.setMinimum(up-1);
				model1.setValue(right);
				model2.setValue(up);
				dx.setEnabled(bots.get(activeBotIndex).isDirectable);
				dy.setEnabled(bots.get(activeBotIndex).isDirectable);
			}
			mapPanel.repaint();
		}
		else if(e.getActionCommand().equals("Putputty")){
			System.out.println(3);
//			Map.fields.get(bots.get(activeBotIndex).currentField.x).get(bots.get(activeBotIndex).currentField.y)
			bots.get(activeBotIndex).currentField.setObstacle(new Putty());
//			Map.fields.get(bots.get(activeBotIndex).currentField.x).get(bots.get(activeBotIndex).currentField.y)
			bots.get(activeBotIndex).currentField.setHasPutty(true);
			bots.get(activeBotIndex).puttyCount -= 1;
			
		}	
		else if(e.getActionCommand().equals("Put Oil")){
			System.out.println(2);
//			Map.fields.get(bots.get(activeBotIndex).currentField.x).get(bots.get(activeBotIndex).currentField.y)
			bots.get(activeBotIndex).currentField.setObstacle(new Oil());
//			Map.fields.get(bots.get(activeBotIndex).currentField.x).get(bots.get(activeBotIndex).currentField.y)
			bots.get(activeBotIndex).currentField.setHasOil(true);
			bots.get(activeBotIndex).oilCount -= 1;
			
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
	}
	
}

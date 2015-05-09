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
        activeBotIndex = 0;
        bots.add(new Bot(0, Map.getInstance().getField(8, 5))); //zeroth bot is the default bot of player1, and so its is highlighted by the panel
	    for(int i = 1; i < 4; i++)
	    {
	    	Bot bot = new Bot(i, Map.getInstance().getField(i, i*2));
	    	bots.add(bot);
	    }
	    
	    mapPanel.setBots(bots);
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
        
    	SpinnerNumberModel model1 = new SpinnerNumberModel(0, -1, 1, 1);
    	SpinnerNumberModel model2 = new SpinnerNumberModel(0, -1, 1, 1);
        JSpinner dx=new JSpinner(model1);
        JSpinner dy=new JSpinner(model2);
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
			if(bots.size() == 0)
				return;
			if(!bots.get(activeBotIndex).jump())
			{
				bots.remove(activeBotIndex);//jumped out, kill
				activeBotIndex--;
			}
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
			mapPanel.repaint();
		}
		else if(e.getActionCommand().equals("Putputty")){
			System.out.println(3);
			Map.fields.get(bots.get(activeBotIndex).currentField.x).get(bots.get(activeBotIndex).currentField.y).
				setObstacle(new Putty());
			Map.fields.get(bots.get(activeBotIndex).currentField.x).get(bots.get(activeBotIndex).currentField.y)
	.			setHasPutty(true);
			bots.get(activeBotIndex).puttyCount -= 1;
			
		}	
		else if(e.getActionCommand().equals("Put Oil")){
			System.out.println(2);
			Map.fields.get(bots.get(activeBotIndex).currentField.x).get(bots.get(activeBotIndex).currentField.y).
				setObstacle(new Oil());
			Map.fields.get(bots.get(activeBotIndex).currentField.x).get(bots.get(activeBotIndex).currentField.y)
		.		setHasOil(true);
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

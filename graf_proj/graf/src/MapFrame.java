import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MapFrame extends JFrame {
	public MapPanel mapPanel;
	
	public MapFrame()
	{
		//frame
		super("Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setResizable(false);
		//map
        mapPanel = new MapPanel();
	     
        setLayout(new BorderLayout());
        add(mapPanel, BorderLayout.NORTH);
        //buttonPanel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 25, 10));
        JButton jumpButton = new JButton("Jump");
        buttonPanel.add(jumpButton);
        JButton putPuttyButton = new JButton("Put putty");
        buttonPanel.add(putPuttyButton);
        
        add(buttonPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);		
	}
	
}

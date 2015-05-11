import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * This class is responsible for the drawing of the map and its element.
 * Strongly depends on the Map
 * Also has to know about the other components that needs to be drawn
 */
@SuppressWarnings("serial")
public class MapPanel extends JPanel {
	private static int width = 1200;
	private static int height = 600;
	public static int rowCount = 16;
	public static int columnCount = 32;
	private int cellWidth = width / columnCount;
	private int cellHeight = height / rowCount;
	private int xOffset = (width - (columnCount * cellWidth)) / 2;//if the grid is not the same size as the panel, this posiotions it to the center
	private int yOffset = (height - (rowCount * cellHeight)) / 2;
	
	private ArrayList<Rectangle> cells = new ArrayList<>(rowCount * columnCount);
	public BufferedImage wallE;
	public BufferedImage wallF;
	public BufferedImage oilStain;
	public BufferedImage putty;

//	private DirectVector[] botsPosition;
	ArrayList<Bot> bots;
	public int activeBotIndex;
	
	public MapPanel() 
	{
		//TODO other needed staff here?
		setSize(600, 300);
		try {
			wallE = ImageIO.read(new File("src\\walle.png"));
			wallF = ImageIO.read(new File("src\\wallf.png"));
			oilStain = ImageIO.read(new File("src\\oilstain.png"));
			putty = ImageIO.read(new File("src\\putty.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		activeBotIndex = 0;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	@Override
	public void invalidate() {
		//cells.clear();
		super.invalidate();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D rectGraphics = (Graphics2D) g.create();

//		build cells
		if (cells.isEmpty()) {
			for (int row = 0; row < rowCount; row++) {
				for (int col = 0; col < columnCount; col++) {
					int currentWidth = xOffset + (col * cellWidth);
					int currentHeight = yOffset + (row * cellHeight);
					Rectangle cell = new Rectangle(
							currentWidth,
							currentHeight,
							cellWidth,
							cellHeight);
					cells.add(cell);
				}
			}
		}

		rectGraphics.setColor(Color.GRAY);
		int activeBotCellIndex = -1;
		if(bots.size() > 0)
			activeBotCellIndex = bots.get(activeBotIndex).currentField.y * columnCount + bots.get(activeBotIndex).currentField.x;
		
		//draw cells
		for (int i = 0; i < cells.size(); i++) {
			rectGraphics.draw(cells.get(i));
		}

		//draw obstacles
		for(int i = 0; i < Map.getInstance().fields.size(); i++){
			for(int j = 0; j < Map.getInstance().fields.get(i).size(); j++){
				if(Map.getInstance().fields.get(i).get(j).getHasOil()){
					drawImage(oilStain, g, i, j);
				}
				if(Map.fields.get(i).get(j).getHasPutty()){
					drawImage(putty, g, i, j);
				}
				if(!Map.getInstance().fields.get(i).get(j).isValid){
					rectGraphics.setColor(Color.BLACK);
					rectGraphics.fill(cells.get(columnCount * j + i));
					rectGraphics.setColor(Color.GRAY);
				}
			}
		}
		//draw smallBots
		for(int i=0;i<Map.smallBots.size();i++) {
			drawImage(wallF, g,Map.smallBots.get(i).currentField.x,Map.smallBots.get(i).currentField.y);
		}
		
		//draw bots
		for(int i = 0; i < bots.size(); i++) {
			drawImage(wallE, g, bots.get(i).currentField.x, bots.get(i).currentField.y);
		}
		
		//draw the active cell if any
		if(activeBotCellIndex > -1)
		{
			float thickness = 3;
			Stroke oldStroke = rectGraphics.getStroke();
			rectGraphics.setStroke(new BasicStroke(thickness));
			rectGraphics.draw(cells.get(activeBotCellIndex));
			rectGraphics.setStroke(oldStroke);
		}

		rectGraphics.dispose();
	}
	
	/**
	 * Draws the bot to the given position
	 * @param g The graphics object for drawing
	 * @param x The x coordinate of the bot
	 * @param y The y coordinate of the bot
	 */
	private void drawImage(Image i, Graphics g, int x, int y) {
		g.drawImage(i, xOffset + (x * cellWidth)+1, yOffset + (y * cellHeight)+1, null);//+1 is magic number
	}
	/*private void drawWallF(Graphics g, int x, int y) {
		g.drawImage(wallF, xOffset + (x * cellWidth)+1, yOffset + (y * cellHeight)+1, null);//+1 is magic number
	}

	private void drawOil(Graphics g, int x, int y){
		g.drawImage(oilStain, xOffset + (x * cellWidth)+1, yOffset + (y * cellHeight)+1, null);
	}

	private void drawPutty(Graphics g, int x, int y){
		g.drawImage(putty, xOffset + (x * cellWidth)+1, yOffset + (y * cellHeight)+1, null);
	}
*/
	public void setActiveBotIndex(int count) {
		if(count == 0)
		{
			activeBotIndex = 0;
		}
		if(count > bots.size() - 1)
		{
			activeBotIndex = 0;
		}
		else
		{
			activeBotIndex = count;
		}
	}
	
	/**
	 * After the initialization of the bots, they need to be added to this class, so it can 
	 * follow their position on repaints. To invalidate (kill) a bot, set its coordinates to -1 -1, 
	 * which is out of the window
	 * @param bots The bots of the game to draw.
	 */
	public void setBots(ArrayList<Bot> bots) {
//		botsPosition = new DirectVector[bots.size()];
		this.bots = bots;
//		for(int i = 0; i < bots.size(); i++)
//		{
//			botsPosition[i] = new DirectVector();
//			botsPosition[i].right = bots.get(i).currentField.x;
//			botsPosition[i].up = bots.get(i).currentField.y;
//		}
	}

}

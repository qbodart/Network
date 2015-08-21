package core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Agent 
{

	private int x;
	private int y;
	private int d;
	public static final Color COLOR = new Color(150, 100, 100);
	public static final Color COLOR_SELECTED = Color.BLUE;
	public static final Color COLOR_LINE = new Color(150, 100, 100);
	private boolean selected;
	
	public Agent(int d)
	{
		this.d = d;
		this.selected = false;
	}
	
	/**
	 * @return the selected
	 */
	public boolean isSelected() 
	{
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) 
	{
		this.selected = selected;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the d
	 */
	public int getD() {
		return d;
	}

	/**
	 * @param d the d to set
	 */
	public void setD(int d) {
		this.d = d;
	}

	public boolean contains(Point2D p)
	{
		int dist = (int) (Math.sqrt(Math.pow(p.getX() - this.x, 2) + Math.pow(p.getY() - this.y, 2)));
		return dist < this.d;
	}
	
	public void draw(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D) g;
		if(selected)
		{
			g2.setColor(COLOR_SELECTED);
		}
		else
		{
			g2.setColor(COLOR);
		}
		g2.fillOval(x - d / 2, y - d / 2, d, d);
		g2.setColor(COLOR_LINE);
		g2.setStroke(new BasicStroke(2f));
		g2.drawOval(x - d / 2, y - d / 2, d, d);
		g2.setColor(Color.BLACK);
		g2.drawString("" + d, x - d / 5, y + 3);
	}

}

package core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Link 
{

	private Agent agentStart;
	private Agent agentEnd;
	private double weight;
	
	public Link(Agent agentStart, Agent agentEnd, double weight)
	{
		this.agentEnd = agentEnd;
		this.agentStart = agentStart;
		this.weight = weight;
	}
	
	public boolean isLinkedTo(Agent agent)
	{
		return (agent.equals(agentEnd) || agent.equals(agentStart));
	}
	
	/**
	 * @return the agentStart
	 */
	public Agent getAgentStart() 
	{
		return agentStart;
	}

	/**
	 * @param agentStart the agentStart to set
	 */
	public void setAgentStart(Agent agentStart) 
	{
		this.agentStart = agentStart;
	}

	/**
	 * @return the agentEnd
	 */
	public Agent getAgentEnd() {
		return agentEnd;
	}

	/**
	 * @param agentEnd the agentEnd to set
	 */
	public void setAgentEnd(Agent agentEnd) {
		this.agentEnd = agentEnd;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void draw(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 0));
		g2.setStroke(new BasicStroke(3f));
		double D = Math.sqrt(Math.pow(agentStart.getX() - agentEnd.getX(), 2) + Math.pow(agentEnd.getY() - agentStart.getY(), 2));
		double X = agentEnd.getX() - agentStart.getX();
		double Y = agentEnd.getY() - agentStart.getY();
		int deltaXStart = (int) ((double) (agentStart.getD() * X / D)) / 2;
		int deltaYStart = (int) ((double) (agentStart.getD() * Y / D)) / 2;
		int deltaXEnd = (int) ((double) (agentEnd.getD() * X / D)) / 2;
		int deltaYEnd = (int) ((double) (agentEnd.getD() * Y / D)) / 2;

		g2.drawLine(agentStart.getX() + deltaXStart, agentStart.getY() + deltaYStart, agentEnd.getX() - deltaXEnd, agentEnd.getY() - deltaYEnd);
	}

}

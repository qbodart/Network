package core;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ViewPanel extends JPanel 
{

    private static final long serialVersionUID = 2020984762866626686L;
    private ArrayList<Agent> selectedAgents;
    private Point2D startPoint;
    private boolean addLink = false;
    private Link link;

    public ViewPanel()
    {
	this.selectedAgents = new ArrayList<Agent>();
	this.addMouseListener(new MouseAdapter() 
	{
	    public void mousePressed(MouseEvent arg0)
	    {
		ArrayList<Agent> agents = Network.getInstance().getAgents();
		for(Agent a : agents)
		{
		    if(a.contains(arg0.getPoint()))
		    {
			startPoint = arg0.getPoint();
			a.setSelected(true);
		    }
		    else if(addLink && !selectedAgents.isEmpty())
		    {
			Agent currentPosition = new Agent(0);
			currentPosition.setX(arg0.getX());
			currentPosition.setY(arg0.getY());
			link = new Link(selectedAgents.get(0), currentPosition, 5);
		    }
		    else
		    {
			a.setSelected(false);
		    }
		}
		Network.getInstance().repaint();
	    }

	    public void mouseReleased(MouseEvent arg0)
	    {
		selectedAgents.clear();
		if(addLink)
		{
		    ArrayList<Agent> agents = Network.getInstance().getAgents();
		    for(Agent a : agents)
		    {
			if(a.contains(arg0.getPoint()) && !selectedAgents.contains(a))
			{
			    link.setAgentEnd(a);
			    Network.getInstance().addLink(link);
			}
		    }
		}
		link = null;
		Network.getInstance().repaint();
	    }
	});

	this.addMouseMotionListener(new MouseMotionAdapter() 
	{	
	    public void mouseDragged(MouseEvent arg0) 
	    {
		if(addLink && !selectedAgents.isEmpty())
		{
		    Agent currentPosition = new Agent(0);
		    currentPosition.setX(arg0.getX());
		    currentPosition.setY(arg0.getY());
		    link = new Link(selectedAgents.get(0), currentPosition, 5);
		    ArrayList<Agent> agents = Network.getInstance().getAgents();
		    for(Agent a : agents)
		    {
			if(a.contains(arg0.getPoint()))
			{
			    a.setSelected(true);
			}
			else if(!a.contains(arg0.getPoint()) && !a.equals(selectedAgents.get(0)))
			{
			    a.setSelected(false);
			}
		    }
		}
		else if(!selectedAgents.isEmpty())
		{
		    int dx = (int) (arg0.getX() - startPoint.getX());
		    int dy = (int) (arg0.getY() - startPoint.getY());
		    startPoint = arg0.getPoint();
		    for(Agent a : selectedAgents)
		    {
			a.setX(a.getX() + dx);
			a.setY(a.getY() + dy);
		    }
		}
		Network.getInstance().repaint();
	    }
	});
    }

    public void setAddLink()
    {
	this.addLink = !this.addLink;
    }

    @Override
    public void paintComponent(Graphics g)
    {
	for(Link l : Network.getInstance().getLinks())
	{
	    l.draw(g);
	}
	for(Agent a : Network.getInstance().getAgents())
	{
	    a.draw(g);
	}
	if(link != null)
	{
	    link.draw(g);
	}
	int cx = Network.getInstance().getWidth() / 2;
	int cy = Network.getInstance().getHeight() / 2;
	g.drawOval(cx - 1, cy - 1, 2, 2);
    }
}

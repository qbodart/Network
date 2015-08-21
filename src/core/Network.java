package core;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Network extends JFrame
{

    private static final long serialVersionUID = 2629602729724555623L;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private ArrayList<Link> links;
    private ArrayList<Agent> agents;
    private ViewPanel viewPanel;
    private static Network instance;

    private Network ()
    {
	this.links = new ArrayList<Link>();
	this.agents = new ArrayList<Agent>();
	this.viewPanel = new ViewPanel();
	this.setLayout(new BorderLayout());
	ControlPanel controlPanel = new ControlPanel();
	this.add(controlPanel, BorderLayout.NORTH);
	this.add(viewPanel, BorderLayout.CENTER);
	this.setResizable(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(WIDTH, HEIGHT);
	this.setVisible(true);

    }

    public static Network getInstance()
    {
	if(instance == null)
	{
	    instance = new Network();
	    return instance;
	}
	else
	{
	    return instance;
	}
    }

    public ArrayList<Agent> getAgents()
    {
	return agents;
    }

    public void addAgent(Agent agent)
    {
	this.agents.add(agent);
    }

    public ArrayList<Link> getLinks()
    {
	return links;
    }

    public void addLink(Link link)
    {
	links.add(link);
    }

    public void organiseAgents()
    {
	for(Agent a : agents)
	{
	    double weight = 0.0;
	    for(Link l : links)
	    {
		if(a.equals(l.getAgentEnd()) || a.equals(l.getAgentStart()))
		{
		    weight += l.getWeight();
		}
	    }
	    a.setD((int) (weight / 2)); 
	}
	AgentsOrganiser organiser = new StarOrganiser();
	organiser.organise(agents);
	viewPanel.repaint();
	this.repaint();
    }

    public void setAddLinkMode()
    {
	this.viewPanel.setAddLink();
    }
}

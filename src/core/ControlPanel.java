package core;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ControlPanel extends JPanel 
{

	private static final long serialVersionUID = 8575672083538383281L;
	
	public ControlPanel()
	{
		JButton organise = new JButton("Organise");
		organise.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Network.getInstance().organiseAgents();
			}
		});
		this.add(organise);
		JButton addAgent = new JButton("Add an agent");
		addAgent.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Agent a = new Agent(3);
				a.setX(Network.getInstance().getWidth() / 2);
				a.setY(Network.getInstance().getHeight() / 2);
				Network.getInstance().addAgent(a);
				Network.getInstance().repaint();
			}
		});
		this.add(addAgent);
		JButton deleteAgent = new JButton("Delete agent");
		deleteAgent.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ArrayList<Agent> agents = Network.getInstance().getAgents();
				ArrayList<Link> links = Network.getInstance().getLinks();
				ArrayList<Link> toRemove = new ArrayList<Link>();
				for(Agent a : agents)
				{
					if(a.isSelected())
					{
						for(Link l : links)
						{
							if(l.isLinkedTo(a))
							{
								toRemove.add(l);
							}
						}
						links.removeAll(toRemove);
						agents.remove(a);
						Network.getInstance().repaint();
						break;
					}
				}
			}
		});
		this.add(deleteAgent);
		JToggleButton addLink = new JToggleButton("Add a link");
		addLink.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Network.getInstance().setAddLinkMode();
			}
		});
		this.add(addLink);
		JButton selectAll = new JButton("Select all");
		selectAll.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ArrayList<Agent> agents = Network.getInstance().getAgents();
				for(Agent a : agents)
				{
					a.setSelected(true);
				}
				Network.getInstance().repaint();
			}
		});
		this.add(selectAll);
		JButton deleteAll = new JButton("Delete all");
		deleteAll.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Network.getInstance().getAgents().removeAll(Network.getInstance().getAgents());
				Network.getInstance().getLinks().removeAll(Network.getInstance().getLinks());
				Network.getInstance().repaint();
			}
		});
		this.add(deleteAll);
		this.setBackground(Color.DARK_GRAY);
	}
}

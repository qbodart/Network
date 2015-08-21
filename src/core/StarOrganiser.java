package core;

import java.util.ArrayList;

public class StarOrganiser implements AgentsOrganiser 
{

	public void organise(ArrayList<Agent> agents) 
	{
		Network network = Network.getInstance();
		int cx = network.getWidth() / 2;
		int cy = network.getHeight() / 2;
		int numberAgents = network.getAgents().size();
		double degrees = 360.0 / numberAgents;
		int d = Math.min(network.getWidth() * 2 / 3, network.getHeight() * 2 / 3) / 2;
		for(int i = 0; i < agents.size(); i++)
		{
			double angle = i * degrees;
			agents.get(i).setX(cx + (int) (d * Math.sin(Math.toRadians(angle))));
			agents.get(i).setY(cy - (int) (d * Math.cos(Math.toRadians(angle))));

		}
	}

}

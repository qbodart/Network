package core;

import java.util.ArrayList;

public class RandomOrganiser implements AgentsOrganiser
{

	public void organise(ArrayList<Agent> agents) 
	{
		for(Agent a : agents)
		{
			a.setX((int) (Math.random() * (Network.getInstance().getWidth() - a.getD() - 50) + a.getD()));
			a.setY((int) (Math.random() * (Network.getInstance().getHeight() - a.getD() - 50) + a.getD()));
		}
	}

}

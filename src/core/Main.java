package core;

import util.Matrix;


public class Main 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Network network = Network.getInstance();
		int numberAgents = 20;
		Matrix m = new Matrix(numberAgents, numberAgents);
		for(int i = 0; i < numberAgents; i++)
		{
			for(int j = 0; j < numberAgents; j++)
			{
				if(i != j)
				{
					m.set(i, j, Math.random() * 10);
				}
			}
		}
		for(int i = 0; i < numberAgents; i++)
		{
			network.addAgent(new Agent(30));
		}

		for(int i = 0; i < network.getAgents().size(); i++)
		{
			for(int j = 0; j < network.getAgents().size(); j++)
			{
				
				network.addLink(new Link( network.getAgents().get(i), network.getAgents().get(j), 2));
			}
		}
		network.organiseAgents();
	}

}

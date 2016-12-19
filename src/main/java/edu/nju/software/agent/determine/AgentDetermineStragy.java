package edu.nju.software.agent.determine;

import edu.nju.software.agent.Agent;

public interface  AgentDetermineStragy {

	public void diffusePerception(double agentWeight,double edgeWeight,double output);

	public double determine(Agent agent);
	
}

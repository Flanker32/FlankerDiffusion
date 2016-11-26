package edu.nju.software.determine;

import edu.nju.software.Agent;

public interface  AgentDetermineStragy {

	public void diffusePerception(double agentWeight,double edgeWeight,double output);

	public double determine(Agent agent);
	
}

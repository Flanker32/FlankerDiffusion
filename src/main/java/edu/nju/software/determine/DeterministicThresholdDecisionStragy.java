package edu.nju.software.determine;

import edu.nju.software.Agent;

public class DeterministicThresholdDecisionStragy implements AgentDetermineStragy{

	private double sensor = 0.0;

	public void diffusePerception(double agentWeight, double edgeWeight, double output) {
		sensor += agentWeight*edgeWeight*output;
	}

	public double determine(Agent agent) {
		return 0;
	}

//	private double threshold;
//
//	public DeterministicThresholdDecisionStragy(double threshold)
//	{
//		this.threshold=threshold;
//	}
//
//	@Override
//	public double determine(double[] stragy, double[] weight, double[] degree)
//	{
//		double diffusionSensor = this.diffusionSensor(stragy, weight, degree);
//		if(diffusionSensor>=threshold){
//			return diffusionSensor;
//		}else{
//			return 0;
//		}
//	}

}

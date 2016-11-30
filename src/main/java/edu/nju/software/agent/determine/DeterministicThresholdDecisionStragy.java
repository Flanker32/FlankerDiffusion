package edu.nju.software.agent.determine;

import edu.nju.software.agent.Agent;

public class DeterministicThresholdDecisionStragy implements AgentDetermineStragy{

	private boolean binary = false;
	private double value = 0.0;
	private double sensor = 0.0;

	public DeterministicThresholdDecisionStragy(boolean binary){
		this.binary = binary;
	}

	public void diffusePerception(double agentWeight, double edgeWeight, double output) {
		value=output;
		sensor += agentWeight*edgeWeight*output;
	}

	public double determine(Agent agent) {
		double result = 0.0;
		double threshold = agent.getThreshold();

		if(sensor>=threshold){
			if(binary){
				result = 1;
			}else{
				result = value;
			}
		}
		sensor=0;
		value = 0;
		return result;
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

package edu.nju.software.agent.determine;

import edu.nju.software.agent.Agent;

public class ProbabilisticThresholdDecisionStragy implements AgentDetermineStragy{

	private boolean binary = false;
	private double value = 0.0;
	private double sensor = 0.0;
    private double probablity = 0.0;

	public ProbabilisticThresholdDecisionStragy(boolean binary,double probablity){
		this.probablity=probablity;
	    this.binary=binary;
	}


	public void diffusePerception(double agentWeight, double edgeWeight, double output) {
		sensor += agentWeight*edgeWeight*output;
	}

	public double determine(Agent agent) {
		double result = 0.0;
		double threshold = agent.getThreshold();

		if((sensor>=threshold)&&(Math.random()<probablity)){
			if(binary){
				result = 1;
			}else{
				result = value;
			}
		}
		sensor=0;
		return result;
	}

//	double probability;
//	double threshold;
//
//	public ProbabilisticThresholdDecisionStragy(double probability,double threshold)
//	{
//		this.probability=probability;
//		this.threshold=threshold;
//	}
//	@Override
//	public double determine(double[] stragy, double[] weight, double[] degree) {
//		double diffusionSensor = this.diffusionSensor(stragy, weight, degree);
//		if((diffusionSensor>=threshold)&&(Math.random()<probability)){
//			return diffusionSensor;
//		}else{
//			return 0;
//		}
//	}
}

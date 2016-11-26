package edu.nju.software.determine;

import edu.nju.software.Agent;

public class ProbabilisticThresholdDecisionStragy implements AgentDetermineStragy{

	public void diffusePerception(double agentWeight, double edgeWeight, double output) {

	}

	public double determine(Agent agent) {
		return 0;
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

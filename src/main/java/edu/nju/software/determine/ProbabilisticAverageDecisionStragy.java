package edu.nju.software.determine;

import edu.nju.software.Agent;

public class ProbabilisticAverageDecisionStragy implements AgentDetermineStragy{
	public void diffusePerception(double agentWeight, double edgeWeight, double output) {

	}

	public double determine(Agent agent) {
		return 0;
	}

//	@Override
//	public double determine(double[] stragy, double[] weight, double[] degree) {
//		double neighborAverage = calNeighborAverage(weight,degree);
//		double diffusionSensor = this.diffusionSensor(stragy, weight, degree);
//
//		double expectOutput = diffusionSensor/neighborAverage;
//		double probability = calProbability(stragy, weight,degree);
//		if(Math.random()<probability){
//			return expectOutput;
//		}else{
//			return 0.0;
//		}
//	}
//
//	private double calNeighborAverage(double[] weight, double[] degree){
//		double result = 0.0;
//		for(int i=0;i<weight.length;i++){
//			result+=weight[i]*degree[i];
//		}
//		return result;
//	}
//
//	private double calProbability(double[] stragy, double[] weight, double[] degree){
//		double result = 0.0;
//		for(int i=0;i<stragy.length;i++){
//			if(stragy[i]>0){
//				result+=weight[i]*degree[i];
//			}
//		}
//		result = result/calNeighborAverage(weight,degree);
//		return result;
//	}
}

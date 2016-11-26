package edu.nju.software.determine;

import edu.nju.software.Agent;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DeterministicAverageDecisionStragy implements AgentDetermineStragy {

	private static final double THRESHOLD = 0.5;

	private boolean binary = false;
	private double sensor = 0.0;

	public DeterministicAverageDecisionStragy(boolean binary){
		this.binary = binary;
	}

	public void diffusePerception(double agentWeight, double edgeWeight, double output) {
		sensor += agentWeight*edgeWeight*output;
	}

	public double determine(Agent agent) {
		double result = 0.0;
		double neighborAvg = calNeighborAvg(agent);
		result = sensor/neighborAvg;
		if(binary){
			if(result>=THRESHOLD){
				result =1;
			}else{
				result = 0;
			}
		}
		sensor=0;
		return result;
	}


	private double calNeighborAvg(Agent agent){
		double result = 0.0;
		HashMap<Agent,Double> neighbors = agent.getFrontAgent();
		for(Agent a:neighbors.keySet()){
			double edgeWeight = neighbors.get(a);
			double agentWeight = a.getWeight();
			result+=edgeWeight*agentWeight;
		}
		return result;
	}

//	@Override
//	public double determine(double[] stragy, double[] weight, double[] degree) {
//		double neighborAverage = calNeighborAverage(weight,degree);
//		double diffusionSensor = this.diffusionSensor(stragy, weight, degree);
//		double expectOutput = diffusionSensor/neighborAverage;
//		return expectOutput;
//	}
//
//	private double calNeighborAverage(double[] weight, double[] degree){
//		double result = 0.0;
//		for(int i=0;i<weight.length;i++){
//			result+=weight[i]*degree[i];
//		}
//		return result;
//	}
}

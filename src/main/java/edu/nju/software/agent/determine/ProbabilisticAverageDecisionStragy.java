package edu.nju.software.agent.determine;

import edu.nju.software.agent.Agent;

import java.util.HashMap;

public class ProbabilisticAverageDecisionStragy implements AgentDetermineStragy {

    private boolean binary = false;
    private double sensor = 0.0;
    private double value=0; //对于输出》0的上级节点 他的地位与边权重乘积和 用于计算概率
    private static final double threshold = 0.5;

    public ProbabilisticAverageDecisionStragy(boolean binary) {
        this.binary = binary;
    }

    public void diffusePerception(double agentWeight, double edgeWeight, double output) {
        value +=agentWeight*edgeWeight;
        sensor += agentWeight * edgeWeight * output;
    }

    public double determine(Agent agent) {
        double result = 0.0;
        double probablity = value/calNeighborAvg(agent);
        result = sensor/calNeighborAvg(agent);
        if(Math.random()<probablity){
            if(binary){
                if(result>=threshold){
                    result = 1;
                }else{
                    result = 0;
                }
            }
        }else{
            result = 0.0;
        }
        value=0;
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

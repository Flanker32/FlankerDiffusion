package edu.nju.software.determine;

public class DeterministicAverageDecisionStragy extends AgentDetermineStragy {
	
	@Override
	public double determine(double[] stragy, double[] weight, double[] degree) {
		double neighborAverage = calNeighborAverage(weight,degree);
		double diffusionSensor = this.diffusionSensor(stragy, weight, degree);
		double expectOutput = diffusionSensor/neighborAverage;
		return expectOutput;
	}

	private double calNeighborAverage(double[] weight, double[] degree){
		double result = 0.0;
		for(int i=0;i<weight.length;i++){
			result+=weight[i]*degree[i];
		}
		return result;		
	}
}

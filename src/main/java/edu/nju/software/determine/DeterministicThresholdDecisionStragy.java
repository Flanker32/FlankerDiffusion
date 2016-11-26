package edu.nju.software.determine;

public class DeterministicThresholdDecisionStragy extends AgentDetermineStragy{
	
	private double threshold;
	
	public DeterministicThresholdDecisionStragy(double threshold)
	{
		this.threshold=threshold;
	}
	
	@Override
	public double determine(double[] stragy, double[] weight, double[] degree) 
	{
		double diffusionSensor = this.diffusionSensor(stragy, weight, degree);
		if(diffusionSensor>=threshold){
			return diffusionSensor;
		}else{
			return 0;
		}		
	}

}

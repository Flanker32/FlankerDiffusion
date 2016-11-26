package edu.nju.software.determine;

public abstract class AgentDetermineStragy {

	protected double diffusionSensor(double[] stragy,double[] weight,double[] degree){
		assert(stragy.length==weight.length&&weight.length==degree.length);
		double result = 0.0;
		int length = stragy.length;
		for(int i=0;i<length;i++)
		{
			result+=stragy[i]*weight[i]*degree[i];
		}
		return result;
	}
	
	public abstract double determine(double[] stragy,double[] weight,double[] degree);
	
}

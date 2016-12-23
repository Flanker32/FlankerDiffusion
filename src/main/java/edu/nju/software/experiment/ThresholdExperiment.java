package edu.nju.software.experiment;

import edu.nju.software.Util;
import edu.nju.software.agent.StrategyType;
import edu.nju.software.bean.MultiDiffusionResult;
import edu.nju.software.bean.NetworkParameter;
import edu.nju.software.network.Network;
import edu.nju.software.network.NetworkFactory;

/**
 * Created by Flanker on 2016/12/20.
 */
public class ThresholdExperiment {

    private Network network;

    public ThresholdExperiment(NetworkParameter networkParameter){
        network = NetworkFactory.generateNetwork(networkParameter);
    }
    public void experimentThreshold(int[] thresholds, int variance, int expRound, double startValue, double percentage, int diffusionMaxRound)
    {
        network.changeAgentDetermineStragy(StrategyType.DetThr,true);
        thresholdExperiment(thresholds,variance,expRound, startValue, percentage,diffusionMaxRound,"ThresholdTest_DetThr.txt");
        network.changeAgentDetermineStragy(StrategyType.DetAvg,true);
        thresholdExperiment(thresholds,variance,expRound, startValue, percentage,diffusionMaxRound,"ThresholdTest_DetAvg.txt");
    }

    public void experimentStartPercentage(double[] percentage,double startValue,int diffusionMaxRound,int expRound)
    {
        network.changeAgentDetermineStragy(StrategyType.DetThr,true);
        percentageExperiment(percentage, startValue, diffusionMaxRound,"StartPercentageTest_DetThr.txt",expRound);
        network.changeAgentDetermineStragy(StrategyType.DetAvg,true);
        percentageExperiment(percentage, startValue, diffusionMaxRound,"StartPercentageTest_DetAvg.txt",expRound);
    }

    public void percentageExperiment(double[] percentage,double startValue,int diffusionMaxRound,String name,int expRound){
        int number = percentage.length;
        MultiDiffusionResult[] results = new MultiDiffusionResult[number];
        for(int i=0;i<number;i++){
            results[i]=network.startMultiDiffusion(percentage[i],startValue,diffusionMaxRound,expRound);
        }

        Util.writeMultiDiffusionResultToFile(results,name);
    }

    public void thresholdExperiment(int[] thresholds,int variance,int expRound,double startValue,double percentage,int diffusionMaxRound,String name){
        int number = thresholds.length;
        MultiDiffusionResult[] results = new MultiDiffusionResult[number];
        for(int i=0;i<number;i++){
            network.changeAgentThreshold(thresholds[i],variance);
            results[i]=network.startMultiDiffusion(percentage,startValue,diffusionMaxRound,expRound);
        }
        Util.writeMultiDiffusionResultToFile(results,name);
    }
}

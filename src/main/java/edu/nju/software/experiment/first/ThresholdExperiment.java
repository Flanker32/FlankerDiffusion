package edu.nju.software.experiment.first;

import edu.nju.software.agent.StrategyType;
import edu.nju.software.bean.MultiDiffusionResult;
import edu.nju.software.bean.NetworkParameter;
import edu.nju.software.network.Network;
import edu.nju.software.network.NetworkFactory;
import edu.nju.software.network.NetworkHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flanker on 2016/12/20.
 */
public class ThresholdExperiment {

    private Network network;

    public ThresholdExperiment(NetworkParameter networkParameter){
        network = NetworkFactory.generateNetwork(networkParameter);
    }
    public void experimentThreshold(int[] thresholds, int variance, int expRound, double startValue, double percentage, int maxRoundPerExp)
    {
        network.changeAgentDetermineStragy(StrategyType.DetThr,true);
        thresholdExperiment(thresholds,variance,expRound, startValue, percentage,maxRoundPerExp,"ThresholdTest_DetThr.txt");
        network.changeAgentDetermineStragy(StrategyType.DetAvg,true);
        thresholdExperiment(thresholds,variance,expRound, startValue, percentage,maxRoundPerExp,"ThresholdTest_DetAvg.txt");
    }

    public void experimentStartPercentage(double[] percentage,double startValue,int maxRoundPerExp,int expRound)
    {
        network.changeAgentDetermineStragy(StrategyType.DetThr,true);
        percentageExperiment(percentage, startValue, maxRoundPerExp,"StartPercentageTest_DetThr.txt",expRound);
        network.changeAgentDetermineStragy(StrategyType.DetAvg,true);
        percentageExperiment(percentage, startValue, maxRoundPerExp,"StartPercentageTest_DetAvg.txt",expRound);
    }

    public void percentageExperiment(double[] percentage,double startValue,int maxRoundPerExp,String name,int expRound){
        int number = percentage.length;
        List<MultiDiffusionResult> results = new ArrayList<>();
        for(int i=0;i<number;i++){
            results.add(network.startMultiDiffusion(percentage[i],startValue,maxRoundPerExp,expRound));
        }
        NetworkHelper.writeMultiDiffusionResultToFile(results,name);
    }

    public void thresholdExperiment(int[] thresholds,int variance,int expRound,double startValue,double percentage,int maxRoundPerExp,String name){
        int number = thresholds.length;
        List<MultiDiffusionResult> results = new ArrayList<>();
        for(int i=0;i<number;i++){
            network.changeAgentThreshold(thresholds[i],variance);
            results.add(network.startMultiDiffusion(percentage,startValue,maxRoundPerExp,expRound));
        }
        NetworkHelper.writeMultiDiffusionResultToFile(results,name);
    }
}

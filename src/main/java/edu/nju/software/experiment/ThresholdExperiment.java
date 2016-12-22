package edu.nju.software.experiment;

import edu.nju.software.IOHelper;
import edu.nju.software.agent.StrategyType;
import edu.nju.software.bean.MultiDiffusionResult;
import edu.nju.software.bean.NetworkParameter;
import edu.nju.software.network.Network;
import edu.nju.software.network.NetworkFactory;

import java.text.DecimalFormat;
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

        int maxRound = 0;
        for(MultiDiffusionResult diffusionResult:results){
            if(diffusionResult.getMaxDiffusionRound()>maxRound){
                maxRound=diffusionResult.getMaxDiffusionRound();
            }
        }

        DecimalFormat df = new DecimalFormat("0.00");
        List<String> termOutput = new ArrayList<String>();
        List<String> totalOutput = new ArrayList<String>();
        double[] total = new double[number];
        for(int i=0;i<maxRound;i++){
            double[] perTerm = new double[number];
            for(int j=0;j<number;j++){
                MultiDiffusionResult diffusionResult = results[j];
                if(i<diffusionResult.getMaxDiffusionRound()){
                    perTerm[j]=diffusionResult.getAverageDiffusePerTerm(i);
                    total[j]+=perTerm[j];
                }else{
                    perTerm[j]=0;
                }
            }
            String termOut = new String();
            String totalOut = new String();
            for(int j=0;j<number;j++){
                termOut = termOut+df.format(perTerm[j])+",";
                totalOut = totalOut+df.format(total[j])+",";
            }
            termOutput.add(termOut);
            totalOutput.add(totalOut);
        }

        IOHelper.writeToFile("term_"+name,termOutput);
        IOHelper.writeToFile("total_"+name,totalOutput);
    }

    public void thresholdExperiment(int[] thresholds,int variance,int expRound,double startValue,double percentage,int diffusionMaxRound,String name){
        int number = thresholds.length;
        MultiDiffusionResult[] results = new MultiDiffusionResult[number];
        for(int i=0;i<number;i++){
            network.changeAgentThreshold(thresholds[i],variance);
            results[i]=network.startMultiDiffusion(percentage,startValue,diffusionMaxRound,expRound);
        }
        int maxRound = 0;
        for(MultiDiffusionResult diffusionResult:results){
            if(diffusionResult.getMaxDiffusionRound()>maxRound){
                maxRound=diffusionResult.getMaxDiffusionRound();
            }
        }

        DecimalFormat df = new DecimalFormat("0.00");
        List<String> termOutput = new ArrayList<String>();
        List<String> totalOutput = new ArrayList<String>();
        double[] total = new double[number];
        for(int i=0;i<maxRound;i++){
            double[] perTerm = new double[number];
            for(int j=0;j<number;j++){
                MultiDiffusionResult diffusionResult = results[j];
                if(i<diffusionResult.getMaxDiffusionRound()){
                    perTerm[j]=diffusionResult.getAverageDiffusePerTerm(i);
                    total[j]+=perTerm[j];
                }else{
                    perTerm[j]=0;
                }
            }
            String termOut = df.format(perTerm[0])+","+df.format(perTerm[1])+","+df.format(perTerm[2]);
            String totalOut = df.format(total[0])+","+df.format(total[1])+","+df.format(total[2]);
            termOutput.add(termOut);
            totalOutput.add(totalOut);
        }

        IOHelper.writeToFile("term_"+name,termOutput);
        IOHelper.writeToFile("total_"+name,totalOutput);
    }
}

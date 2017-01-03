package edu.nju.software.network;

import edu.nju.software.IOHelper;
import edu.nju.software.bean.DiffusionResult;
import edu.nju.software.bean.MultiDiffusionResult;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Dell on 2017/1/2.
 */
public class NetworkHelper {
    public static MultiDiffusionResult analyzeMultiDiffusionResult(List<DiffusionResult> results , int maxDiffuseRound, int expTimes){
        MultiDiffusionResult result = new MultiDiffusionResult(expTimes);
        int agentNumber = results.get(0).getAgentCount();
        int edgeNumber = results.get(0).getEdgeCount();
        result.setAgentCount(agentNumber);
        result.setEdgeCount(edgeNumber);

        int actualMaxRound = 0;
        double averageAffectedAgentCount = 0;
        double[] averageDiffusePerTerm = new double[maxDiffuseRound];
        int[] agentStatus = new int[agentNumber];
        for(DiffusionResult diffusionResult:results){
            if(diffusionResult.getDiffusionRound()>actualMaxRound){
                actualMaxRound = diffusionResult.getDiffusionRound();
            }
            averageAffectedAgentCount+=diffusionResult.getAffectedAgentCount();
            for(int m=0;m<diffusionResult.getDiffusePerTerm().length;m++){
                averageDiffusePerTerm[m]+=diffusionResult.getDiffusePerTerm()[m];
            }
            for(int m=0;m<agentNumber;m++){
                if(diffusionResult.getAgentStatus()[m]){
                    agentStatus[m]++;
                }
            }
        }
        averageAffectedAgentCount = averageAffectedAgentCount/expTimes;
        for(int i=0;i<averageDiffusePerTerm.length;i++){
            averageDiffusePerTerm[i]=averageDiffusePerTerm[i]/expTimes;
        }

        result.setAgentStatus(agentStatus);
        result.setAverageAffectedAgentCount(averageAffectedAgentCount);
        result.setAverageDiffusePerTerm(averageDiffusePerTerm);
        result.setMaxDiffusionRound(maxDiffuseRound);
        return result;
    }

    //根据Agent数量和初级激活比例生成初始激活节点
    public static  int[] getStartAgents(int agentNumber, double startPercentage) {
        int number = (int) Math.floor(agentNumber * startPercentage);
        int[] result = new int[number];
        HashSet<Integer> set = new HashSet<Integer>();
        while (set.size() < number) {
            int randomNumber = (int) Math.floor(agentNumber * Math.random());
            set.add(randomNumber);
        }
        int count = 0;
        for (Integer integer : set) {
            result[count++] = integer;
        }

        return result;
    }

    //将多个多次试验的结果输出
    public static void writeMultiDiffusionResultToFile(List<MultiDiffusionResult> resultList, String fileName){
        int resultNumber = resultList.size();
        int maxRound = 0;
        for(MultiDiffusionResult diffusionResult:resultList){
            if(diffusionResult.getMaxDiffusionRound()>maxRound){
                maxRound=diffusionResult.getMaxDiffusionRound();
            }
        }

        DecimalFormat df = new DecimalFormat("0.00");
        List<String> termOutput = new ArrayList<String>();
        List<String> totalOutput = new ArrayList<String>();
        double[] total = new double[resultNumber];
        double[] perTerm = new double[resultNumber];
        for(int i=0;i<maxRound;i++){
            for(int j=0;j<resultNumber;j++){
                MultiDiffusionResult diffusionResult = resultList.get(j);
                if(i<diffusionResult.getMaxDiffusionRound()){
                    perTerm[j]=diffusionResult.getAverageDiffusePerTerm(i);
                    total[j]+=perTerm[j];
                }else{
                    perTerm[j]=0;
                }
            }
            String termOut = new String();
            String totalOut = new String();
            for(int j=0;j<resultNumber;j++){
                termOut = termOut+ df.format(perTerm[j])+",";
                totalOut = totalOut + df.format(total[j])+",";
            }
            termOutput.add(termOut);
            totalOutput.add(totalOut);
        }

        IOHelper.writeToFile(fileName+"_TERM.txt",termOutput);
        IOHelper.writeToFile(fileName+"_TOTAL.txt",totalOutput);
    }
}

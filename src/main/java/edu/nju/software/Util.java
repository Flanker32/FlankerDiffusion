package edu.nju.software;

import edu.nju.software.bean.MultiDiffusionResult;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Created by Dell on 2016/11/26.
 */
public class Util {
    private static Random r = new Random();

    public static double generageNormalValue(double mean,double variance){
        double temple =r.nextGaussian();
        temple = temple*Math.sqrt(variance);
        temple = temple + mean;
        return temple;
    }

    public static double generagePositiveNormalValue(double mean,double variance){
        double temple = generageNormalValue(mean,variance);
        if(temple<=0){
            return Math.abs(temple);
        }else{
            return temple;
        }
    }

    public static void writeMultiDiffusionResultToFile(MultiDiffusionResult[] resultList, String fileName){
        int resultNumber = resultList.length;
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
                MultiDiffusionResult diffusionResult = resultList[j];
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

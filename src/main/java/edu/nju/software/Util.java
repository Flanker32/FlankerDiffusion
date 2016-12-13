package edu.nju.software;

import java.util.HashSet;
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
            return 0;
        }else{
            return temple;
        }
    }

    public static int[] getStartAgents(int agentNumber, double startPercentage) {
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
}

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

    public static void showIntList(int[] list){
        for(int i:list){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}

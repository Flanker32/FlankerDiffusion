package edu.nju.software.experiment;

import edu.nju.software.network.Network;

/**
 * Created by Dell on 2016/12/7.
 */
public class ExpFirstNetwork {
    private Network network;
    private int expTerms = 0;

    public ExpFirstNetwork(Network network) {
        this.network = network;
    }

    public void similarityTest(int startValue, int diffusionRound, int[] startPoints,int terms) {
        int size = network.getSize();
        double[] result = new double[size];
        int [] activedNumbers = new int[size];

        for(int i=0;i<terms;i++){
            network.startDiffusion(startPoints,startValue,diffusionRound);
            boolean[] temp = network.getAgentStatus();
            for(int j=0;j<size;j++){
                if(temp[j]){
                    activedNumbers[j]++;
                }
            }
        }

        double temp = 0.0;
        double unSameCount = 0;
        for(int i=0;i<size;i++){
            if(activedNumbers[i]!=0&&activedNumbers[i]!=terms){
//                System.out.print(i+" ");
                unSameCount++;
            }
        }
        for(int i=0;i<size;i++){
            double distance = activedNumbers[i]-((double)terms)/2;

            double temp1 = 4*Math.pow(distance,2);
            double temp2 = Math.pow(terms,2);
            result[i]=temp1/temp2;
            temp +=result[i];
        }
        System.out.println();
        System.out.println("平均决策统一程度"+(temp/size));
        System.out.println("共有"+unSameCount+"个节点决策有异");
    }
}

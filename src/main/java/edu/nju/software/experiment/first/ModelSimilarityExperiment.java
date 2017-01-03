package edu.nju.software.experiment.first;

import edu.nju.software.Util;
import edu.nju.software.agent.Agent;
import edu.nju.software.agent.StrategyType;
import edu.nju.software.bean.MultiDiffusionResult;
import edu.nju.software.bean.NetworkParameter;
import edu.nju.software.network.Network;
import edu.nju.software.network.NetworkFactory;
import edu.nju.software.network.NetworkHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flanker on 2016/12/23.
 */
public class ModelSimilarityExperiment {
    Network network;
    private static final int AGENT_NUMBER =10;
    private static final int GROUP_NUMBER =3;

    public ModelSimilarityExperiment(NetworkParameter networkParameter){
        network = NetworkFactory.generateNetwork(networkParameter);
    }


    public void experiment(double startPercentage, double startValue,int maxRound,int expRound){
//        network.changeAgentDetermineStragy(StrategyType.DetThr,true);
//        singleExperiment(startValue,maxRound,expRound,"DetThe");
        network.changeAgentDetermineStragy(StrategyType.ProThr,true);
        singleExperiment(startValue,maxRound,expRound,"ProThr");
//        network.changeAgentDetermineStragy(StrategyType.DetAvg,true);
//        singleExperiment(startPercentage,startValue,maxRound,expRound,"DetAvg");
//        network.changeAgentDetermineStragy(StrategyType.ProAvg,true);
//        singleExperiment(startPercentage,startValue,maxRound,expRound,"ProAvg");
    }

    private void singleExperiment(double startValue,int maxRound,int expRound,String name){
        int point = (int)(network.getSize()*Math.random());
        List<Agent> list = network.calSimilarityPoints(point);

        int[] first = new int[AGENT_NUMBER];
        int[] second = new int[AGENT_NUMBER];
        int[] third = new int[AGENT_NUMBER];

        int[] firstSub = new int[AGENT_NUMBER-1];
        int[] secondSub = new int[AGENT_NUMBER-1];
        int[] thirdSub = new int[AGENT_NUMBER-1];


        int firstCount = 0;
        int secondCount = 0;
        int thirdCount = 0;
        for(int i=0;i< AGENT_NUMBER*GROUP_NUMBER;i++){
            switch(i%3){
                case 0:
                    first[firstCount++]=list.get(i).getId();
                    if(firstCount!=1){
                        firstSub[firstCount-2]=list.get(i).getId();
                    }
                    break;
                case 1:
                    second[secondCount++]=list.get(i).getId();
                    if(secondCount!=1){
                        secondSub[secondCount-2]=list.get(i).getId();
                    }
                    break;
                case 2:
                    third[thirdCount++]=list.get(i).getId();
                    if(thirdCount!=1){
                        thirdSub[thirdCount-2]=list.get(i).getId();
                    }
                    break;
                default:
                    break;
            }
        }

        List<MultiDiffusionResult> temp = new ArrayList<>();
        MultiDiffusionResult firstResult = network.startMultiDiffusion(first,startValue,maxRound,expRound);
        System.out.println("Finish 1~");
        MultiDiffusionResult secondResult = network.startMultiDiffusion(second,startValue,maxRound,expRound);
        System.out.println("Finish 2~");
        MultiDiffusionResult thirdResult = network.startMultiDiffusion(third,startValue,maxRound,expRound);
        System.out.println("Finish 3~");
        temp.add(firstResult);
        temp.add(secondResult);
        temp.add(thirdResult);

        NetworkHelper.writeMultiDiffusionResultToFile(temp,"ModelSimilarity_"+name);


        temp.clear();
        firstResult = network.startMultiDiffusion(firstSub,startValue,maxRound,expRound);
        System.out.println("Finish 4~");
        secondResult = network.startMultiDiffusion(secondSub,startValue,maxRound,expRound);
        System.out.println("Finish 5~");
        thirdResult = network.startMultiDiffusion(thirdSub,startValue,maxRound,expRound);
        System.out.println("Finish 6~");
        temp.add(firstResult);
        temp.add(secondResult);
        temp.add(thirdResult);

        NetworkHelper.writeMultiDiffusionResultToFile(temp,"ModelSimilarityAdvance_"+name);
    }
}

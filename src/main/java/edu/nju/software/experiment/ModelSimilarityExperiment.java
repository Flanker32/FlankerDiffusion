package edu.nju.software.experiment;

import edu.nju.software.agent.Agent;
import edu.nju.software.agent.StrategyType;
import edu.nju.software.bean.MultiDiffusionResult;
import edu.nju.software.bean.NetworkParameter;
import edu.nju.software.network.Network;
import edu.nju.software.network.NetworkFactory;

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
        network.changeAgentDetermineStragy(StrategyType.DetThr,true);
        singleExperiment(startPercentage,startValue,maxRound,expRound,"DetThe");
        network.changeAgentDetermineStragy(StrategyType.ProThr,true);
        singleExperiment(startPercentage,startValue,maxRound,expRound,"ProThr");
        network.changeAgentDetermineStragy(StrategyType.DetAvg,true);
        singleExperiment(startPercentage,startValue,maxRound,expRound,"DetAvg");
        network.changeAgentDetermineStragy(StrategyType.ProAvg,true);
        singleExperiment(startPercentage,startValue,maxRound,expRound,"ProAvg");
    }

    private void singleExperiment(double startPercentage, double startValue,int maxRound,int expRound,String name){
        int point = (int)(network.getSize()*Math.random());
        List<Agent> list = network.calSimilarityPoints(point);

        int[] first = new int[AGENT_NUMBER];
        int[] second = new int[AGENT_NUMBER];
        int[] third = new int[AGENT_NUMBER];

        int firstCount = 0;
        int secondCount = 0;
        int thirdCount = 0;
        for(int i=0;i< AGENT_NUMBER*GROUP_NUMBER;i++){
            switch(i%3){
                case 0:
                    first[firstCount++]=list.get(i).getId();
                    break;
                case 1:
                    second[secondCount++]=list.get(i).getId();
                    break;
                case 2:
                    third[thirdCount++]=list.get(i).getId();
                    break;
                default:
                    break;
            }
        }

        MultiDiffusionResult[] resultList = new MultiDiffusionResult[GROUP_NUMBER];
        MultiDiffusionResult firstResult = network.startMultiDiffusion(first,startValue,maxRound,expRound);
        MultiDiffusionResult secondResult = network.startMultiDiffusion(first,startValue,maxRound,expRound);
        MultiDiffusionResult thirdResult = network.startMultiDiffusion(first,startValue,maxRound,expRound);
        resultList[0]=firstResult;
        resultList[1]=secondResult;
        resultList[2]=thirdResult;
    }
}

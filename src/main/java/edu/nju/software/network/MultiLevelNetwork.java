package edu.nju.software.network;

import edu.nju.software.agent.StrategyType;
import edu.nju.software.bean.DiffusionResult;

/**
 * Created by Dell on 2016/12/27.
 */
public interface MultiLevelNetwork {


    public boolean addNetwork(Network network,int id);

    public boolean setMultiLevelAgent(int[][] list);

    public  DiffusionResult startDiffusion(double startPercentage, double startvalue, int round);

    public void changeAgentWeight(int level,double mean,double variance);

    public void changeAgentThreshold(int level,double mean,double variance);

    public void changeAgentDetermineStragy(int level,StrategyType strategyType, boolean isBinary);
}

package edu.nju.software.network;

import edu.nju.software.Util;
import edu.nju.software.agent.Agent;
import edu.nju.software.agent.StrategyType;
import edu.nju.software.agent.determine.*;
import edu.nju.software.bean.DiffusionResult;
import edu.nju.software.bean.MultiDiffusionResult;

import java.util.*;

/**
 * Created by Dell on 2016/12/28.
 */
public interface Network {
    boolean addAgent(Agent agent) ;
    boolean addEdge(int first, int second, double weight);

    Agent getAgent(int id) ;
    int getSize();
    int getEdgeCount() ;

     DiffusionResult startDiffusion(double startPercentage, double startvalue, int round) ;
     DiffusionResult startDiffusion(int[] startAgents, double startvalue, int round);

     MultiDiffusionResult startMultiDiffusion(int[] startAgents, double startvalue, int maxDiffuseRound, int expTimes);
     MultiDiffusionResult startMultiDiffusion(double percentage, double startvalue, int maxDiffuseRound,int expTimes);
     MultiDiffusionResult startMultiDiffusion(double percentage, double startvalue, int maxDiffuseRound,int expTimes,boolean reSelect);

     List<Agent> calSimilarityPoints(int number) ;

     void changeAgentWeight(double mean,double variance);
     void changeAgentThreshold(double mean,double variance);
     void changeAgentDetermineStragy(StrategyType strategyType, boolean isBinary);
}

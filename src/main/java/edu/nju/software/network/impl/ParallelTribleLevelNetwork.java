package edu.nju.software.network.impl;

import edu.nju.software.agent.Agent;
import edu.nju.software.agent.StrategyType;
import edu.nju.software.bean.DiffusionResult;
import edu.nju.software.bean.MultiDiffusionResult;
import edu.nju.software.bean.NetworkParameter;
import edu.nju.software.agent.impl.MultiLevelAgent;
import edu.nju.software.network.MultiLevelNetwork;
import edu.nju.software.network.Network;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2016/12/29.
 */
public class ParallelTribleLevelNetwork implements MultiLevelNetwork {

    SubNetwork[] networks = new SubNetwork[3];
    List<MultiLevelAgent> agents = new ArrayList<>();

    public ParallelTribleLevelNetwork(NetworkParameter networkParameter,int multiPercentage){

    }

    @Override
    public boolean addNetwork(Network network, int id) {
        return false;
    }

    @Override
    public boolean setMultiLevelAgent(int[][] list) {
        return false;
    }

    @Override
    public boolean addAgent(Agent agent) {
        return false;
    }

    @Override
    public boolean addEdge(int first, int second, double weight) {
        return false;
    }

    @Override
    public Agent getAgent(int id) {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getEdgeCount() {
        return 0;
    }

    @Override
    public DiffusionResult startDiffusion(double startPercentage, double startvalue, int round) {
        return null;
    }

    @Override
    public DiffusionResult startDiffusion(int[] startAgents, double startvalue, int round) {
        return null;
    }

    @Override
    public MultiDiffusionResult startMultiDiffusion(int[] startAgents, double startvalue, int maxDiffuseRound, int expTimes) {
        return null;
    }

    @Override
    public MultiDiffusionResult startMultiDiffusion(double percentage, double startvalue, int maxDiffuseRound, int expTimes) {
        return null;
    }

    @Override
    public MultiDiffusionResult startMultiDiffusion(double percentage, double startvalue, int maxDiffuseRound, int expTimes, boolean reSelect) {
        return null;
    }

    @Override
    public List<Agent> calSimilarityPoints(int number) {
        return null;
    }

    @Override
    public void changeAgentWeight(double mean, double variance) {

    }

    @Override
    public void changeAgentThreshold(double mean, double variance) {

    }

    @Override
    public void changeAgentDetermineStragy(StrategyType strategyType, boolean isBinary) {

    }
}

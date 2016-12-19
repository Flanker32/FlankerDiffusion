package edu.nju.software.bean;

import edu.nju.software.agent.StrategyType;
import edu.nju.software.network.NetworkType;

/**
 * Created by Dell on 2016/12/8.
 */
public class NetworkParameter {
    private StrategyType strategyType;
    private NetworkType networkType;
    private boolean isBinary;
    private int agentNumber;
    private int edgeNumber;


    public NetworkParameter() {
    }

    public NetworkParameter(StrategyType strategyType, boolean isBinary) {
        this.strategyType = strategyType;
        this.isBinary = isBinary;
    }

    public NetworkParameter(StrategyType strategyType, boolean isBinary,NetworkType networkType, int agentNumber, int edgeNumber) {
        this.strategyType = strategyType;
        this.isBinary = isBinary;
        this.networkType=networkType;
        this.agentNumber = agentNumber;
        this.edgeNumber = edgeNumber;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    public int getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(int agentNumber) {
        this.agentNumber = agentNumber;
    }

    public int getEdgeNumber() {
        return edgeNumber;
    }

    public void setEdgeNumber(int edgeNumber) {
        this.edgeNumber = edgeNumber;
    }

    public StrategyType getStrategyType() {
        return strategyType;
    }

    public boolean isBinary() {
        return isBinary;
    }

    public void setStrategyType(StrategyType strategyType) {
        this.strategyType = strategyType;
    }

    public void setBinary(boolean binary) {
        isBinary = binary;
    }
}

package edu.nju.software.bean;

import edu.nju.software.agent.StrategyType;

/**
 * Created by Dell on 2016/12/8.
 */
public class NetworkParameter {
    private StrategyType strategyType;
    private boolean isBinary;

    public NetworkParameter() {
    }

    public NetworkParameter(StrategyType strategyType, boolean isBinary) {
        this.strategyType = strategyType;
        this.isBinary = isBinary;
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

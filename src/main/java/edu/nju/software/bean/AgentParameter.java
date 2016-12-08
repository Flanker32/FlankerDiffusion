package edu.nju.software.bean;

import edu.nju.software.agent.StrategyType;

/**
 * Created by Dell on 2016/12/8.
 */
public class AgentParameter {
    private int id;
    private double weight;
    private double threshold;
    private boolean isBinary;
    private StrategyType type;

    public AgentParameter(int id) {
        this.id = id;
        this.weight=1;
        this.threshold=1;
        this.isBinary=true;
        this.type=StrategyType.DetThr;

    }

    public AgentParameter(int id, double weight, double threshold, boolean isBinary, StrategyType type) {
        this.id = id;
        this.weight = weight;
        this.threshold = threshold;
        this.isBinary = isBinary;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public boolean isBinary() {
        return isBinary;
    }

    public void setBinary(boolean binary) {
        isBinary = binary;
    }

    public StrategyType getType() {
        return type;
    }

    public void setType(StrategyType type) {
        this.type = type;
    }
}

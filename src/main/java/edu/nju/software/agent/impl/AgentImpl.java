package edu.nju.software.agent.impl;

import edu.nju.software.Util;
import edu.nju.software.agent.Agent;
import edu.nju.software.agent.determine.AgentDetermineStragy;

import java.util.HashMap;

/**
 * Created by Dell on 2016/11/26.
 */
public class AgentImpl implements Agent {
    static final double DEFAULT_THRESHOLD_MEAN = 0.5;
    static final double DEFAULT_THRESHOLD_VARIANCE = 1;

    static final double DEFAULT_WEIGHT_MEAN = 1;
    static final double DEFAULT_WEIGHT_VARIANCE = 1;

    protected int id;
    protected boolean isActived = false;

    protected int inDegree = 0;
    protected int outDegree = 0;
    protected double weight = 0.0;
    protected double threshold = 0.0;
    protected double output = 0.0;
    protected AgentDetermineStragy agentDetermineStragy = null;
    protected HashMap<Agent, Double> frontAgent = new HashMap<Agent, Double>();
    protected HashMap<Agent, Double> afterAgent = new HashMap<Agent, Double>();

    public AgentImpl(int id, AgentDetermineStragy agentDetermineStragy) {
        this.id = id;
        this.agentDetermineStragy = agentDetermineStragy;

        this.weight = Util.generageNormalValue(DEFAULT_WEIGHT_MEAN, DEFAULT_WEIGHT_VARIANCE);
        this.threshold = Util.generageNormalValue(DEFAULT_THRESHOLD_MEAN, DEFAULT_THRESHOLD_VARIANCE);
    }

    public AgentImpl(int id, AgentDetermineStragy agentDetermineStragy, double weight, double threshold) {
        this(id, agentDetermineStragy);
        this.weight = weight;
        this.threshold = threshold;
    }

    public boolean addConnectionAsStart(Agent end, double weight) {
        afterAgent.put(end, weight);
        outDegree++;
        return true;
    }

    public boolean addConnectionAsEnd(Agent start, double weight) {
        frontAgent.put(start, weight);
        inDegree++;
        return true;
    }

    public boolean diffusePerception(double agentWeight, double edgeWeight, double output) {
        this.agentDetermineStragy.diffusePerception(agentWeight, edgeWeight, output);
        return true;
    }

    public boolean diffusionJudgement() {
        output = agentDetermineStragy.determine(this);
        if (output <= 0 || isActived) {
            return false;
        } else {
            return true;
        }
    }

    public boolean diffuse() {
        for (Agent agent : afterAgent.keySet()) {
            agent.diffusePerception(this.weight, afterAgent.get(agent), output);
        }
        this.isActived = true;
        return true;
    }

    public boolean diffuseFirstTime(double value) {
        for (Agent agent : afterAgent.keySet()) {
            agent.diffusePerception(this.weight, afterAgent.get(agent), value);
        }
        this.isActived = true;
        return true;
    }

    public void clear() {
        this.isActived = false;
        this.output = 0.0;
    }


    public int getId() {
        return id;
    }

    public boolean isActived() {
        return isActived;
    }

    public int getInDegree() {
        return inDegree;
    }

    public int getOutDegree() {
        return outDegree;
    }

    public double getWeight() {
        return weight;
    }

    public double getThreshold() {
        return threshold;
    }

    public HashMap<Agent, Double> getFrontAgent() {
        return frontAgent;
    }

    public HashMap<Agent, Double> getAfterAgent() {
        return afterAgent;
    }

    public void setAgentDetermineStragy(AgentDetermineStragy agentDetermineStragy) {
        this.agentDetermineStragy = agentDetermineStragy;
    }


    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

}

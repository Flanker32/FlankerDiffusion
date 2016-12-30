package edu.nju.software.agent.impl;

import edu.nju.software.agent.Agent;
import edu.nju.software.agent.MultiLevelNode;
import edu.nju.software.agent.determine.AgentDetermineStragy;

import java.util.HashMap;

/**
 * Created by Dell on 2016/12/28.
 */
public class MultiLevelNodeImpl extends AgentImpl implements MultiLevelNode {

    private int agentId;
    private HashMap<Agent,Double> otherLevelInput = new HashMap<>();
    private HashMap<Agent,Double> otherLevelOutput = new HashMap<>();

    public MultiLevelNodeImpl(int id, AgentDetermineStragy agentDetermineStragy) {
        super(id, agentDetermineStragy);
    }

    public MultiLevelNodeImpl(int id, AgentDetermineStragy agentDetermineStragy, double weight, double threshold) {
        super(id, agentDetermineStragy, weight, threshold);
    }

    @Override
    public void setAgentId(int agentId) {
        this.agentId=agentId;
    }

    @Override
    public int getAgentId() {
        return agentId;
    }

    @Override
    public boolean addOtherLevelInput(Agent agent,double weight) {
        otherLevelInput.put(agent,weight);
        return true;
    }

    @Override
    public boolean addOtherLevelOutput(Agent agent,double weight) {
        otherLevelOutput.put(agent,weight);
        return true;
    }

    @Override
    public boolean diffuse()
    {
        super.diffuse();
        for (Agent agent : otherLevelOutput.keySet()) {
            agent.diffusePerception(weight, otherLevelOutput.get(agent), output);
        }
        return true;
    }
}

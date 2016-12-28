package edu.nju.software.agent.impl;

import edu.nju.software.agent.Agent;
import edu.nju.software.agent.MultiLevelAgentInterface;
import edu.nju.software.agent.determine.AgentDetermineStragy;

import java.util.HashMap;

/**
 * Created by Dell on 2016/12/28.
 */
public class MultiLevelAgent extends SimpleAgent implements MultiLevelAgentInterface {
    private HashMap<Agent,Double> otherLevelInput = new HashMap<>();
    private HashMap<Agent,Double> otherLevelOutput = new HashMap<>();

    public MultiLevelAgent(int id, AgentDetermineStragy agentDetermineStragy) {
        super(id, agentDetermineStragy);
    }

    public MultiLevelAgent(int id, AgentDetermineStragy agentDetermineStragy, double weight, double threshold) {
        super(id, agentDetermineStragy, weight, threshold);
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
}

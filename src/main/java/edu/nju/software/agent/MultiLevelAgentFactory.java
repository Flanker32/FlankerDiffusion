package edu.nju.software.agent;

import edu.nju.software.agent.determine.*;
import edu.nju.software.agent.impl.MultiLevelNodeImpl;
import edu.nju.software.bean.AgentData;
import edu.nju.software.bean.AgentParameter;
import edu.nju.software.bean.NetworkParameter;

/**
 * Created by Dell on 2016/12/28.
 */
public class MultiLevelAgentFactory {

    public static MultiLevelNode newMultiLevelAgent(NetworkParameter networkParameter, AgentData agent){
        AgentParameter agentParameter = new AgentParameter(agent.getId());
        agentParameter.setBinary(networkParameter.isBinary());
        agentParameter.setThreshold(agent.getThreshold());
        agentParameter.setWeight(agent.getWeight());
        agentParameter.setType(networkParameter.getStrategyType());
        return newMultiLevelAgent(agentParameter);
    }

    public static MultiLevelNode newMultiLevelAgent(AgentParameter agentParameter){
        int id = agentParameter.getId();
        double weight = agentParameter.getWeight();
        double threshold = agentParameter.getThreshold();
        boolean isBinary = agentParameter.isBinary();
        StrategyType strategyType = agentParameter.getType();
        return newMultiLevelAgent(id,weight,threshold,isBinary,strategyType);
    }

    public static MultiLevelNode newMultiLevelAgent(int id, double weight, double threshold, boolean isBinary, StrategyType type) {
        AgentDetermineStragy stragy = null;
        switch (type) {
            case DetAvg:
                stragy = new DeterministicAverageDecisionStragy(isBinary);
                break;
            case DetThr:
                stragy = new DeterministicThresholdDecisionStragy(isBinary);
                break;
            case ProAvg:
                stragy = new ProbabilisticAverageDecisionStragy(isBinary);
                break;
            case ProThr:
                stragy = new ProbabilisticThresholdDecisionStragy(isBinary,Math.random());
                break;
            default:
                return null;
        }
        return new MultiLevelNodeImpl(id, stragy, weight, threshold);
    }
}

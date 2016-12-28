package edu.nju.software.agent;

import edu.nju.software.agent.determine.*;
import edu.nju.software.agent.impl.MultiLevelAgent;
import edu.nju.software.bean.AgentParameter;

/**
 * Created by Dell on 2016/12/28.
 */
public class MultiLevelAgentFactory {
    public static MultiLevelAgentInterface newMultiLevelAgent(AgentParameter agentParameter){
        int id = agentParameter.getId();
        double weight = agentParameter.getWeight();
        double threshold = agentParameter.getThreshold();
        boolean isBinary = agentParameter.isBinary();
        StrategyType strategyType = agentParameter.getType();
        return newMultiLevelAgent(id,weight,threshold,isBinary,strategyType);
    }

    public static MultiLevelAgentInterface newMultiLevelAgent(int id, double weight, double threshold, boolean isBinary, StrategyType type) {
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
        return new MultiLevelAgent(id, stragy, weight, threshold);
    }
}

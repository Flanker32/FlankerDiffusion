package edu.nju.software.agent;

import edu.nju.software.agent.determine.*;
import edu.nju.software.agent.impl.SimpleAgent;
import edu.nju.software.bean.AgentParameter;

/**
 * Created by Dell on 2016/12/1.
 */
public class SimpleAgentFactory {

    public static Agent newAgent(AgentParameter agentParameter){
        int id = agentParameter.getId();
        double weight = agentParameter.getWeight();
        double threshold = agentParameter.getThreshold();
        boolean isBinary = agentParameter.isBinary();
        StrategyType strategyType = agentParameter.getType();
        return newAgent(id,weight,threshold,isBinary,strategyType);
    }

    public static Agent newAgent(int id, double weight, double threshold, boolean isBinary, StrategyType type) {
        switch (type) {
            case DetAvg:
                return newDetAvgAgent(id,weight,threshold,isBinary);
            case DetThr:
                return newDetThrAgent(id,weight,threshold,isBinary);
            case ProAvg:
                return newProAvgAgent(id,weight,threshold,isBinary);
            case ProThr:
                return newProThrAgent(id,weight,threshold,isBinary);
            default:
                return null;
        }
    }

    public static Agent newDetAvgAgent(int id, double weight, double threshold, boolean isBinary) {
        return new SimpleAgent(id, new DeterministicAverageDecisionStragy(isBinary), weight, threshold);
    }

    public static Agent newDetThrAgent(int id, double weight, double threshold, boolean isBinary) {
        return new SimpleAgent(id, new DeterministicThresholdDecisionStragy(isBinary), weight, threshold);
    }

    public static Agent newProAvgAgent(int id, double weight, double threshold, boolean isBinary) {
        return new SimpleAgent(id, new ProbabilisticAverageDecisionStragy(isBinary), weight, threshold);
    }

    public static Agent newProThrAgent(int id, double weight, double threshold, boolean isBinary) {
        double probablity = Math.random();

        return new SimpleAgent(id, new ProbabilisticThresholdDecisionStragy(isBinary, probablity), weight, threshold);
    }

    public static Agent newProThrAgent(int id, double weight, double threshold, boolean isBinary, double probablity) {
        return new SimpleAgent(id, new ProbabilisticThresholdDecisionStragy(isBinary, probablity), weight, threshold);
    }
}

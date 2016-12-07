package edu.nju.software.agent;

import edu.nju.software.agent.determine.*;

/**
 * Created by Dell on 2016/12/1.
 */
public class AgentFactory {
//    private static AgentFactory agentFactory = null;
//
//    public static AgentFactory getAgentFactoryInstance() {
//        if (agentFactory == null) {
//            agentFactory = new AgentFactory();
//        }
//        return agentFactory;
//    }

    public static Agent netAgent(int id, double weight, double threshold, boolean isBinary, AgentType type) {
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
        return new Agent(id, new DeterministicAverageDecisionStragy(isBinary), weight, threshold);
    }

    public static Agent newDetThrAgent(int id, double weight, double threshold, boolean isBinary) {
        return new Agent(id, new DeterministicThresholdDecisionStragy(isBinary), weight, threshold);
    }

    public static Agent newProAvgAgent(int id, double weight, double threshold, boolean isBinary) {
        return new Agent(id, new ProbabilisticAverageDecisionStragy(isBinary), weight, threshold);
    }

    public static Agent newProThrAgent(int id, double weight, double threshold, boolean isBinary) {
        double probablity = Math.random();

        return new Agent(id, new ProbabilisticThresholdDecisionStragy(isBinary, probablity), weight, threshold);
    }

    public static Agent newProThrAgent(int id, double weight, double threshold, boolean isBinary, double probablity) {
        return new Agent(id, new ProbabilisticThresholdDecisionStragy(isBinary, probablity), weight, threshold);
    }
}

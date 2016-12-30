package edu.nju.software.agent;

/**
 * Created by Dell on 2016/12/28.
 */
public interface MultiLevelNode extends Agent {
    void setAgentId(int agentId);

    int getAgentId();

    boolean addOtherLevelInput(Agent agent, double weight);

    boolean addOtherLevelOutput(Agent agent, double weight);
}

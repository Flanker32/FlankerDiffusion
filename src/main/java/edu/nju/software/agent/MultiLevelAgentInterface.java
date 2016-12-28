package edu.nju.software.agent;

import edu.nju.software.agent.Agent;

/**
 * Created by Dell on 2016/12/28.
 */
public interface MultiLevelAgentInterface extends Agent {
    public boolean addOtherLevelInput(Agent agent,double weight);
    public boolean addOtherLevelOutput(Agent agent,double weight);
}

package edu.nju.software.network;

import edu.nju.software.agent.Agent;

import java.util.List;

/**
 * Created by Dell on 2016/12/28.
 */
public interface SubNetwork extends  Network {
    boolean addOtherLevelInput(int agentId,Agent agent,int weight);
    boolean addOtherLevelOutput(int agentId,Agent agent,int weight);

    void diffuseNewRound();
    List<Agent> getActivedNodes();
}

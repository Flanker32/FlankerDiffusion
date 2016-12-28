package edu.nju.software.network;

import edu.nju.software.agent.Agent;

/**
 * Created by Dell on 2016/12/28.
 */
public interface SubNetworkInterface extends  Network {
    boolean addOtherLevelInput(int agentId,Agent agent);
    boolean addOtherLevelOutput(int agentId,Agent agent);
}

package edu.nju.software.network.impl;

import edu.nju.software.agent.Agent;
import edu.nju.software.agent.MultiLevelAgentInterface;
import edu.nju.software.agent.impl.MultiLevelAgent;
import edu.nju.software.network.SubNetworkInterface;

import java.util.HashMap;

/**
 * Created by Dell on 2016/12/28.
 */
public class SubNetwork extends SimpleNetwork implements SubNetworkInterface {
    public SubNetwork(){
        super();
        agents = new HashMap<Integer,MultiLevelAgentInterface>();
    }

    @Override
    public boolean addOtherLevelInput(int agentId, Agent agent) {
        return false;
    }

    @Override
    public boolean addOtherLevelOutput(int agentId, Agent agent) {
        return false;
    }
}

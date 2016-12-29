package edu.nju.software.network.impl;

import edu.nju.software.agent.Agent;
import edu.nju.software.agent.MultiLevelAgentInterface;
import edu.nju.software.network.SubNetworkInterface;

/**
 * Created by Dell on 2016/12/28.
 */
public class SubNetwork extends SimpleNetwork implements SubNetworkInterface {
    public SubNetwork(){
        super();
    }


    @Override
    public boolean addOtherLevelInput(int agentId, Agent agent,int weight) {
        MultiLevelAgentInterface  multiLevelAgentInterface = (MultiLevelAgentInterface) agents.get(agentId);
        multiLevelAgentInterface.addOtherLevelInput(agent,weight);
        return false;
    }

    @Override
    public boolean addOtherLevelOutput(int agentId, Agent agent,int weight)
    {
        MultiLevelAgentInterface  multiLevelAgentInterface = (MultiLevelAgentInterface) agents.get(agentId);
        multiLevelAgentInterface.addOtherLevelOutput(agent,weight);
        return false;
    }
}

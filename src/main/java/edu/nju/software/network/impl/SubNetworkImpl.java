package edu.nju.software.network.impl;

import edu.nju.software.agent.Agent;
import edu.nju.software.agent.MultiLevelNode;
import edu.nju.software.network.SubNetwork;

import java.util.List;

/**
 * Created by Dell on 2016/12/28.
 */
public class SubNetworkImpl extends NetworkImpl implements SubNetwork {
    private List<Agent> activedAgents;


    @Override
    public boolean addOtherLevelInput(int agentId, Agent agent,int weight) {
        MultiLevelNode multiLevelNode = (MultiLevelNode) agents.get(agentId);
        multiLevelNode.addOtherLevelInput(agent,weight);
        return false;
    }

    @Override
    public boolean addOtherLevelOutput(int agentId, Agent agent,int weight)
    {
        MultiLevelNode multiLevelNode = (MultiLevelNode) agents.get(agentId);
        multiLevelNode.addOtherLevelOutput(agent,weight);
        return false;
    }

    public void diffuseForTheFirstTime(int[] startAgents,double startvalue){
        super.startFirstRoundDiffusion(startAgents,startvalue);
    }

    public void diffuseNewRound(){
        super.diffusionNewRound(activedAgents);
    }

    public List<Agent> getActivedNodes(){
        this.activedAgents = super.getActivedAgents();
//        System.out.println("新扩散：新激活节点" + activedAgents.size());
//        for(Agent agent:activedAgents){
//            System.out.println("编号为："+agent.getId()+"的agent本轮后被激活");
//        }
        return activedAgents;
    }

    public void showDiffusionStatus(){

    }
}

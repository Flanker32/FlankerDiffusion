package edu.nju.software.network.impl;

import edu.nju.software.agent.Agent;
import edu.nju.software.agent.MultiLevelNode;
import edu.nju.software.agent.impl.MultiLevelAgent;
import edu.nju.software.bean.NetworkParameter;
import edu.nju.software.network.MultiLevelNetwork;

import java.util.HashMap;

/**
 * Created by Dell on 2016/12/29.
 */
public class ParallelTribleLevelNetwork extends MultiLevelNetwork{

    public ParallelTribleLevelNetwork(NetworkParameter[] networkParameter,double multiPercentage){
        super(networkParameter,multiPercentage);

        addCrossLink();
        System.out.println("Network size: "+agents.size());
//        debug();
    }

    private void addCrossLink(){
        for(MultiLevelAgent agent:agents){
            if(!agent.isCrossLevelAgent()){
                continue;
            }else{
                for(MultiLevelNode first:agent.getNodes().keySet()){
                    for(MultiLevelNode second:agent.getNodes().keySet()){
                        if(first==second){
                            continue;
                        }else{
                            linkNodesOfSameAgent(first,second);
                        }
                    }
                }
            }
        }
    }

    private void linkNodesOfSameAgent(MultiLevelNode first,MultiLevelNode second){
        HashMap<Agent, Double> firstFronts = first.getFrontAgent();
        HashMap<Agent, Double> secondFronts = first.getFrontAgent();
        for(Agent agent:firstFronts.keySet()){
            MultiLevelNode mln = (MultiLevelNode)agent;
            mln.addOtherLevelOutput(second,firstFronts.get(agent));
        }
        for(Agent agent:secondFronts.keySet()){
            MultiLevelNode mln = (MultiLevelNode)agent;
            mln.addOtherLevelOutput(first,secondFronts.get(agent));
        }
    }
}

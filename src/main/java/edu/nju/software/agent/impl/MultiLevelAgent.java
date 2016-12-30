package edu.nju.software.agent.impl;

import edu.nju.software.agent.MultiLevelNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dell on 2016/12/29.
 */
public class MultiLevelAgent {

    private int agentId;
    private boolean isCrossLevelAgent = false;
    private boolean isActived = false;
    private HashMap<MultiLevelNode,Integer> nodes = new HashMap<>();

    public MultiLevelAgent(int agentId) {
        this.agentId = agentId;
    }

    public MultiLevelAgent(int agentId, boolean isCrossLevelAgent, HashMap<MultiLevelNode,Integer> nodes) {
        this.agentId = agentId;
        this.isCrossLevelAgent = isCrossLevelAgent;
        this.nodes = nodes;
    }

    public boolean isActived() {
        return isActived;
    }

    public void setActived(boolean actived) {
        isActived = actived;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public boolean isCrossLevelAgent() {
        return isCrossLevelAgent;
    }

    public void setCrossLevelAgent(boolean crossLevelAgent) {
        isCrossLevelAgent = crossLevelAgent;
    }

    public HashMap<MultiLevelNode,Integer> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<MultiLevelNode,Integer> nodes) {
        this.nodes = nodes;
    }

    public void addNode(MultiLevelNode node,int networkId){
        this.nodes.put(node,networkId);
    }

    public int getNodeCount(){
        return this.nodes.size();
    }
}

package edu.nju.software.agent.impl;

import edu.nju.software.agent.impl.MultiLevelNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2016/12/29.
 */
public class MultiLevelAgent {

    private int agentId;
    private boolean isCrossLevelAgent = false;
    private List<MultiLevelNode> nodes = new ArrayList<>();

    public MultiLevelAgent(int agentId) {
        this.agentId = agentId;
    }

    public MultiLevelAgent(int agentId, boolean isCrossLevelAgent, List<MultiLevelNode> nodes) {
        this.agentId = agentId;
        this.isCrossLevelAgent = isCrossLevelAgent;
        this.nodes = nodes;
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

    public List<MultiLevelNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<MultiLevelNode> nodes) {
        this.nodes = nodes;
    }

    public void addNode(MultiLevelNode node){
        this.nodes.add(node);
    }

    public int getNodeCount(){
        return this.nodes.size();
    }
}

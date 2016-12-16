package edu.nju.software.bean;

import java.util.List;

/**
 * Created by Dell on 2016/12/12.
 */
public class NetworkData {
    List<AgentData> agents;
    List<EdgeData> edges;

    public NetworkData() {
    }

    public NetworkData(List<AgentData> agents, List<EdgeData> edges) {
        this.agents = agents;
        this.edges = edges;
    }

    public void addAgent(AgentData agent) {
        this.agents.add(agent);
    }

    public void addEdge(EdgeData edgeData) {
        this.edges.add(edgeData);
    }

    public List<AgentData> getAgents() {
        return agents;
    }

    public void setAgents(List<AgentData> agents) {
        this.agents = agents;
    }

    public List<EdgeData> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeData> edges) {
        this.edges = edges;
    }
}

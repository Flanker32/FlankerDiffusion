package edu.nju.software.network.impl;

import edu.nju.software.agent.Agent;
import edu.nju.software.agent.MultiLevelAgentFactory;
import edu.nju.software.agent.MultiLevelNode;
import edu.nju.software.agent.impl.MultiLevelAgent;
import edu.nju.software.bean.AgentData;
import edu.nju.software.bean.NetworkParameter;
import edu.nju.software.network.MultiLevelNetwork;
import edu.nju.software.network.Network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Dell on 2016/12/31.
 */
public class NormalizationTribleLevelNetwork extends MultiLevelNetwork {
    private NetworkParameter networkParameter;

    public NormalizationTribleLevelNetwork(NetworkParameter[] networkParameter, double multiPercentage) {
        super(networkParameter, multiPercentage);
        System.out.println("Network size: "+agents.size());
        this.networkParameter=networkParameter[0];
    }

    public Network generageSingleLevelNetwork(){
        Network network = new NetworkImpl();
        for(int i=0;i<agents.size();i++){
            MultiLevelAgent mla=agents.get(i);
            AgentData agentData = getAgentDate(mla.getNodes().keySet(),mla.getAgentId());
            network.addAgent(MultiLevelAgentFactory.newMultiLevelAgent(networkParameter,agentData));
        }

        HashMap<Integer,List<Double>> multiEdges = new HashMap<>();
        for(MultiLevelAgent mlafirst:agents){
            for(MultiLevelNode first:mlafirst.getNodes().keySet()){
                for(Agent sec:first.getAfterAgent().keySet()){
                    MultiLevelNode second = (MultiLevelNode)sec;
                    int edgeId = first.getAgentId()*agents.size()+second.getAgentId();
                    double weight = first.getAfterAgent().get(sec);
                    if(multiEdges.containsKey(edgeId)){
                        multiEdges.get(edgeId).add(weight);
                    }else{
                        List<Double> temple = new ArrayList<>();
                        temple.add(weight);
                        multiEdges.put(edgeId,temple);
                        network.addEdge(first.getAgentId(),second.getAgentId(),weight);
                    }
                }
            }
        }
        processMultiEdges(multiEdges,network);
        return network;
    }

    public AgentData getAgentDate(Set<MultiLevelNode> agents, int agentId){
        AgentData result = new AgentData();
        double weight = 0;
        double threshold = 0;
        for(Agent agent:agents){
            weight+=agent.getWeight();
            threshold+=agent.getThreshold();
        }
        weight = weight/agents.size();
        threshold = threshold/agents.size();
        result.setThreshold(threshold);
        result.setWeight(weight);
        result.setId(agentId);
        return result;
    }

    public void processMultiEdges(HashMap<Integer,List<Double>> edges,Network network){
        for(Integer edgeId: edges.keySet()){
            List<Double> weights = edges.get(edgeId);
            if(weights.size()<=1){
                continue;
            }else{
//                int edgeId = first.getAgentId()*agents.size()+second.getAgentId();
                int first = edgeId/agents.size();
                int second = edgeId%agents.size();
                double weight = getWeight(weights);
                network.addEdge(first,second,weight);
            }
        }
    }

    public double getWeight(List<Double> weights){
        double result = 0;
        for(double d:weights){
            if(d>result){
                result=d;
            }
        }
        return result;
    }
}

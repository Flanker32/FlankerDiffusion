package edu.nju.software.network;

import edu.nju.software.agent.Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dell on 2016/11/26.
 */
public class Network {

    private int diffusionRound = 0;
    HashMap<Integer,Agent> agents = new HashMap<Integer, Agent>();
    List<Agent> activedAgents = new ArrayList<Agent>();

    public boolean addAgent(Agent agent){
        agents.put(agent.getId(),agent);
        return true;
    }

    public boolean addEdge(int first,int second ,int weight){
        Agent start = agents.get(first);
        Agent end = agents.get(second);

        if(start==null||end==null){
            return false;
        }
        start.addConnectionAsStart(end,weight);
        end.addConnectionAsEnd(start,weight);
        return true;
    }

    public void startDiffusion(int[] startAgents,double startvalue,int round)
    {
        // 第一轮传播
        for(int agentId:startAgents){
            agents.get(agentId).diffuseFirstTime(startvalue);
        }

        for(int i=0;i<round;i++)
        {
            boolean check = checkAllPoints();
            if(!check){
                break;
            }
            diffusionNewRound();
            showNetworkStatus();
        }
    }

    protected boolean checkAllPoints(){
        activedAgents.clear();
        for(Agent agent:agents.values())
        {
            boolean result = agent.diffusionJudgement();
            if(result){
                activedAgents.add(agent);
            }
        }
        if(activedAgents.size()==0){
            return false;
        }else{
            return true;
        }
    }

    protected void diffusionNewRound(){
        diffusionRound++;
        for(Agent agent:activedAgents){
            agent.diffuse();
        }
    }

    protected void showNetworkStatus(){
        System.out.println("第"+diffusionRound+"轮开始：");
        for(Agent agent:this.activedAgents){
            System.out.println("编号为："+agent.getId()+"的agent本轮后被激活");
        }
    }


//    public boolean addEdge(Agent first,Agent second ,int weight){
//        return true;
//    }

//    public void startDiffusion(List<Agent> startAgents){
//
//    }
}

package edu.nju.software.network;

import edu.nju.software.agent.Agent;

import java.util.*;

/**
 * Created by Dell on 2016/11/26.
 */
public class Network {

    private int diffusionRound = 0;
    private int activedAgentNumber = 0;
    HashMap<Integer, Agent> agents = new HashMap<Integer, Agent>();
    List<Agent> activedAgents = new ArrayList<Agent>();
    boolean[] finalActivedAgents;

    public boolean addAgent(Agent agent) {
        agents.put(agent.getId(), agent);
        return true;
    }

    public boolean addEdge(int first, int second, int weight) {
        Agent start = agents.get(first);
        Agent end = agents.get(second);

        if (start == null || end == null) {
            return false;
        }
        start.addConnectionAsStart(end, weight);
        end.addConnectionAsEnd(start, weight);
        return true;
    }

    public void startDiffusion(int[] startAgents, double startvalue, int round) {
        // 第一轮传播
        finalActivedAgents = new boolean[agents.size()];
        for (int agentId : startAgents) {
            agents.get(agentId).diffuseFirstTime(startvalue);
        }

        for (int i = 0; i < round; i++) {
            boolean check = checkAllPoints();
            if (!check) {
                break;
            }
            diffusionNewRound();
            showNetworkStatus();
        }
        clear();
    }

    protected boolean checkAllPoints() {
        activedAgents.clear();
        for (Agent agent : agents.values()) {
            boolean result = agent.diffusionJudgement();
            if (result) {
                activedAgents.add(agent);
                activedAgentNumber++;
            }
        }
        if (activedAgents.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    protected void diffusionNewRound() {
        diffusionRound++;
        for (Agent agent : activedAgents) {
            agent.diffuse();
        }
    }

    protected void showNetworkStatus() {
        //System.out.println("第"+diffusionRound+"轮扩散：新激活节点"+activedAgents.size());
//        for(Agent agent:this.activedAgents){
//            System.out.println("编号为："+agent.getId()+"的agent本轮后被激活");
//        }
    }

    protected void clear() {

        int count = 0;
        for (Agent agent : agents.values()) {
            finalActivedAgents[count++] = agent.isActived();
        }

        System.out.println("扩散结束，扩散轮数" + diffusionRound + "扩散节点数" + activedAgentNumber + "/" + agents.size());

        this.diffusionRound = 0;
        this.activedAgentNumber = 0;
        this.activedAgents.clear();
        for(Agent agent:agents.values()){
            agent.clear();
        }
    }

    public int getSize(){
        return agents.size();
    }

    public boolean[] getAgentStatus() {
        return finalActivedAgents;
    }

    public List<Agent> calSimilarityPoints(int number) {
        LinkedList<Agent> result = new LinkedList<Agent>();
        Agent sample = agents.get(number);
        result.sort(new AgentSimilarityComparator(sample));
        return result;
    }

    class AgentSimilarityComparator implements Comparator {

        Agent sample;

        public AgentSimilarityComparator(Agent sample) {
            this.sample = sample;
        }

        public int compare(Object o1, Object o2) {
            Agent first = (Agent) o1;
            Agent second = (Agent) o2;
            double result = calDistance(first,sample)-calDistance(second,sample);
            if(result>0){
                return -1;
            }else{
                return 1;
            }
        }

        public double calDistance(Agent first, Agent second) {
            double result = 0;
            result = Math.pow(first.getInDegree() - second.getInDegree(), 2) + Math.pow(first.getOutDegree() - second.getOutDegree(), 2);
            return result;
        }
    }


//    public boolean addEdge(Agent first,Agent second ,int weight){
//        return true;
//    }

//    public void startDiffusion(List<Agent> startAgents){
//
//    }
}

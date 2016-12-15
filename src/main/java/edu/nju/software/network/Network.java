package edu.nju.software.network;

import edu.nju.software.agent.Agent;
import edu.nju.software.agent.StrategyType;
import edu.nju.software.agent.determine.*;
import edu.nju.software.bean.DiffusionResult;

import java.util.*;

import static edu.nju.software.agent.StrategyType.*;

/**
 * Created by Dell on 2016/11/26.
 */
public class Network {

    private int edgeCount = 0;
    private int diffusionRound = 0;
    private int activedAgentNumber = 0;
    HashMap<Integer, Agent> agents = new HashMap<Integer, Agent>();
    boolean[] finalActivedAgents;

    public boolean addAgent(Agent agent) {
        agents.put(agent.getId(), agent);
        return true;
    }

    public boolean addEdge(int first, int second, double weight) {
        Agent start = agents.get(first);
        Agent end = agents.get(second);

        if (start == null || end == null) {
            return false;
        }
        start.addConnectionAsStart(end, weight);
        end.addConnectionAsEnd(start, weight);
        this.edgeCount++;
        return true;
    }

    public DiffusionResult startDiffusion(int[] startAgents, double startvalue, int round) {
        // 第一轮传播
        int agentCount = getSize();
        DiffusionResult diffusionResult = new DiffusionResult(agentCount, edgeCount, round);
        diffusionResult.setDiffusionRound(round);// set diffusion round max at first.

        finalActivedAgents = new boolean[agents.size()];
        for (int agentId : startAgents) {
            Agent agent = agents.get(agentId);
            if (agent != null) {
                agent.diffuseFirstTime(startvalue);
            }
        }

        for (int i = 1; i <= round; i++) {
            List<Agent> activedAgents = getActivedAgents();
            if (activedAgents.size() == 0) {
                diffusionResult.setDiffusionRound(i - 1);
                diffusionResult.setAffectedAgentCount(activedAgentNumber);
                break;
            } else {
                diffusionResult.setDiffusePerTerm(i - 1, activedAgents.size());
                diffusionNewRound(activedAgents);
                showNetworkStatus(activedAgents);
            }
        }
        // record every agents' status
        boolean[] finalActivedAgents = new boolean[agentCount];
        int count = 0;
        for (Agent agent : agents.values()) {
            finalActivedAgents[count++] = agent.isActived();
        }
        diffusionResult.setAgentStatus(finalActivedAgents);

        clear();
        return diffusionResult;
    }

    protected List<Agent> getActivedAgents() {
        List<Agent> result = new ArrayList<Agent>();
        for (Agent agent : agents.values()) {
            boolean temp = agent.diffusionJudgement();
            if (temp) {
                result.add(agent);
                activedAgentNumber++;
            }
        }
        return result;
    }

    protected void diffusionNewRound(List<Agent> agents) {
        diffusionRound++;
        for (Agent agent : agents) {
            agent.diffuse();
        }
    }

    protected void showNetworkStatus(List<Agent> activedAgents) {
//        System.out.println("第" + diffusionRound + "轮扩散：新激活节点" + activedAgents.size());
//        for(Agent agent:this.activedAgents){
//            System.out.println("编号为："+agent.getId()+"的agent本轮后被激活");
//        }
    }

    protected void clear() {
        System.out.println("扩散结束，扩散轮数" + diffusionRound + "扩散节点数" + activedAgentNumber + "/" + agents.size());
        this.diffusionRound = 0;
        this.activedAgentNumber = 0;
        for (Agent agent : agents.values()) {
            agent.clear();
        }
    }

    public Agent getAgent(int id){
        return agents.get(id);
    }

    public int getSize() {
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
            double result = calDistance(first, sample) - calDistance(second, sample);
            if (result > 0) {
                return -1;
            } else {
                return 1;
            }
        }

        public double calDistance(Agent first, Agent second) {
            double result = 0;
            result = Math.pow(first.getInDegree() - second.getInDegree(), 2) + Math.pow(first.getOutDegree() - second.getOutDegree(), 2);
            return result;
        }
    }

    public void changeAgentDetermineStragy(StrategyType strategyType, boolean isBinary) {
        AgentDetermineStragy stragy = null;
        for (Agent agent : agents.values()) {
            switch (strategyType) {
                case DetAvg:
                    stragy = new DeterministicAverageDecisionStragy(isBinary);
                    break;
                case DetThr:
                    stragy = new DeterministicThresholdDecisionStragy(isBinary);
                    break;
                case ProAvg:
                    stragy = new ProbabilisticAverageDecisionStragy(isBinary);
                    break;
                case ProThr:
                    stragy = new ProbabilisticThresholdDecisionStragy(isBinary, Math.random());
                    break;
                default:
                    break;
            }
            agent.setAgentDetermineStragy(stragy);
        }
    }

//    public boolean addEdge(Agent first,Agent second ,int weight){
//        return true;
//    }

//    public void startDiffusion(List<Agent> startAgents){
//
//    }
    }

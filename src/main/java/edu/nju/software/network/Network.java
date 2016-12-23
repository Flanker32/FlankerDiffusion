package edu.nju.software.network;

import edu.nju.software.Util;
import edu.nju.software.agent.Agent;
import edu.nju.software.agent.StrategyType;
import edu.nju.software.agent.determine.*;
import edu.nju.software.bean.DiffusionResult;
import edu.nju.software.bean.MultiDiffusionResult;

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

    //指定起始点百分比，初始值，传播轮数进行扩散
    public DiffusionResult startDiffusion(double startPercentage, double startvalue, int round) {
        int[] startList = this.getStartAgents(getSize(),startPercentage);
        return startDiffusion(startList,startvalue,round);
    }

    //指定初始节点，初始值，传播轮数进行扩散
    public DiffusionResult startDiffusion(int[] startAgents, double startvalue, int round) {
        // 第一轮传播
        int agentCount = getSize();
        DiffusionResult diffusionResult = new DiffusionResult(agentCount, edgeCount, round);
        diffusionResult.setDiffusionRound(round);// set diffusion round max at first.

        for (int agentId : startAgents) {
            Agent agent = agents.get(agentId);
            if (agent != null) {
                agent.diffuseFirstTime(startvalue);
                activedAgentNumber++;
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

    public MultiDiffusionResult startMultiDiffusion(int[] startAgents, double startvalue, int maxDiffuseRound,int expTimes){
        List<DiffusionResult> results = new ArrayList<>();
        for(int i=0;i<expTimes;i++){
            results.add(this.startDiffusion(startAgents,startvalue,maxDiffuseRound));
        }
        return analyzeMultiDiffusionResult(results,maxDiffuseRound,expTimes);
    }

    public MultiDiffusionResult startMultiDiffusion(double percentage, double startvalue, int maxDiffuseRound,int expTimes){
        return startMultiDiffusion(percentage,startvalue,maxDiffuseRound,expTimes,true);
    }

    public MultiDiffusionResult startMultiDiffusion(double percentage, double startvalue, int maxDiffuseRound,int expTimes,boolean reSelect){
        List<DiffusionResult> results = new ArrayList<>();
        int[] startList = this.getStartAgents(this.getSize(),percentage);
        for(int i=0;i<expTimes;i++){
            if(reSelect){
                results.add(this.startDiffusion(percentage,startvalue,maxDiffuseRound));
            }else{
                results.add(this.startDiffusion(startList,startvalue,maxDiffuseRound));
            }
        }
        return analyzeMultiDiffusionResult(results,maxDiffuseRound,expTimes);
    }

    public MultiDiffusionResult analyzeMultiDiffusionResult(List<DiffusionResult> results ,int maxDiffuseRound,int expTimes){
        MultiDiffusionResult result = new MultiDiffusionResult(expTimes);
        result.setAgentCount(getSize());
        result.setEdgeCount(edgeCount);

        int actualMaxRound = 0;
        double averageAffectedAgentCount = 0;
        double[] averageDiffusePerTerm = new double[maxDiffuseRound];
        int[] agentStatus = new int[getSize()];
        for(DiffusionResult diffusionResult:results){
            if(diffusionResult.getDiffusionRound()>actualMaxRound){
                actualMaxRound = diffusionResult.getDiffusionRound();
            }
            averageAffectedAgentCount+=diffusionResult.getAffectedAgentCount();
            for(int m=0;m<diffusionResult.getDiffusePerTerm().length;m++){
                averageDiffusePerTerm[m]+=diffusionResult.getDiffusePerTerm()[m];
            }
            for(int m=0;m<getSize();m++){
                if(diffusionResult.getAgentStatus()[m]){
                    agentStatus[m]++;
                }
            }
        }
        averageAffectedAgentCount = averageAffectedAgentCount/expTimes;
        for(int i=0;i<averageDiffusePerTerm.length;i++){
            averageDiffusePerTerm[i]=averageDiffusePerTerm[i]/expTimes;
        }

        result.setAgentStatus(agentStatus);
        result.setAverageAffectedAgentCount(averageAffectedAgentCount);
        result.setAverageDiffusePerTerm(averageDiffusePerTerm);
        result.setMaxDiffusionRound(maxDiffuseRound);
        return result;
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
//        for(Agent agent:activedAgents){
//            System.out.println("编号为："+agent.getId()+"的agent本轮后被激活");
//        }
    }

    protected void clear() {
//        System.out.println("扩散结束，扩散轮数" + diffusionRound + "扩散节点数" + activedAgentNumber + "/" + agents.size());
        this.diffusionRound = 0;
        this.activedAgentNumber = 0;
        for (Agent agent : agents.values()) {
            agent.clear();
        }
    }

    //获取特定agent的引用
    public Agent getAgent(int id) {
        return agents.get(id);
    }

    //获取网络规模（Agent数量）
    public int getSize() {
        return agents.size();
    }

    //计算所有Agent与指定id Agent的相似程度，并按照顺序返回
    public List<Agent> calSimilarityPoints(int number) {
        LinkedList<Agent> result = new LinkedList<Agent>();
        Agent sample = agents.get(number);
        result.sort(new AgentSimilarityComparator(sample));
        return result;
    }

    //相似度比较器类
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

    //按照正态分布重新分配权重
    public void changeAgentWeight(double mean,double variance){
        for(Agent agent:agents.values()){
            agent.setWeight(Util.generagePositiveNormalValue(mean,variance));
        }
    }

    //按照正态分布重新分配阈值
    public void changeAgentThreshold(double mean,double variance){
        for(Agent agent:agents.values()){
            agent.setThreshold(Util.generagePositiveNormalValue(mean,variance));
        }
    }

    //修改所有节点的决策方式
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

    //生成随机初始节点
    private  int[] getStartAgents(int agentNumber, double startPercentage) {
        int number = (int) Math.floor(agentNumber * startPercentage);
        int[] result = new int[number];
        HashSet<Integer> set = new HashSet<Integer>();
        while (set.size() < number) {
            int randomNumber = (int) Math.floor(agentNumber * Math.random());
            set.add(randomNumber);
        }
        int count = 0;
        for (Integer integer : set) {
            result[count++] = integer;
        }

        return result;
    }
}

package edu.nju.software;

import edu.nju.software.determine.AgentDetermineStragy;

import java.util.HashMap;

/**
 * Created by Dell on 2016/11/26.
 */
public class Agent {
    static final double  DEFAULT_THRESHOLD_MEAN = 0.5;
    static final double DEFAULT_THRESHOLD_VARIANCE = 1;

    static final double  DEFAULT_WEIGHT_MEAN = 1;
    static final double DEFAULT_WEIGHT_VARIANCE = 1;

    private int id;
    private boolean isActived=false;

    private int inDegree = 0;
    private int outDegree = 0;
    private double weight = 0.0;
    private double threshold = 0.0;
    private double probablity = 0.0;
    private double output = 0.0;
    private AgentDetermineStragy agentDetermineStragy = null;
    private HashMap<Agent,Double> frontAgent = new HashMap<Agent, Double>();
    private HashMap<Agent,Double> afterAgent = new HashMap<Agent, Double>();

    public Agent(int id, AgentDetermineStragy agentDetermineStragy){
        this.id=id;
        this.agentDetermineStragy = agentDetermineStragy;

        this.probablity = Util.generagePositiveNormalValue(0.5,1);
        this.weight = Util.generageNormalValue(DEFAULT_WEIGHT_MEAN, DEFAULT_WEIGHT_VARIANCE);
        this.threshold = Util.generageNormalValue(DEFAULT_THRESHOLD_MEAN, DEFAULT_THRESHOLD_VARIANCE);
    }

    public Agent(int id,AgentDetermineStragy agentDetermineStragy,double weight,double threshold){
        this(id,agentDetermineStragy);
        this.weight=weight;
        this.threshold=threshold;
    }

    public boolean addConnectionAsStart(Agent end,double weight){
        afterAgent.put(end,weight);
        outDegree++;
        return true;
    }

    public boolean addConnectionAsEnd(Agent start,double weight){
        frontAgent.put(start,weight);
        inDegree++;
        return true;
    }

    public boolean diffusePerception(double agentWeight,double edgeWeight,double output){
        this.agentDetermineStragy.diffusePerception(agentWeight,edgeWeight,output);
        return true;
    }

    public boolean diffusionJudgement()
    {
        output = agentDetermineStragy.determine(this);
        if(output<=0||isActived){
            return false;
        }else{
            return true;
        }
    }

    public boolean diffuse()
    {
        for(Agent agent:afterAgent.keySet()){
            agent.diffusePerception(this.weight,afterAgent.get(agent),output);
        }
        this.isActived=true;
        return true;
    }

    public boolean diffuseFirstTime(double value)
    {
        for(Agent agent:afterAgent.keySet()){
            agent.diffusePerception(this.weight,afterAgent.get(agent),value);
        }
        this.isActived=true;
        return true;
    }


    public int getId() {
        return id;
    }

    public boolean isActived() {
        return isActived;
    }

    public int getInDegree() {
        return inDegree;
    }

    public int getOutDegree() {
        return outDegree;
    }

    public double getWeight() {
        return weight;
    }

    public double getThreshold() {
        return threshold;
    }

    public HashMap<Agent, Double> getFrontAgent() {
        return frontAgent;
    }

    public double getProbablity() {
        return probablity;
    }
}

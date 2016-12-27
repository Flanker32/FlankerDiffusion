package edu.nju.software.agent;

import edu.nju.software.agent.determine.AgentDetermineStragy;

import java.util.HashMap;

/**
 * Created by Dell on 2016/12/27.
 */
public interface Agent {
    public boolean addConnectionAsStart(Agent end, double weight) ;

    public boolean addConnectionAsEnd(Agent start, double weight);

    public boolean diffusePerception(double agentWeight, double edgeWeight, double output);

    public boolean diffusionJudgement();

    public boolean diffuse();

    public boolean diffuseFirstTime(double value);

    public void clear() ;

    public int getId();

    public boolean isActived();

    public int getInDegree();

    public int getOutDegree();

    public double getWeight();

    public double getThreshold();

    public HashMap<Agent, Double> getFrontAgent();

    public HashMap<Agent, Double> getAfterAgent();

    public void setAgentDetermineStragy(AgentDetermineStragy agentDetermineStragy) ;

    public void setWeight(double weight);

    public void setThreshold(double threshold);
}

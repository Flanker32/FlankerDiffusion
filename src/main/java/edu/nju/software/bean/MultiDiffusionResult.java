package edu.nju.software.bean;

/**
 * Created by Dell on 2016/12/20.
 */
public class MultiDiffusionResult {
    int terms;
    int agentCount = 0;
    int edgeCount = 0;
    int maxDiffusionRound = 0;
    double averageAffectedAgentCount = 0;
    double[] averageDiffusePerTerm;
    int[] agentStatus;

    public MultiDiffusionResult(int terms) {
        this.terms = terms;
    }

    public int getTerms() {
        return terms;
    }

    public void setTerms(int terms) {
        this.terms = terms;
    }

    public int getAgentCount() {
        return agentCount;
    }

    public void setAgentCount(int agentCount) {
        this.agentCount = agentCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void setEdgeCount(int edgeCount) {
        this.edgeCount = edgeCount;
    }

    public int getMaxDiffusionRound() {
        return maxDiffusionRound;
    }

    public void setMaxDiffusionRound(int maxDiffusionRound) {
        this.maxDiffusionRound = maxDiffusionRound;
    }

    public double getAverageAffectedAgentCount() {
        return averageAffectedAgentCount;
    }

    public void setAverageAffectedAgentCount(double averageAffectedAgentCount) {
        this.averageAffectedAgentCount = averageAffectedAgentCount;
    }

    public double[] getAverageDiffusePerTerm() {
        return averageDiffusePerTerm;
    }

    public double getAverageDiffusePerTerm(int term) {
        return averageDiffusePerTerm[term];
    }

    public void setAverageDiffusePerTerm(double value,int term){
        averageDiffusePerTerm[term]=value;
    }

    public void setAverageDiffusePerTerm(double[] averageDiffusePerTerm) {
        this.averageDiffusePerTerm = averageDiffusePerTerm;
    }

    public int[] getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(int[] agentStatus) {
        this.agentStatus = agentStatus;
    }
}

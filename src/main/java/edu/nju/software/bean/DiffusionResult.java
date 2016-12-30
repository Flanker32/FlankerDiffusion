package edu.nju.software.bean;

/**
 * Created by Dell on 2016/12/10.
 */
public class DiffusionResult {
    int agentCount = 0;
    int edgeCount = 0;
    int maxDiffusionRound = 0;

    int diffusionRound = 0;
    int affectedAgentCount = 0;
    int[] diffusePerTerm;
    boolean[] agentStatus;

    public DiffusionResult(int agentCount, int edgeCount,int maxDiffusionRound) {
        this.agentCount = agentCount;
        this.edgeCount = edgeCount;
        this.maxDiffusionRound=maxDiffusionRound;
        this.agentStatus = new boolean[agentCount];
        this.diffusePerTerm = new int[maxDiffusionRound];
    }

    public int getDiffusionRound() {
        return diffusionRound;
    }

    public void setDiffusionRound(int diffusionRound) {
        this.diffusionRound = diffusionRound;
    }

    public int getAffectedAgentCount() {
        return affectedAgentCount;
    }

    public void setAffectedAgentCount(int affectedAgentCount) {
        this.affectedAgentCount = affectedAgentCount;
    }

    public int[] getDiffusePerTerm() {
        return diffusePerTerm;
    }

    public void setDiffusePerTerm(int[] diffusePerTerm) {
        this.diffusePerTerm = diffusePerTerm;
    }

    public void setDiffusePerTerm(int term,int value){
        this.diffusePerTerm[term]=value;
    }

    public boolean[] getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(boolean[] agentStatus) {
        this.agentStatus = agentStatus;
    }

    public void debug(){
        System.out.println("Diffusion Result: ");
        System.out.println("Diffusion Round: "+this.diffusionRound);
        System.out.println("Affected Agent : "+this.affectedAgentCount);
    }
}

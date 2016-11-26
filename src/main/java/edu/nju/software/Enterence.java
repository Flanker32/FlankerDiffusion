package edu.nju.software;

import edu.nju.software.determine.DeterministicThresholdDecisionStragy;

/**
 * Created by Dell on 2016/11/26.
 */
public class Enterence {
    public static void main(String[] args){
        Network network = new Network();
        Agent first = new Agent(1,new DeterministicThresholdDecisionStragy(true),1,1);
        Agent second = new Agent(2,new DeterministicThresholdDecisionStragy(true),1,0.5);
        Agent third = new Agent(3,new DeterministicThresholdDecisionStragy(true),1,3);
        network.addAgent(first);
        network.addAgent(second);
        network.addAgent(third);
        network.addEdge(1,2,1);
        network.addEdge(1,3,1);
        network.addEdge(2,3,10);

        int[] list = new int[1];
        list[0] = 1;
        network.startDiffusion(list,1,3);
    }
}
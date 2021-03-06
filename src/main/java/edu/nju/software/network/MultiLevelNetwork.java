package edu.nju.software.network;

import edu.nju.software.agent.Agent;
import edu.nju.software.agent.MultiLevelNode;
import edu.nju.software.agent.impl.MultiLevelAgent;
import edu.nju.software.agent.impl.MultiLevelNodeImpl;
import edu.nju.software.bean.DiffusionResult;
import edu.nju.software.bean.MultiDiffusionResult;
import edu.nju.software.bean.NetworkParameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Dell on 2016/12/30.
 */
public abstract class MultiLevelNetwork {
    protected static final int NETWORK_NUMBER = 3;

    protected int nodePerLevel = 0;
    protected SubNetwork[] networks = new SubNetwork[NETWORK_NUMBER];
    protected List<MultiLevelAgent> agents = new ArrayList<>();
    private int edgeCount = 0;

    public MultiLevelNetwork(NetworkParameter[] networkParameter, double multiPercentage) {
        if (networkParameter.length < NETWORK_NUMBER) {
            throw new RuntimeException("Parameters not engough");
        }

        nodePerLevel = networkParameter[0].getAgentNumber();
        for (int i = 0; i < NETWORK_NUMBER; i++) {
            networks[i] = NetworkFactory.generateSubNetwork(networkParameter[i]);
            edgeCount +=networks[i].getEdgeCount();
        }

        generateCrossLevelAgents(multiPercentage);
    }

    protected int generateCrossLevelAgents(double multiPercentage) {
        int agentCount = 0;
        for (int i = 1; i <= nodePerLevel; i++) {
            if (Math.random() < multiPercentage) {
                if (Math.random() < 0.1) {
                    MultiLevelAgent mla = new MultiLevelAgent(agentCount++);
                    for (int j = 0; j < NETWORK_NUMBER; j++) {
                        MultiLevelNode node = ((MultiLevelNodeImpl) networks[j].getAgent(i));
                        node.setAgentId(mla.getAgentId());
                        mla.addNode(node, j);
                    }
                    mla.setCrossLevelAgent(true);
                    agents.add(mla);
                } else {
                    MultiLevelAgent single = new MultiLevelAgent(agentCount++);
                    single.setCrossLevelAgent(false);
                    MultiLevelAgent cross = new MultiLevelAgent(agentCount++);
                    cross.setCrossLevelAgent(true);

                    boolean[] temple = new boolean[NETWORK_NUMBER];
                    temple[(int) (Math.random() * NETWORK_NUMBER)] = true;
                    for (int j = 0; j < NETWORK_NUMBER; j++) {
                        if (temple[j]) {
                            MultiLevelNode node = ((MultiLevelNodeImpl) networks[j].getAgent(i));
                            node.setAgentId(single.getAgentId());
                            single.addNode(node, j);
                        } else {
                            MultiLevelNode node = ((MultiLevelNodeImpl) networks[j].getAgent(i));
                            node.setAgentId(cross.getAgentId());
                            cross.addNode(node, j);
                        }
                    }
                    agents.add(single);
                    agents.add(cross);
                }
            } else {
                for (int j = 0; j < NETWORK_NUMBER; j++) {
                    MultiLevelAgent mla = new MultiLevelAgent(agentCount++);
                    MultiLevelNode node = ((MultiLevelNodeImpl) networks[j].getAgent(i));
                    node.setAgentId(mla.getAgentId());
                    mla.addNode(node, j);
                    mla.setCrossLevelAgent(false);
                    agents.add(mla);
                }
            }
        }
        return agentCount;
    }

    public DiffusionResult startDiffusion(double startPercentage, double startvalue, int maxRound) {
        DiffusionResult result = new DiffusionResult(agents.size(), edgeCount, maxRound);
        result.setDiffusionRound(maxRound);
        // diffuse for the first time
        int[] startAgents = NetworkHelper.getStartAgents(agents.size(), startPercentage);
        for (int startNumber : startAgents) {
            Set<MultiLevelNode> nodes = agents.get(startNumber).getNodes().keySet();
            for (MultiLevelNode node : nodes) {
                node.diffuseFirstTime(startvalue);
            }
        }
        // diffusing
        int totalActivedNumber = 0;
        for (int i = 0; i < maxRound; i++) {
            int newActivedNumber = 0;
            List<Agent> activedAgent = new ArrayList<>();
            for (SubNetwork network : networks) {
                activedAgent.addAll(network.getActivedNodes());
            }
            if (activedAgent.size() == 0) {
                result.setDiffusionRound(i);
                result.setAffectedAgentCount(totalActivedNumber);
                break;
            } else {
                for (Agent agent : activedAgent) {
                    MultiLevelNode node = (MultiLevelNode) agent;
                    int agentId = node.getAgentId();
                    MultiLevelAgent multiLevelAgent = agents.get(agentId);
                    if (!multiLevelAgent.isActived()) {
                        multiLevelAgent.setActived(true);
                        newActivedNumber++;
                    }
                }
                result.setDiffusePerTerm(i, newActivedNumber);
                totalActivedNumber += newActivedNumber;
                for (SubNetwork network : networks) {
                    network.diffuseNewRound();
                }
            }
        }
        clear();
        return result;
    }

    public MultiDiffusionResult startMultiDiffusion(double percentage, double startvalue, int maxRoundPerExp, int expTimes) {
        List<DiffusionResult> results = new ArrayList<>();
        for (int i = 0; i < expTimes; i++) {
            results.add(this.startDiffusion(percentage, startvalue, maxRoundPerExp));
        }
        return NetworkHelper.analyzeMultiDiffusionResult(results, maxRoundPerExp, expTimes);
    }

    protected void clear() {
        for (SubNetwork network : networks) {
            network.clear();
        }
    }

    public void debug() {
        int count = 0;
        for (SubNetwork subNetwork : networks) {
            System.out.println("subNetwork " + count++);
            subNetwork.debug();
        }
        for (MultiLevelAgent multiLevelAgent : agents) {
            if (multiLevelAgent.isCrossLevelAgent()) {
                System.out.print("CROSS FIELD ");
                for (MultiLevelNode mln : multiLevelAgent.getNodes().keySet()) {
                    System.out.print(mln.getAgentId() + " at level " + multiLevelAgent.getNodes().get(mln));
                }
                System.out.println();
            }
        }
    }
}

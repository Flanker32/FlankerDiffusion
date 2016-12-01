package edu.nju.software.network;

import edu.nju.software.IOHelper;
import edu.nju.software.agent.Agent;
import edu.nju.software.agent.AgentFactory;
import edu.nju.software.agent.AgentType;
import edu.nju.software.network.Network;

import java.util.List;

/**
 * Created by Dell on 2016/12/1.
 */
public class NetworkFactory {


    public static Network readNetworkFromFile(String nodeFile, String edgeFile, boolean isBinary, AgentType type) {
        Network network = new Network();

        List<String> agent = IOHelper.readFileByLine(nodeFile);
        List<String> edges = IOHelper.readFileByLine(edgeFile);

        if (agent == null || edges == null) {
            return null;
        }

        for (String s : agent) {
            String[] temple = s.split(",");
            int id = Integer.valueOf(temple[0]);
            int weight = Integer.valueOf(temple[1]);
            int threshold = Integer.valueOf(temple[2]);
            Agent temp = AgentFactory.netAgent(id, weight, threshold, isBinary, type);
            network.addAgent(temp);
        }
        for (String s : edges) {
            String[] temple = s.split(",");
            int start = Integer.valueOf(temple[0]);
            int end = Integer.valueOf(temple[1]);
            int weight = Integer.valueOf(temple[2]);
            network.addEdge(start, end, weight);
        }

        return network;
    }


}

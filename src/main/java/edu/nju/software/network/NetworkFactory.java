package edu.nju.software.network;

import edu.nju.software.Constant;
import edu.nju.software.agent.MultiLevelAgentFactory;
import edu.nju.software.agent.SimpleAgentFactory;
import edu.nju.software.agent.StrategyType;
import edu.nju.software.bean.*;
import edu.nju.software.network.datagenerator.NetworkDataFactory;
import edu.nju.software.network.impl.SimpleNetwork;
import edu.nju.software.network.impl.SubNetwork;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2016/12/1.
 */
public class NetworkFactory {

    public static SubNetworkInterface generateSubNetwork(NetworkParameter networkParameter) {
        NetworkData data = NetworkDataFactory.generate(networkParameter);
        SubNetwork network = new SubNetwork();
        List<AgentData> agentDatas = data.getAgents();
        List<EdgeData> edgeDatas = data.getEdges();
        for (AgentData agent : agentDatas) {
            network.addAgent(MultiLevelAgentFactory.newMultiLevelAgent(networkParameter,agent));
        }

        for (EdgeData edge : edgeDatas) {
            network.addEdge(edge.getStart(), edge.getEnd(), edge.getWeight());
        }
        return network;
    }

    public static SubNetworkInterface generateTrueSubNetwork(NetworkParameter networkParameter,int maxNumber){
        String nodeFile = Constant.trueNode;
        String edgeFile = Constant.trueEdge;

        SubNetwork network = new SubNetwork();
        NetworkData data = readDataFromFile(nodeFile, edgeFile, maxNumber);
        List<AgentData> agentDatas = data.getAgents();
        List<EdgeData> edgeDatas = data.getEdges();
        for (AgentData agent : agentDatas) {
            network.addAgent(MultiLevelAgentFactory.newMultiLevelAgent(networkParameter,agent));
        }
        for (EdgeData edge : edgeDatas) {
            network.addEdge(edge.getStart(), edge.getEnd(), edge.getWeight());
        }
        return network;
    }

    public static Network generateNetwork(NetworkParameter networkParameter) {
        NetworkData data = NetworkDataFactory.generate(networkParameter);
        Network network = new SimpleNetwork();
        List<AgentData> agentDatas = data.getAgents();
        List<EdgeData> edgeDatas = data.getEdges();
        for (AgentData agent : agentDatas) {
            network.addAgent(SimpleAgentFactory.newAgent(networkParameter, agent));
        }

        for (EdgeData edge : edgeDatas) {
            network.addEdge(edge.getStart(), edge.getEnd(), edge.getWeight());
        }
        return network;
    }


    public static Network readNetworkFromFile(String nodeFile, String edgeFile, NetworkParameter networkParameter) {
        return readBigNetworkFromFile(nodeFile, edgeFile, networkParameter, Integer.MAX_VALUE);
    }

    public static Network readNetworkFromFile(String nodeFile, String edgeFile, boolean isBinary, StrategyType type) {
        NetworkParameter networkParameter = new NetworkParameter();
        networkParameter.setBinary(isBinary);
        networkParameter.setStrategyType(type);
        return readNetworkFromFile(nodeFile, edgeFile, networkParameter);
    }

    public static Network readBigNetworkFromFile(String nodeFile, String edgeFile, NetworkParameter networkParameter, int maxNumber) {
        Network network = new SimpleNetwork();
        NetworkData data = readDataFromFile(nodeFile, edgeFile, maxNumber);
        List<AgentData> agentDatas = data.getAgents();
        List<EdgeData> edgeDatas = data.getEdges();
        for (AgentData agent : agentDatas) {
            network.addAgent(SimpleAgentFactory.newAgent(networkParameter, agent));
        }

        for (EdgeData edge : edgeDatas) {
            network.addEdge(edge.getStart(), edge.getEnd(), edge.getWeight());
        }

        return network;
    }

    private static NetworkData readDataFromFile(String nodeFile, String edgeFile, int maxNumber) {
        List<EdgeData> edges = new ArrayList<>();
        List<AgentData> agents = new ArrayList<>();

        File file = new File(nodeFile);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            int count = 0;
            while (br.ready() && count < maxNumber) {
                String input = br.readLine();
                String[] temple = input.split(",");
                int id = Integer.valueOf(temple[0]);
                int weight = Integer.valueOf(temple[1]);
                int threshold = Integer.valueOf(temple[2]);

                if (id > maxNumber) {
                    continue;
                } else {
                    AgentData agentData = new AgentData(id, threshold, weight);
                    agents.add(agentData);
                    count++;
                }
            }
            br.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try {
            file = new File(edgeFile);
            fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            while (br.ready()) {
                String input = br.readLine();
                String[] temple = input.split(",");
                int first = Integer.valueOf(temple[0]);
                int second = Integer.valueOf(temple[1]);
                int weight = Integer.valueOf(temple[2]);
                if ((first < maxNumber) && (second < maxNumber)) {
                    EdgeData edgeData = new EdgeData(first, second, weight);
                    edges.add(edgeData);
                }
            }
            br.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new NetworkData(agents, edges);
    }

    public static Network readSouthEaseNetworkFromFile(int number, int maxThreshold, String file, NetworkParameter networkParameter) {
        Network network = new SimpleNetwork();
        for (int i = 0; i < number; i++) {
            AgentParameter agentParameter = new AgentParameter(i);
            agentParameter.setType(networkParameter.getStrategyType());
            agentParameter.setWeight(5);
            agentParameter.setThreshold(20);
            agentParameter.setBinary(networkParameter.isBinary());
            network.addAgent(SimpleAgentFactory.newAgent(agentParameter));
        }

        int lineCode = 0;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(file));
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            while (br.ready() && lineCode < number) {
                String input = br.readLine();
                input.trim();
                if (input.length() == 0) {
                    lineCode++;
                    continue;
                }
                String[] connectPoints = input.split(" ");
                for (String s : connectPoints) {
                    network.addEdge(lineCode,Integer.valueOf(s),Math.random()*10+1);
//                    network.addEdge(lineCode, Integer.valueOf(s), 5);
                }
                if(connectPoints.length>0){
                    network.getAgent(lineCode).setWeight(connectPoints.length);
                }
                lineCode++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return network;
    }

//    public static Network readNetworkFromFile(String nodeFile, String edgeFile, boolean isBinary, StrategyType type) {
//        Network network = new SimpleNetwork();
//
//        List<String> agent = IOHelper.readFileByLine(nodeFile);
//        List<String> edges = IOHelper.readFileByLine(edgeFile);
//
//        if (agent == null || edges == null) {
//            return null;
//        }
//
//        for (String s : agent) {
//            String[] temple = s.split(",");
//            int id = Integer.valueOf(temple[0]);
//            int weight = Integer.valueOf(temple[1]);
//            int threshold = Integer.valueOf(temple[2]);
////            int weight = Integer.valueOf(5);
////            int threshold = Integer.valueOf(30);
//            Agent temp = SimpleAgentFactory.newAgent(id, weight, threshold, isBinary, type);
//            network.addAgent(temp);
//        }
//        for (String s : edges) {
//            String[] temple = s.split(",");
//            int start = Integer.valueOf(temple[0]);
//            int end = Integer.valueOf(temple[1]);
//            int weight = Integer.valueOf(temple[2]);
////            int weight = Integer.valueOf(5);
//            network.addEdge(start, end, weight);
//        }
//
//        return network;
//    }

}

package edu.nju.software.network.datagenerator;

import edu.nju.software.Util;
import edu.nju.software.bean.AgentData;
import edu.nju.software.bean.EdgeData;
import edu.nju.software.bean.NetworkData;
import edu.nju.software.bean.NetworkParameter;
import edu.nju.software.network.NetworkType;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created by Flanker on 2016/12/13.
 */
public class NetworkDataFactory {

    private static final int LEVEL_ONE = 1;
    private static final int LEVEL_TWO = 2;
    private static final int LEVEL_THREE = 3;
    private static final int LEVEL_FOUR = 4;
    private static final int LEVEL_FIVE = 5;
    private static final double  probability = 0.5;
    private static Random ra = new Random();

    public static NetworkData generate(NetworkParameter networkParameter){
        NetworkType type = networkParameter.getNetworkType();
        Graph networkGraph = null;
        switch(type){
            case Random:
                ErdosRenyiRandomGenerator random = new ErdosRenyiRandomGenerator(networkParameter);
                networkGraph=random.generate();
                break;
            case Regular:
                RegularNetworkGenerator regular = new RegularNetworkGenerator(networkParameter);
                networkGraph=regular.generate();
                break;
            case ScaleFree:
                BarabasiAlbertNetGenerator scaleFree = new BarabasiAlbertNetGenerator(networkParameter);
                networkGraph = scaleFree.generate();
                break;
            case SmallWorld:
                SmallWorldGenerator smallWorld = new SmallWorldGenerator(networkParameter);
                networkGraph = smallWorld.generate();
                break;
            default:
                return null;
        }
        List<EdgeData> edges = generateEdges(networkGraph);
        List<AgentData> agents = generateAgents(networkGraph);

        return new NetworkData(agents,edges);
    }

    static double createVertexThreshold(){
        return Util.generagePositiveNormalValue(0.5, 1);
    }

    //还没写好…………
    static double[] createVertexWeight(int count,double weight[] ,double weightPer[]){
        double[] result = new double[count];

        if(weight.length!=weightPer.length){
            return null;
        }
        double[] weightTemp = new double[weight.length];
        double total = 0;
        for(double d:weightPer){
            total+=d;
        }
        double temp = 0;
        for(int i=0;i<weightTemp.length;i++){

        }

        for(int i=0;i<count;i++){
            double probablity = Math.random();

        }

        return result;
    }

    static void createVertexWeight(int vertexCount,double [] weightArray){

        for(int i = 1; i <= vertexCount*(0.05);i++){
            int randomLOne = ra.nextInt(vertexCount)+1;
            while(weightArray[randomLOne-1] != 0){
                randomLOne = ra.nextInt(vertexCount)+1;
            }
            weightArray[randomLOne-1] = LEVEL_ONE;

            int randomLFive = ra.nextInt(vertexCount)+1;
            while(weightArray[randomLFive-1] != 0){
                randomLFive = ra.nextInt(vertexCount)+1;
            }
            weightArray[randomLFive-1] = LEVEL_FIVE;
        }
        for(int j = 1; j <= vertexCount*(0.15);j++){
            int randomLTwo = ra.nextInt(vertexCount)+1;
            while(weightArray[randomLTwo-1] != 0){
                randomLTwo = ra.nextInt(vertexCount)+1;
            }
            weightArray[randomLTwo-1] = LEVEL_TWO;

            int randomLFour = ra.nextInt(vertexCount)+1;
            while(weightArray[randomLFour-1] != 0){
                randomLFour = ra.nextInt(vertexCount)+1;
            }
            weightArray[randomLFour-1] = LEVEL_FOUR;
        }
        for(int k =1; k <= vertexCount*(0.6);k++){
            int randomLThree = ra.nextInt(vertexCount)+1;
            while(weightArray[randomLThree-1] != 0){
                randomLThree = ra.nextInt(vertexCount)+1;
            }
            weightArray[randomLThree-1] = LEVEL_THREE;
        }
    }

    static List<AgentData> generateAgents(Graph network) {
        Collection<Integer> vertices = network.getVertices();
        List<AgentData> agentDatas = new ArrayList<AgentData>();
        double[] weightArray = new double[network.getVertexCount()];

        createVertexWeight(network.getVertexCount(), weightArray);
        for (Integer v : vertices) {
            double threshold = createVertexThreshold(); //the threshold of vertex
            double weight = weightArray[v.intValue() - 1];
            AgentData agent = new AgentData();
            agent.setId(v.intValue());
            agent.setThreshold(threshold);
            agent.setWeight(weight);
            agentDatas.add(agent);
        }
        return agentDatas;
    }

    static List<EdgeData> generateEdges(Graph network) {
        //handle edge
        Collection<Integer> edges = network.getEdges();
        List<EdgeData> edgeList = new ArrayList<EdgeData>();

        for (Integer e : edges) {
            Pair edgeEndpoints = network.getEndpoints(e);
            Object firstElement = edgeEndpoints.getFirst();
            Object secondElement = edgeEndpoints.getSecond();

            double random = Math.random();
            double edgeWeight=Math.random()*(10-1)+1; //the weight of edge
            EdgeData edge = null;
            if(random<probability){
                edge= new EdgeData();
                edge.setStart(Integer.parseInt(firstElement.toString()));
                edge.setEnd(Integer.parseInt(secondElement.toString()));
                edge.setWeight(edgeWeight);
            }else{
                edge = new EdgeData();
                edge.setStart(Integer.parseInt(firstElement.toString()));
                edge.setEnd(Integer.parseInt(secondElement.toString()));
                edge.setWeight(edgeWeight);
            }
            edgeList.add(edge);
        }
        return edgeList;
    }
}

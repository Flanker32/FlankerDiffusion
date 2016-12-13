package edu.nju.software.network.datagenerator;

import edu.nju.software.Util;
import edu.nju.software.bean.AgentData;
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

    static final int LEVEL_ONE = 1;
    static final int LEVEL_TWO = 2;
    static final int LEVEL_THREE = 3;
    static final int LEVEL_FOUR = 4;
    static final int LEVEL_FIVE = 5;

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

        return null;
    }


    private static Random ra = new Random();

    static double createVertexThreshold(){
        return Util.generagePositiveNormalValue(0.5, 1);
    }

    static double[] createVertexWeight(int count,double weight[] ,double weightPer[]){
        double[] result = new double[count];

        //
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

    static List<AgentData> handle(Graph network, String netLable) {
        //handle vertex
        Collection<Integer> vertices = network.getVertices();
        List<AgentData> agentDatas = new ArrayList<AgentData>();
        double[] weightArray = new double[network.getVertexCount()];
        createVertexWeight(network.getVertexCount(), weightArray);
        for (Integer v : vertices) {
            //System.out.println(v); // print the vertex
            double threshold = createVertexThreshold(); //the threshold of vertex
            double weight = weightArray[v.intValue() - 1];
            //conduct a new Vertex object
            AgentData agent = new AgentData();
            agent.setId(v.intValue());
            agent.setThreshold(threshold);
            agent.setWeight(weight);
            agentDatas.add(agent);
        }
        return agentDatas;
    }


    {
        File xmlFileVertex = new File(netLable+"_vertices.xml");
        try{
            FileOutputStream ofs = new FileOutputStream(xmlFileVertex);
            XmlSerialize<Vertex> xmlSerialize = new XmlSerialize<Vertex>();
            xmlSerialize.serializeMultipleObject(ofs,vertexList);
        }catch(FileNotFoundException e)  {
            e.printStackTrace();
        }catch(IOException e)  {
            e.printStackTrace();
        }
        //handle edge
        Collection<Integer> edges = network.getEdges();
        List<Edge> edgeList = new ArrayList<Edge>();
        //System.out.println("NumEdges:"+network.getEdgeCount());
        //System.out.println("NumVertices:"+network.getVertexCount());
        for (Integer e : edges) {
            Pair edgeEndpoints = network.getEndpoints(e);
            //System.out.println(edgeEndpoints);
            Object firstElement = edgeEndpoints.getFirst();
            Object secondElement = edgeEndpoints.getSecond();
            //System.out.println(firstElement.toString()+"��������"+secondElement);
            //System.out.println(Integer.parseInt(firstElement.toString())+"/"+Integer.parseInt(secondElement.toString()));
            Random ra = new Random();
            double  probability = 0.5;
            double random = ra.nextDouble();
            double edgeWeight;
            edgeWeight = ra.nextDouble()*(10-1)+1; //the weight of edge
            Edge edge;
            if(random<probability){
                edge = new Edge();
                edge.setBeginNode(Integer.parseInt(firstElement.toString()));
                edge.setEndNode(Integer.parseInt(secondElement.toString()));
                edge.setWeight(edgeWeight);
            }else{
                edge = new Edge();
                edge.setEndNode(Integer.parseInt(firstElement.toString()));
                edge.setBeginNode(Integer.parseInt(secondElement.toString()));
                edge.setWeight(edgeWeight);
            }
            edgeList.add(edge);
        }
        File xmlFileEdge = new File(netLable+"_edges.xml");
        try{
            FileOutputStream ofs = new FileOutputStream(xmlFileEdge);
            XmlSerialize<Edge> xmlSerialize = new XmlSerialize<Edge>();
            xmlSerialize.serializeMultipleObject(ofs,edgeList);
        }catch(FileNotFoundException e)  {
            e.printStackTrace();
        }catch(IOException e)  {
            e.printStackTrace();
        }
    }
}

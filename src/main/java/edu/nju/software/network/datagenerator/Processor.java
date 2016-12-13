package edu.nju.software.network.datagenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import edu.nju.software.Util;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;

public class Processor {
	static final int LEVEL_ONE = 1;
	static final int LEVEL_TWO = 2;
	static final int LEVEL_THREE = 3;
	static final int LEVEL_FOUR = 4;
	static final int LEVEL_FIVE = 5;
	
	private static Random ra = new Random();
	
	static double createVertexThreshold(){
		return Util.generagePositiveNormalValue(0.5, 1);
		//return ra.nextGaussian();
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

	static void handle(Graph network, String netLable){
		//handle vertex
		Collection<Integer> vertices = network.getVertices();
		List<Vertex> vertexList = new ArrayList<Vertex>();
		double [] weightArray = new double[network.getVertexCount()];
		createVertexWeight(network.getVertexCount(),weightArray);
		//System.out.println(netLable); //print the name of network
		for (Integer v : vertices) {
			//System.out.println(v); // print the vertex
			double threshold = createVertexThreshold(); //the threshold of vertex
			double weight = weightArray[v.intValue()-1];
			//conduct a new Vertex object
			Vertex vertex = new Vertex();
			vertex.setLable(v.intValue());
			vertex.setWeight(weight);
			vertex.setThreshold(threshold);
			vertexList.add(vertex);
		}
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

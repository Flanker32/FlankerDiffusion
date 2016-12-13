package edu.nju.software.network.datagenerator;

import edu.nju.software.bean.NetworkParameter;
import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.algorithms.generators.random.KleinbergSmallWorldGenerator;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;

public class SmallWorldGenerator {
	
	private int vertexCount;
	static int vi = 1;
	static int ei = 1;

	public SmallWorldGenerator(int vertexCount){
		this.vertexCount = vertexCount;
	}

	public SmallWorldGenerator(NetworkParameter networkParameter){
		int count = networkParameter.getAgentNumber();
		if(count%2!=0){
			count++;
		}
		this.vertexCount=count;
	}
	
	public Graph generate(){
		Factory<SparseGraph<Integer, Integer>> graphFactory;
		graphFactory = new Factory<SparseGraph<Integer, Integer>>() {
			public SparseGraph<Integer, Integer> create() {
				return new SparseGraph<Integer, Integer>();
			}
		};
		Factory<Integer> vertexFactory;
		vertexFactory = new Factory<Integer>() {
			public Integer create() {
				return new Integer(vi++); 
			}
		};
		Factory<Integer> edgeFactory;
		edgeFactory = new Factory<Integer>() {
			public Integer create() {
				return new Integer(ei++); 
			}
		};
		KleinbergSmallWorldGenerator smallWorld = new KleinbergSmallWorldGenerator(graphFactory,vertexFactory,edgeFactory,2,vertexCount/2,3);		
		Graph network = smallWorld.create();
		clear();
		return network;
//		Processor.handle(network,"SmallWorld");
		
	}

	private static void clear(){
		vi=1;
		ei=0;
	}
}

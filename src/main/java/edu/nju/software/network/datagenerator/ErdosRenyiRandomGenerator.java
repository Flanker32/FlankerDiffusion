package edu.nju.software.network.datagenerator;

import edu.nju.software.bean.NetworkParameter;
import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.algorithms.generators.random.ErdosRenyiGenerator;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class ErdosRenyiRandomGenerator {
	private int vertexCount;
	private double probability;
	static int vi = 1;
	static int ei = 1;

	public ErdosRenyiRandomGenerator(NetworkParameter networkParameter){
		this.vertexCount = networkParameter.getAgentNumber();
		this.probability = networkParameter.getEdgeNumber()/Math.pow(networkParameter.getAgentNumber(),2);
	}

	public ErdosRenyiRandomGenerator(int vertexCount, double probability) {
		this.vertexCount = vertexCount;
		this.probability = probability;
	}
	public Graph generate(){
		Factory<UndirectedSparseGraph<Integer, Integer>> graphFactory;
		graphFactory = new Factory<UndirectedSparseGraph<Integer, Integer>>() {
			public UndirectedSparseGraph<Integer, Integer> create() {
				return new UndirectedSparseGraph<Integer, Integer>();
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
		ErdosRenyiGenerator erRandom = new ErdosRenyiGenerator(graphFactory,vertexFactory,edgeFactory,vertexCount,probability);
		
		Graph network = erRandom.create();
		clear();
		return network;
//		Processor.handle(network,"ErdosRenyiRandom");
		
	}

	private static void clear(){
		vi=1;
		ei=0;
	}
}

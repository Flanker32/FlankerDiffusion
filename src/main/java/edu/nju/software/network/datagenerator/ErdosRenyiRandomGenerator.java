package edu.nju.software.network.datagenerator;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.algorithms.generators.random.ErdosRenyiGenerator;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class ErdosRenyiRandomGenerator {
	private int vertexCount;
	private double probability;
	static int vi = 1;
	static int ei = 1;
	public ErdosRenyiRandomGenerator(int vertexCount, double probability) {
		this.vertexCount = vertexCount;
		this.probability = probability;
	}
	public void generate(){
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
		
		Processor.handle(network,"ErdosRenyiRandom");
		
	}
}

package edu.nju.software.network.datagenerator;

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
	
	public void generate(){
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
		
		Processor.handle(network,"SmallWorld");
		
	}
}

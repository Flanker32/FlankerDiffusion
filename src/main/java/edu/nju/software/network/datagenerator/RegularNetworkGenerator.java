package edu.nju.software.network.datagenerator;

import edu.nju.software.bean.NetworkParameter;
import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.algorithms.generators.Lattice2DGenerator;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;

public class RegularNetworkGenerator {
	private int vertexCount;
	static int vi = 1;
	static int ei = 1;
	public RegularNetworkGenerator(int vertexCount) {
		this.vertexCount = vertexCount;
	}

	public RegularNetworkGenerator(NetworkParameter networkParameter){
		this.vertexCount = networkParameter.getAgentNumber();
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
		Lattice2DGenerator rn = new Lattice2DGenerator(graphFactory,vertexFactory,edgeFactory,2,vertexCount/2,false);
		Graph network = rn.create();
		return network;
//		Processor.handle(network,"RegularNetwork");
		
	}
}

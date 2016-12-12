package edu.nju.software.network.datagenerator;

public class Edge {
	private int beginNode;
	private int endNode;
	private double weight;
	public Edge(){
		
	}
	public int getBeginNode() {
		return beginNode;
	}
	public void setBeginNode(int beginNode) {
		this.beginNode = beginNode;
	}
	public int getEndNode() {
		return endNode;
	}
	public void setEndNode(int endNode) {
		this.endNode = endNode;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}

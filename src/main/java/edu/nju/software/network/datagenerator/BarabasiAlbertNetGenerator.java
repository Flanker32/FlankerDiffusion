package edu.nju.software.network.datagenerator;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.algorithms.generators.random.BarabasiAlbertGenerator;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;

public class BarabasiAlbertNetGenerator {
    private int vertexCount;
    private int init_vertices;
    private int numEdgesToAttach;
    static int vi = 1;
    static int ei = 1;

    public BarabasiAlbertNetGenerator(int vertexCount, int init_vertices, int numEdgesToAttach) {
        this.vertexCount = vertexCount;
        this.init_vertices = init_vertices;
        this.numEdgesToAttach = numEdgesToAttach;
    }

    public void generate() {
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
        Set<Integer> seedVertices = new HashSet<Integer>();
        BarabasiAlbertGenerator ba = new BarabasiAlbertGenerator(graphFactory, vertexFactory, edgeFactory, init_vertices, numEdgesToAttach, seedVertices);
        ba.evolveGraph(vertexCount - init_vertices);
        Graph network = ba.create();
        //System.out.println(seedVertices);
        Processor.handle(network, "BarabasiAlbertNet");
    }

}

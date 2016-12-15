package edu.nju.software;

import edu.nju.software.agent.StrategyType;
import edu.nju.software.bean.NetworkParameter;
import edu.nju.software.experiment.DiffusionSpeedExperiment;
import edu.nju.software.network.Network;
import edu.nju.software.network.NetworkFactory;
import edu.nju.software.network.NetworkType;

import java.util.HashSet;

/**
 * Created by Dell on 2016/11/26.
 */
public class Enterence {
//    public static void main(String[] args){
//        Network scale_free0 = NetworkFactory.readNetworkFromFile("small_world_nodes.txt","small_world_edges.txt",false, StrategyType.ProThr);
////        Network small_world3 = NetworkFactory.readNetworkFromFile("small_world_nodes.txt","small_world_edges.txt",false, StrategyType.ProAvg);
//        ExpFirstNetwork exp = new ExpFirstNetwork(scale_free0);
//        int startValue = 10;
//        int diffusionRound = 100;
//        int[] startPoints = {0, 1};
//        exp.similarityTest(startValue,diffusionRound,startPoints,100);
////        small_world3.startDiffusion(startPoints,diffusionRound,startValue);
//    }

//    public static void main(String[] args){
//        Network scale_free0 = NetworkFactory.readNetworkFromFile("scale_free_nodes.txt","scale_free_edges.txt",true, StrategyType.DetThr);
//        Network scale_free1 = NetworkFactory.readNetworkFromFile("scale_free_nodes.txt","scale_free_edges.txt",true, StrategyType.DetAvg);
//        Network scale_free2 = NetworkFactory.readNetworkFromFile("scale_free_nodes.txt","scale_free_edges.txt",true, StrategyType.ProThr);
//        Network scale_free3 = NetworkFactory.readNetworkFromFile("scale_free_nodes.txt","scale_free_edges.txt",true, StrategyType.ProAvg);
//
//        Network random0 = NetworkFactory.readNetworkFromFile("random_nodes.txt","random_edges.txt",true, StrategyType.DetThr);
//        Network random1 = NetworkFactory.readNetworkFromFile("random_nodes.txt","random_edges.txt",true, StrategyType.DetAvg);
//        Network random2 = NetworkFactory.readNetworkFromFile("random_nodes.txt","random_edges.txt",true, StrategyType.ProThr);
//        Network random3 = NetworkFactory.readNetworkFromFile("random_nodes.txt","random_edges.txt",true, StrategyType.ProAvg);
//
//        Network regular0 = NetworkFactory.readNetworkFromFile("regular_nodes.txt","regular_edges.txt",true, StrategyType.DetThr);
//        Network regular1 = NetworkFactory.readNetworkFromFile("regular_nodes.txt","regular_edges.txt",true, StrategyType.DetAvg);
//        Network regular2 = NetworkFactory.readNetworkFromFile("regular_nodes.txt","regular_edges.txt",true, StrategyType.ProThr);
//        Network regular3 = NetworkFactory.readNetworkFromFile("regular_nodes.txt","regular_edges.txt",true, StrategyType.ProAvg);
//
//        Network small_world0 = NetworkFactory.readNetworkFromFile("small_world_nodes.txt","small_world_edges.txt",true, StrategyType.DetThr);
//        Network small_world1 = NetworkFactory.readNetworkFromFile("small_world_nodes.txt","small_world_edges.txt",true, StrategyType.DetAvg);
//        Network small_world2 = NetworkFactory.readNetworkFromFile("small_world_nodes.txt","small_world_edges.txt",true, StrategyType.ProThr);
//        Network small_world3 = NetworkFactory.readNetworkFromFile("small_world_nodes.txt","small_world_edges.txt",true, StrategyType.ProAvg);
////        Network twitter = NetworkFactory.readNetworkFromFile("twitter_nodes.txt","twitter_edges.txt",false, StrategyType.DetThr);
//        int startValue = 1;
//        int diffusionRound = 100;
//        int[] startPoints = {0,1};
//        System.out.println("网络生成完毕");
//
//        System.out.println("Scale_Free网络 D T");
//        scale_free0.startDiffusion(startPoints,startValue,diffusionRound);
//        System.out.println("Scale_Free网络 D A");
//        scale_free1.startDiffusion(startPoints,startValue,diffusionRound);
//        System.out.println("Scale_Free网络 P T");
//        scale_free2.startDiffusion(startPoints,startValue,diffusionRound);
//        System.out.println("Scale_Free网络 P A");
//        scale_free3.startDiffusion(startPoints,startValue,diffusionRound);
//
//        System.out.println("Random网络 D T");
//        random0.startDiffusion(startPoints,startValue,diffusionRound);
//        System.out.println("Random网络 D A");
//        random1.startDiffusion(startPoints,startValue,diffusionRound);
//        System.out.println("Random网络 P T");
//        random2.startDiffusion(startPoints,startValue,diffusionRound);
//        System.out.println("Random网络 P A");
//        random3.startDiffusion(startPoints,startValue,diffusionRound);
//
//        System.out.println("Regular网络 D T");
//        regular0.startDiffusion(startPoints,startValue,diffusionRound);
//        System.out.println("Regular网络 D A");
//        regular1.startDiffusion(startPoints,startValue,diffusionRound);
//        System.out.println("Regular网络 P T");
//        regular2.startDiffusion(startPoints,startValue,diffusionRound);
//        System.out.println("Regular网络 P A");
//        regular3.startDiffusion(startPoints,startValue,diffusionRound);
//
//        System.out.println("Small_world网络 D T");
//        small_world0.startDiffusion(startPoints,startValue,diffusionRound);
//        System.out.println("Small_world网络 D A");
//        small_world1.startDiffusion(startPoints,startValue,diffusionRound);
//        System.out.println("Small_world网络 P T");
//        small_world2.startDiffusion(startPoints,startValue,diffusionRound);
//        System.out.println("Small_world网络 P A");
//        small_world3.startDiffusion(startPoints,startValue,diffusionRound);
//
//    }

    public static void main(String[] args) {
//        NetworkParameter networkParameter = new NetworkParameter();
//        networkParameter.setBinary(true);
//        networkParameter.setStrategyType(StrategyType.DetThr);
//
//        Network dCluster = NetworkFactory.readSouthEaseNetworkFromFile(30000,100,"cluster.txt",networkParameter);
//        Network dRandom = NetworkFactory.readSouthEaseNetworkFromFile(30000,100,"random.txt",networkParameter);
//        Network dScaleFree = NetworkFactory.readSouthEaseNetworkFromFile(30000,100,"scalefree.txt",networkParameter);
//        Network dSmallWorld = NetworkFactory.readSouthEaseNetworkFromFile(30000,100,"smallworld.txt",networkParameter);
//
//        int[] startList = Util.getStartAgents(30000,0.01);
//        System.out.println("dCluster diffusion!");
//        dCluster.startDiffusion(startList,1,100);
//        System.out.println("dRandom diffusion!");
//        dRandom.startDiffusion(startList,1,100);
//        System.out.println("dScaleFree diffusion!");
//        dScaleFree.startDiffusion(startList,1,100);
//        System.out.println("dSmallWorld diffusion!");
//        dSmallWorld.startDiffusion(startList,1,100);
        for(int i=0;i<100;i++){
            System.out.println(Util.generagePositiveNormalValue(10,1));
        }
    }


}

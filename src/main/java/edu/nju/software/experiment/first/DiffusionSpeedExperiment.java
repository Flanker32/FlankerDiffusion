package edu.nju.software.experiment.first;

import edu.nju.software.Constant;
import edu.nju.software.IOHelper;
import edu.nju.software.bean.MultiDiffusionResult;
import edu.nju.software.bean.NetworkParameter;
import edu.nju.software.network.Network;
import edu.nju.software.network.NetworkFactory;
import edu.nju.software.network.NetworkHelper;
import edu.nju.software.network.NetworkType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2016/12/11.
 */
public class DiffusionSpeedExperiment {
    private Network random;
    private Network regular;
    private Network scale_free;
    private Network small_world;
    private Network twitter;
    private String outputFile;
    private static int networkNumber = 5;

    private int expRound;

    public DiffusionSpeedExperiment(NetworkParameter networkParameter, String outputFile,int expRound) {
        this.outputFile = outputFile;
        networkParameter.setNetworkType(NetworkType.Random);
        random = NetworkFactory.generateNetwork(networkParameter);

        networkParameter.setNetworkType(NetworkType.Regular);
        regular = NetworkFactory.generateNetwork(networkParameter);

        networkParameter.setNetworkType(NetworkType.ScaleFree);
        scale_free = NetworkFactory.generateNetwork(networkParameter);

        networkParameter.setNetworkType(NetworkType.SmallWorld);
        small_world = NetworkFactory.generateNetwork(networkParameter);

        twitter = NetworkFactory.readBigNetworkFromFile("twitter_nodes.txt","twitter_edges.txt",networkParameter,5000);
        twitter.changeAgentWeight(3,1);
        System.out.println("twitter "+twitter.getSize()+" "+twitter.getEdgeCount());
        double averageWeight = 3;
        double edgeAverageWeight = 1;
        double averageEdgeNumber = twitter.getEdgeCount()/twitter.getSize();
        double averageThreshold =edgeAverageWeight*averageWeight*averageEdgeNumber*Constant.THRESHOLD_AVERAGE_PERCENTAGE;

        twitter.changeAgentThreshold(averageThreshold,1);

        this.expRound = expRound;
    }

    private double[] calDevideSimilarity(int[] agentStatus,int total){
        double[] result = new double[agentStatus.length];
        for(int i=0;i<result.length;i++){
            result[i]=calDecideSimilarity(agentStatus[i],total);
        }
        return result;
    }

    private double calDecideSimilarity(int decide,int total){
        double result = 0.0;
        result =(Math.pow(decide-total/2,2)*4)/Math.pow(total,2);
        return result;
    }

    public void diffusionExperiment(double startPercentage, double startValue,int round) {
        MultiDiffusionResult randomResult = random.startMultiDiffusion(startPercentage,startValue,round,expRound);
        MultiDiffusionResult regularResult = regular.startMultiDiffusion(startPercentage,startValue,round,expRound);
        MultiDiffusionResult scale_freeResult = scale_free.startMultiDiffusion(startPercentage,startValue,round,expRound);
        MultiDiffusionResult small_worldResult = small_world.startMultiDiffusion(startPercentage,startValue,round,expRound);
        MultiDiffusionResult twitterResult = twitter.startMultiDiffusion(startPercentage,startValue,round,expRound);

        List<MultiDiffusionResult> temp = new ArrayList<>();
        temp.add(randomResult);
        temp.add(regularResult);
        temp.add(scale_freeResult);
        temp.add(small_worldResult);
        temp.add(twitterResult);

        NetworkHelper.writeMultiDiffusionResultToFile(temp,outputFile+"_Diffusion");

        List<double[]> agentDecideSimilarityList = new ArrayList<>();
        agentDecideSimilarityList.add(calDevideSimilarity(randomResult.getAgentStatus(),randomResult.getTerms()));
        agentDecideSimilarityList.add(calDevideSimilarity(regularResult.getAgentStatus(),randomResult.getTerms()));
        agentDecideSimilarityList.add(calDevideSimilarity(scale_freeResult.getAgentStatus(),randomResult.getTerms()));
        agentDecideSimilarityList.add(calDevideSimilarity(small_worldResult.getAgentStatus(),randomResult.getTerms()));
        agentDecideSimilarityList.add(calDevideSimilarity(twitterResult.getAgentStatus(),twitterResult.getTerms()));

        List<String> output = new ArrayList<String>();
        DecimalFormat df = new DecimalFormat("0.00");
        for(int i=0;i<agentDecideSimilarityList.get(0).length;i++){
            String temple = new String();
            for(double[] list:agentDecideSimilarityList){
                temple = temple+ df.format(list[i])+",";
            }
            output.add(temple);
        }
        IOHelper.writeToFile(outputFile+"_DecideSimilarity.txt",output);
    }



}

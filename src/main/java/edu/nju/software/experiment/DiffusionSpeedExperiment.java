package edu.nju.software.experiment;

import edu.nju.software.Constant;
import edu.nju.software.IOHelper;
import edu.nju.software.bean.DiffusionResult;
import edu.nju.software.bean.NetworkParameter;
import edu.nju.software.network.Network;
import edu.nju.software.network.NetworkFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Dell on 2016/12/11.
 */
public class DiffusionSpeedExperiment {
    private Network random;
    private Network regular;
    private Network scale_free;
    private Network small_world;
    private String outputFile;
    private static int networkNumber = 4;

    public DiffusionSpeedExperiment(NetworkParameter networkParameter, String outputFile) {
        this.outputFile = outputFile;
        random = NetworkFactory.readNetworkFromFile(Constant.randomNode, Constant.randomEdge, networkParameter);
        regular = NetworkFactory.readNetworkFromFile(Constant.regularNode, Constant.regularEdge, networkParameter);
        scale_free = NetworkFactory.readNetworkFromFile(Constant.scaleFreeNode, Constant.scaleFreeEdge, networkParameter);
        small_world = NetworkFactory.readNetworkFromFile(Constant.smallWorldNode, Constant.smallWorldEdge, networkParameter);
    }

    public void diffusionExperiment(double startPercentage, double startValue,int round) {
//        int[] randomStartList = getStartAgents(random.getSize(),startPercentage);
//        DiffusionResult randomResult = random.startDiffusion(randomStartList,startValue,round);
//        int[] regularStartList = getStartAgents(regular.getSize(),startPercentage);
//        DiffusionResult regularResult = random.startDiffusion(regularStartList,startValue,round);
//        int[] scale_freeStartList = getStartAgents(scale_free.getSize(),startPercentage);
//        DiffusionResult scale_freeResult = random.startDiffusion(scale_freeStartList,startValue,round);
//        int[] small_worldStartList = getStartAgents(small_world.getSize(),startPercentage);
//        DiffusionResult small_worldResult = random.startDiffusion(small_worldStartList,startValue,round);
//
//        DiffusionResult[] temp = new DiffusionResult[networkNumber];
//        temp[0]=randomResult;
//        temp[1]=regularResult;
//        temp[2]=scale_freeResult;
//        temp[3]=small_worldResult;
//
//        int maxRound = 0;
//        for(DiffusionResult diffusionResult:temp){
//            if(diffusionResult.getDiffusionRound()>maxRound){
//                maxRound=diffusionResult.getDiffusionRound();
//            }
//        }
//
//        List<String> termOutput = new ArrayList<String>();
//        List<String> totalOutput = new ArrayList<String>();
//        int[] total = new int[networkNumber];
//        for(int i=0;i<maxRound;i++){
//            int[] perTerm = new int[networkNumber];
//            //for(DiffusionResult diffusionResult:temp){
//            for(int j=0;j<networkNumber;j++){
//                DiffusionResult diffusionResult = temp[j];
//                if(i<diffusionResult.getDiffusionRound()){
//                    perTerm[j]=diffusionResult.getDiffusePerTerm()[i];
//                    total[j]+=perTerm[j];
//                }else{
//                    perTerm[j]=0;
//                }
//            }
//            String termOut = perTerm[0]+" "+perTerm[1]+" "+perTerm[2]+" "+perTerm[3];
//            String totalOut = total[0]+" "+total[1]+" "+total[2]+" "+total[3];
//            termOutput.add(termOut);
//            totalOutput.add(totalOut);
//        }
//
//        IOHelper.writeToFile("term_"+outputFile,termOutput);
//        IOHelper.writeToFile("total_"+outputFile,totalOutput);
    }

}

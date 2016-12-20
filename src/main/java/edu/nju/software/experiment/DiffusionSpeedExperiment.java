package edu.nju.software.experiment;

import edu.nju.software.IOHelper;
import edu.nju.software.bean.MultiDiffusionResult;
import edu.nju.software.bean.NetworkParameter;
import edu.nju.software.network.Network;
import edu.nju.software.network.NetworkFactory;
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
    private String outputFile;
    private static int networkNumber = 4;

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

        this.expRound = expRound;
    }

    public void diffusionExperiment(double startPercentage, double startValue,int round) {
        MultiDiffusionResult randomResult = random.startMultiDiffusion(startPercentage,startValue,round,expRound);
        MultiDiffusionResult regularResult = regular.startMultiDiffusion(startPercentage,startValue,round,expRound);
        MultiDiffusionResult scale_freeResult = scale_free.startMultiDiffusion(startPercentage,startValue,round,expRound);
        MultiDiffusionResult small_worldResult = small_world.startMultiDiffusion(startPercentage,startValue,round,expRound);

        MultiDiffusionResult[] temp = new MultiDiffusionResult[networkNumber];
        temp[0]=randomResult;
        temp[1]=regularResult;
        temp[2]=scale_freeResult;
        temp[3]=small_worldResult;

        int maxRound = 0;
        for(MultiDiffusionResult diffusionResult:temp){
            if(diffusionResult.getMaxDiffusionRound()>maxRound){
                maxRound=diffusionResult.getMaxDiffusionRound();
            }
        }

        DecimalFormat df = new DecimalFormat("0.00");
        List<String> termOutput = new ArrayList<String>();
        List<String> totalOutput = new ArrayList<String>();
        double[] total = new double[networkNumber];
        for(int i=0;i<maxRound;i++){
            double[] perTerm = new double[networkNumber];
            //for(DiffusionResult diffusionResult:temp){
            for(int j=0;j<networkNumber;j++){
                MultiDiffusionResult diffusionResult = temp[j];
                if(i<diffusionResult.getMaxDiffusionRound()){
                    perTerm[j]=diffusionResult.getAverageDiffusePerTerm(i);
                    total[j]+=perTerm[j];
                }else{
                    perTerm[j]=0;
                }
            }
            String termOut = df.format(perTerm[0])+","+df.format(perTerm[1])+","+df.format(perTerm[2])+","+df.format(perTerm[3]);
            String totalOut = df.format(total[0])+","+df.format(total[1])+","+df.format(total[2])+","+df.format(total[3]);
            termOutput.add(termOut);
            totalOutput.add(totalOut);
        }

        IOHelper.writeToFile("term_"+outputFile,termOutput);
        IOHelper.writeToFile("total_"+outputFile,totalOutput);
    }

}

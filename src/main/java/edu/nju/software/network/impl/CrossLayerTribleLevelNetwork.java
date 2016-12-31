package edu.nju.software.network.impl;

import edu.nju.software.Constant;
import edu.nju.software.Util;
import edu.nju.software.agent.MultiLevelNode;
import edu.nju.software.agent.impl.MultiLevelAgent;
import edu.nju.software.bean.NetworkParameter;
import edu.nju.software.network.MultiLevelNetwork;

/**
 * Created by Dell on 2016/12/31.
 */
public class CrossLayerTribleLevelNetwork extends MultiLevelNetwork {

    public CrossLayerTribleLevelNetwork(NetworkParameter[] networkParameter, double multiPercentage) {
        super(networkParameter, multiPercentage);

        addCrossLink();
        System.out.println("Network size: "+agents.size());
    }

    private void addCrossLink(){
        double mean = Constant.CROSS_MEAN;
        double variance = Constant.CROSS_VARIANCE;

        for(MultiLevelAgent agent:agents){
            if(!agent.isCrossLevelAgent()){
                continue;
            }else{
                for(MultiLevelNode first:agent.getNodes().keySet()){
                    for(MultiLevelNode second:agent.getNodes().keySet()){
                        if(first==second){
                            continue;
                        }else{
                            first.addOtherLevelOutput(second, Util.generagePositiveNormalValue(mean,variance));
                            second.addOtherLevelOutput(first,Util.generagePositiveNormalValue(mean,variance));
                        }
                    }
                }
            }
        }
    }


}

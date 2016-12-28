package edu.nju.software.network;
import edu.nju.software.bean.DiffusionResult;

/**
 * Created by Dell on 2016/12/27.
 */
public interface MultiLevelNetwork extends Network{
    public boolean addNetwork(Network network, int id);
    public boolean setMultiLevelAgent(int[][] list);
    public DiffusionResult startDiffusion(double startPercentage, double startvalue, int round);
}

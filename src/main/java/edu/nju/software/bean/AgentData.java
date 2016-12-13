package edu.nju.software.bean;



/**
 * Created by Dell on 2016/12/12.
 */
public class AgentData {
    private int id;
    private double threshold;
    private double weight;

    public AgentData() {
    }

    public AgentData(int id, double threshold, double weight) {
        this.id = id;
        this.threshold = threshold;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}


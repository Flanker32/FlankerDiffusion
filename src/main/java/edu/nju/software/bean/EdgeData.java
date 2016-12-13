package edu.nju.software.bean;

/**
 * Created by Dell on 2016/12/12.
 */
public class EdgeData {
    private int start;
    private int end;
    private double weight;

    public EdgeData() {
    }

    public EdgeData(int start, int end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

package ir.ac.kntu;

import java.io.*;

public class Player implements Serializable {
    private double score;

    private String name;

    private int lastLevel;

    public Player(double score, String name,int lastLevel) {
        this.score = score;
        this.name = name;
        this.lastLevel=lastLevel;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLastLevel() {
        return lastLevel;
    }

    public void setLastLevel(int lastLevel) {
        this.lastLevel = lastLevel;
    }

    @Override
    public String toString() {
        return "Player:" +
                "   name=" + name +
                "   score=" + score +
                "   lastLevel=" + lastLevel ;
    }
}

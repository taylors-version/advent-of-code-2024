package com.ben.aoc;

public class LinearEqs {
    private double ax;
    private double ay;
    private double bx;
    private double by;
    private double px;
    private double py;

    public LinearEqs(int ax, int ay, int bx, int by, int px, int py) {
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
        this.px = px;
        this.py = py;
    }

    public double solution(){
        double a = (px * by - py * bx) / (ax * by - ay * bx);
        double b = (px - ax * a) / bx;
        if(a % 1 != 0 || b % 1 != 0){
            return 0;
        }
        return 3*a + b;
    }

    public double solutionFar(){
        px+=10000000000000L;
        py+=10000000000000L;
        return solution();
    }

}

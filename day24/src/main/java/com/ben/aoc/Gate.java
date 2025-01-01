package com.ben.aoc;

public class Gate {
    String wireA;
    String wireB;
    String type;
    String wireOut;

    public Gate(String gate){
        String[] out = gate.split(" -> ");
        wireOut = out[1];
        String[] processing = out[0].split(" ");
        wireA = processing[0];
        wireB = processing[2];
        type = processing[1];
    }

    public boolean calculate(boolean a, boolean b){
        return switch (type) {
            case "AND" -> a && b;
            case "OR" -> a || b;
            default -> a ^ b;
        };
    }
}

package com.ben.aoc;

import java.util.*;

public class Day24 {
    List<String> lines;
    Map<String, Boolean> wires;
    Set<Gate> unresolvedGates;


    public Day24(String filename){
        lines = Util.readFile(getClass(), filename);
    }

    private void init(){
        wires = new HashMap<>();
        unresolvedGates = new HashSet<>();

        for(String line : lines){
            if(line.contains(":")){
                String[] split = line.split(": ");
                wires.put(split[0], split[1].equals("1"));
            } else if (line.contains("->")) {
                unresolvedGates.add(new Gate(line));
            }
        }
    }

    private List<Gate> findFaultyGates() {
        List<Gate> faultyGates = new ArrayList<>();
        for (Gate gate : unresolvedGates) {
            if (gate.wireOut.startsWith("z") && !gate.wireOut.equals("z45")) {
                if (!gate.type.equals("XOR")) {
                    faultyGates.add(gate);
                }
            } else if (!gate.wireOut.startsWith("z")
                    && !(gate.wireA.startsWith("x") || gate.wireA.startsWith("y"))
                    && !(gate.wireB.startsWith("x") || gate.wireB.startsWith("y"))) {
                if (gate.type.equals("XOR")) {
                    faultyGates.add(gate);
                }
            } else if (gate.type.equals("XOR")
                    && (gate.wireA.startsWith("x") || gate.wireA.startsWith("y"))
                    && (gate.wireB.startsWith("x") || gate.wireB.startsWith("y"))) {
                if (!(gate.wireA.endsWith("00") && gate.wireB.endsWith("00"))) {
                    String output = gate.wireOut;
                    boolean anotherFound = false;
                    for (Gate c2 : unresolvedGates) {
                        if (!c2.equals(gate)) {
                            if ((c2.wireA.equals(output) || c2.wireB.equals(output))
                                    && c2.type.equals("XOR")) {
                                anotherFound = true;
                                break;
                            }
                        }
                    }
                    if (!anotherFound) {
                        faultyGates.add(gate);
                    }
                }
            } else if (gate.type.equals("AND")
                    && (gate.wireA.startsWith("x") || gate.wireA.startsWith("y"))
                    && (gate.wireB.startsWith("x") || gate.wireB.startsWith("y"))) {
                if (!(gate.wireA.endsWith("00") && gate.wireB.endsWith("00"))) {
                    String output = gate.wireOut;
                    boolean anotherFound = false;
                    for (Gate gate2 : unresolvedGates) {
                        if (!gate2.equals(gate)) {
                            if ((gate2.wireA.equals(output) || gate2.wireB.equals(output))
                                    && gate2.type.equals("OR")) {
                                anotherFound = true;
                                break;
                            }
                        }
                    }
                    if (!anotherFound) {
                        faultyGates.add(gate);
                    }
                }
            }
        }
        return faultyGates;
    }


    public Long puzzle1(){
        init();
        long result = 0;

        while (!unresolvedGates.isEmpty()){
            for (Iterator<Gate> iterator = unresolvedGates.iterator(); iterator.hasNext();){
                Gate gate = iterator.next();
                if(wires.containsKey(gate.wireA) && wires.containsKey(gate.wireB)){
                    iterator.remove();
                    boolean a = wires.get(gate.wireA);
                    boolean b = wires.get(gate.wireB);
                    wires.put(gate.wireOut, gate.calculate(a, b));
                }
            }
        }

        List<String> zWires = new ArrayList<>();
        for(String wire : wires.keySet()){
            if(wire.startsWith("z")) zWires.add(wire);
        }
        Collections.sort(zWires);

        for(int i = 0; i < zWires.size(); i++){
            if(wires.get(zWires.get(i))) {
                result += (long) Math.pow(2, i);
            }
        }

        return result;
    }

    public String puzzle2(){
        init();
        List<Gate> faultyGates = findFaultyGates();
        List<String> faultyOuts = new ArrayList<>();
        for(Gate gate : faultyGates){
            faultyOuts.add(gate.wireOut);
        }
        Collections.sort(faultyOuts);
        return String.join("," , faultyOuts);
    }

}

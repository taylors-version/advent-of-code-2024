package com.ben.aoc;

import org.javatuples.Pair;

import java.util.*;

public class Day07 {
    String filename;
    List<Pair<Long, List<Long>>> calibrations;

    public Day07(String filename){
        this.filename = filename;
    }

    private void init(){
        List<String> lines = Util.readFile(getClass(), filename);
        calibrations = new ArrayList<>();
        for(String line : lines){
            String [] numbers = line.replace(":", "").split(" ");
            List<Long> operands = new ArrayList<>();
            for(int i = 1; i<numbers.length; i++){
                operands.add(Long.parseLong(numbers[i]));
            }
            calibrations.add(new Pair<>(Long.parseLong(numbers[0]), operands));
        }
    }

    public boolean isPossible(Long goal, List<Long> operands){
        if(operands.size() == 1){
            return goal.equals(operands.get(0));
        }
        if(operands.get(0) > goal){
            return false;
        }
        Long added = operands.get(0) + operands.get(1);
        Long multiplied = operands.get(0) * operands.get(1);
        operands.remove(0);
        operands.remove(0);
        List<Long> add = new ArrayList<>(operands);
        add.add(0, added);
        List<Long> multiply = new ArrayList<>(operands);
        multiply.add(0, multiplied);

        return isPossible(goal, add) || isPossible(goal, multiply);
    }

    public boolean isPossibleConCat(Long goal, List<Long> operands){
        if(operands.size() == 1){
            return goal.equals(operands.get(0));
        }
        if(operands.get(0) > goal){
            return false;
        }
        Long added = operands.get(0) + operands.get(1);
        Long multiplied = operands.get(0) * operands.get(1);
        Long concatted = Long.parseLong(operands.get(0).toString() + operands.get(1).toString());
        operands.remove(0);
        operands.remove(0);
        List<Long> add = new ArrayList<>(operands);
        add.add(0, added);
        List<Long> multiply = new ArrayList<>(operands);
        multiply.add(0, multiplied);
        List<Long> concat = new ArrayList<>(operands);
        concat.add(0, concatted);

        return isPossibleConCat(goal, add) || isPossibleConCat(goal, multiply) || isPossibleConCat(goal, concat);
    }

    public long puzzle1(){
        init();
        long result = 0;
        for(Pair<Long,List<Long>> p : calibrations){
            if(isPossible(p.getValue0(), p.getValue1())){
                result += p.getValue0();
            }
        }
        return result;
    }

    public long puzzle2(){
        init();
        long result = 0;
        for(Pair<Long,List<Long>> p : calibrations){
            if(isPossibleConCat(p.getValue0(), p.getValue1())){
                result += p.getValue0();
            }
        }
        return result;
    }
}

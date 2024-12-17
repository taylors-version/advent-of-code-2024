package com.ben.aoc;

import org.javatuples.Pair;

import java.util.*;

public class Day05 {
    List<Pair<Integer, Integer>> rules;
    List<List<Integer>> manuals;

    public Day05(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        int blank = lines.indexOf("");

        rules = new ArrayList<>();
        manuals = new ArrayList<>();

        for(int i = 0; i<blank; i++){
            String[] rule = lines.get(i).split("\\|");
            rules.add(new Pair<Integer, Integer>(Integer.parseInt(rule[0]), Integer.parseInt(rule[1])));
        }
        for(int i = blank+1; i<lines.size(); i++){
            List<Integer> manual = new ArrayList<>();
            String[] man = lines.get(i).split(",");
            for(String num : man){
                manual.add(Integer.parseInt(num));
            }
            manuals.add(manual);
        }
        String ben = "ben";
    }

    public List<Integer> ordered(List<Integer> toBe){
        List<Integer> sorted = new ArrayList<>(toBe);
        Comparator<Integer> pageComparator =
                (Integer a, Integer b) -> {
            if(Objects.equals(a, b)) return 0;
            if(rules.contains(new Pair<>(a,b))){
                return -1;
            }return 1;
                };
        sorted.sort(pageComparator);
        return sorted;
    }

    public long puzzle1(){
        long result = 0;

        for(List<Integer> manual : manuals){
            List<Integer> sorted = ordered(manual);
            if(manual.equals(sorted)){
                result += manual.get((manual.size()-1) /2);
            }
        }

        return result;
    }

    public long puzzle2(){
        long result = 0;

        for(List<Integer> manual : manuals){
            List<Integer> sorted = ordered(manual);
            if(!manual.equals(sorted)){
                result += sorted.get((sorted.size()-1) /2);
            }
        }

        return result;
    }
}

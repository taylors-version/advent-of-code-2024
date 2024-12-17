package com.ben.aoc;

import com.ben.aoc.collection.Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 {
    List<List<Integer>> inputs = new ArrayList<>();

    public Day02(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        for(String line : lines){
            List<String> l = Arrays.asList(line.split(" "));
            inputs.add(l.stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
    }

    public boolean isSorted(List<Integer> toTest){
        List<Integer> sorted = new ArrayList<>(toTest.stream().sorted().toList());
        if(toTest.equals(sorted)) return true;
        Collections.reverse(sorted);
        return toTest.equals(sorted);
    }

    public boolean isSmallSteps(List<Integer> toTest){
        List<List<Integer>> sliding = Collection.sliding(toTest, 2);
        for(List<Integer> window : sliding){
            int diff = Math.abs(window.get(0) - window.get(1));
            if(diff == 0 || diff > 3){
                return false;
            }
        }
        return true;
    }

    public boolean safeWithBuffer(List<Integer> toTest){
        for(int i = 0; i < toTest.size(); i++){
            List<Integer> newList = new ArrayList<>(toTest);
            newList.remove(i);
            if(isSorted(newList) && isSmallSteps(newList)){
                return true;
            }
        }
        return false;
    }

    public long puzzle1(){
        long result = 0;
        for(List<Integer> input : inputs){
            if(isSorted(input) && isSmallSteps(input)){
                result++;
            }
        }
        return result;
    }

    public long puzzle2(){
        long result = 0;
        for(List<Integer> input : inputs){
            if(isSorted(input) && isSmallSteps(input) || safeWithBuffer(input)){
                result++;
            }
        }

        return result;
    }
}

package com.ben.aoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 {
    List<Integer> lhs = new ArrayList<>();
    List<Integer> rhs = new ArrayList<>();

    public Day01(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        for(String line : lines){
            String[] numbers = line.split(" {3}");
            lhs.add(Integer.parseInt(numbers[0]));
            rhs.add(Integer.parseInt(numbers[1]));
        }
        Collections.sort(lhs);
        Collections.sort(rhs);
    }

    public long puzzle1(){
        long result = 0;

        for(int i = 0; i<lhs.size(); i++){
            result += Math.abs(lhs.get(i) - rhs.get(i));
        }

        return result;
    }

    public long puzzle2(){
        long result = 0;

        for(Integer number: lhs){
            int firstIndex = rhs.indexOf(number);
            if(firstIndex > -1) {
                result += (long) number * (rhs.lastIndexOf(number) - firstIndex + 1);
            }
        }

        return result;
    }
}
